[toc]

# Pipe 管道

管道是具有 `@Injectable()`装饰器的类。管道应该实现`PipeTransform`接口

管道又两个典型的应用场景

*   转换: 管道将输入数据转换为所需的数据输出(例如，将字符串转换为整数)
*   验证: 对输入数据进行验证，如果验证成功继续传递，验证失败则抛出异常。

## 内置管道

-   `ValidationPipe`
-   `ParseIntPipe`
-   `ParseFloatPipe`
-   `ParseBoolPipe`
-   `ParseArrayPipe`
-   `ParseUUIDPipe`
-   `ParseEnumPipe`
-   `DefaultValuePipe`
-   `ParseFilePipe`

>   需要从`@nestjs/common`包中导出

## 绑定管道

#### 类型传递

~~~ ts
@Get(':id')
async findOne(@Param('id',ParseIntPipe) id:number) {
    return this.catsService.findOne(id);
}
~~~

这确保了在`FindOne()`方法中接收的是一个数字，或者在路由处理程序被调用之前抛出异常。

~~~ json
{
  "statusCode": 400,
  "message": "Validation failed (numeric string is expected)",
  "error": "Bad Request"
}
~~~

这个异常阻止了`findOne()`方法的执行。

#### 值传递

~~~ ts
@Get(':id')
async findOne(
	@Param('id',new ParseIntPipe({errorHttpStatusCode:HttpStatus.NOT_ACCEPTABLE}))
    id:number,
){
    return this.catsService.findOne(id);
}
~~~

#### 例子

验证字符串参数

~~~ ts
@Get()
async findOne(@Query('id',ParseIntPipe) id:number) {
    return this.catsService.findOne(id);
}
~~~

解析字符串并验证是否为UUID

~~~ ts
@Get(':uuid')
async findOne(@Param('uuid',new ParseUUIDPipe()) uuid:string) {
    return this.catsService.findOne(uuid);
}
~~~

>   `ParseUUIDPipe()`时，将解析版本3、版本4和版本5的UUID，如果需要特定版本的UUID，可以在管道选项中传递版本。

## 自定义管道

~~~ ts
import { PipeTransform,Injectable,ArgumentMetadata } from '@nestjs/common';

@Injectable()
export class ValidationPipe implements PipeTransform {
    transform(value:any,metada:ArgumentMetadata) {
        return value;
    }
}
~~~

>   `PipeTransform<T,R>`是每一个管道必须要实现的泛型接口。泛型`T`表明输入的`value`的类型，`R`表明`transform()`方法返回的类型

实现`PipeTransform`，每个管道必须声明`transform()`方法。该方法有两个参数

*   value：当前处理的方法参数
*   metadata：处理的方法参数的元数据

ArgumentMetadata

~~~ ts
export interface ArgumentMetadata {
    type: 'body' | 'query' | 'param' | 'custom';
    metatype?: Type<unknown>;
    data?:string;
}
~~~

*   type: 参数的种类，body、query、param、或者是自定义参数
*   metatype: 参数的元类型，例如string，如果在函数签名中省略类型声明，或者使用原生javascipt，则为underfined
*   data: 传递给装饰器的字符串，例如@Body(‘string’)，如果括号留空，则为underfined

## 对象结构验证

[Joi](https://github.com/hapijs/joi)库允许使用刻度的API以直接的方式创建schema

~~~ shell
npm install --save joi
npm install --save-dev @types/joi
~~~

~~~ ts
import { PipeTransform,Injectable,ArgumentMetadata,BadRequestException } from '@nestjs/common';
import { ObjectSchema } from 'joi';

@Injectable()
export class JoiValidationPipe import PipeTransform {
    constructor(private schema:ObjectSchema) {}
    transform(value:any,metadata:ArgumentMetadata) {
        const {error} = this.schema.validate(value);
        if(error) {
            throw new BadRequestException('Validation failed');
        }
        return value;
    }
}
~~~

## 绑定验证管道

1.   创建`JoiValidationPipe`实例
2.   传递上下文特定的Joi schema 给构造函数
3.   绑定到方法

~~~ ts
@Post()
@UsePipes(new JoiValidationPipe(createCatSchema))
async create(@Body() createCatDto:CreateCatDto) {
    this.catsService.create(createCatDto);
}
~~~

>   从`@nestjs/common`包导入`@UsePipes()`装饰器

## 类验证器

>   只有`TypeScript`中可以使用

~~~ shell
npm i --save class-validator class-transformer
~~~

可以向`Dto`类添加一些装饰器。

~~~ ts
import { IsString, IsInt } from 'class-validator';

export class CreateCatDto {
    @IsString()
    name: string;
    
    @IsInt()
    age number;
    
    @IsString()
    breed:string;
}
~~~

创建一个`ValidationPipe`类 validate.pipe.ts

~~~ ts
import { PipeTransform, Injectable, ArgumentMetadata, BadRequestException } from '@nestjs/common';
import { validate } from 'class-validator';
import { plainToInstance } from 'class-transformer';

@Injectable()
export class ValidationPipe implements PipeTransform<any> {
  async transform(value: any, { metatype }: ArgumentMetadata) {
    if (!metatype || !this.toValidate(metatype)) {
      return value;
    }
    const object = plainToInstance(metatype, value);
    const errors = await validate(object);
    if (errors.length > 0) {
      throw new BadRequestException('Validation failed');
    }
    return value;
  }

  private toValidate(metatype: Function): boolean {
    const types: Function[] = [String, Boolean, Number, Array, Object];
    return !types.includes(metatype);
  }
}
~~~

绑定

~~~ ts
@Post()
async create(
	@Body(new ValidationPipe()) craeteCatDto: CreateCatDto,
) {
    this.catsService.create(createCatDto);
}
~~~

## 全局管道

~~~ ts
async function bootstrap(){
    const app = await NestFactory.create(AppModule);
    app.useGlobalPipes(new ValidationPipe());
    await app.listen(3000);
}
bootstrap();
~~~

>   在混合模式中，`useGlobalPipes()`方法不会为网关和微服务设置管道，对于标准(非混合)微服务应用程序应用使用`useGlobalPipes()全局设置管道。`

如上方式无法注入依赖，因为他们不属于任何模块。为了解决这个问题，可以使用以下构造函数直接为任何模块设置管道

app.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { APP_PIPE } from '@nestjs/core';

@Module({
    providers:[
        {
            provide:APP_PIPE,
            useClass:ValidationPipe
        }
    ]
})
export class AppModule {}
~~~

## 转换的应用场景

```typescript
import { PipeTransform, Injectable, ArgumentMetadata, BadRequestException } from '@nestjs/common';

@Injectable()
export class ParseIntPipe implements PipeTransform<string, number> {
  transform(value: string, metadata: ArgumentMetadata): number {
    const val = parseInt(value, 10);
    if (isNaN(val)) {
      throw new BadRequestException('Validation failed');
    }
    return val;
  }
}
```

使用

~~~ ts
@Get(':id')
async findOne(@Param('id', new ParseIntPipe()) id) {
  return this.catsService.findOne(id);
}
~~~

另一种使用是按ID从数据库中选择一个现有的**用户实体**

```typescript
@Get(':id')
findOne(@Param('id', UserByIdPipe) userEntity: UserEntity) {
  return userEntity;
}
```

## 提供默认值

>   当接收到null或者underfined时，会抛异常

```typescript
@Get()
async findAll(
  @Query('activeOnly', new DefaultValuePipe(false), ParseBoolPipe) activeOnly: boolean,
  @Query('page', new DefaultValuePipe(0), ParseIntPipe) page: number,
) {
  return this.catsService.findAll({ activeOnly, page });
}
```