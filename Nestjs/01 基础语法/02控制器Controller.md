[toc]

# 控制器 Controller

控制器负责处理传入的**请求**和向客户端返回**响应**。

控制器的目的是可以接收应用的特定请求，**路由**机制控制哪个控制器接收哪些请求。通常，每个控制器有多个路由，不同的路由可以执行不同的操作。

可以使用**装饰器**将类与所需的元素相关联，并使Nest能够创建路由映射(将请求绑定到相应的控制器)。

## @Controller()

定义控制器，可选路由前缀

~~~ ts
import { Controller,Get } from '@nestjs/common';
@Controller('cats')
export class CatsController{
    @Get()
    findAll():string{
        return 'test';
    }
}
~~~

>   可以使用cli创建控制器
>
>   ~~~ shell
>   nest g controller cats
>   ~~~

### 响应规则

#### 标准

当请求返回一个JavaScript时，将自动序列化为Json，如果时基本类型，则只发送值;

#### 类库特有的

通过`@Res()`类注入类库特有的响应对象(如Express)。

## Request 请求

处理程序有时需要访问客户端的**请求**细节。

可以再处理函数的签名中使用`@Req()`装饰器，指示Nest将请求对象注入处理程序。

~~~ ts
import { Controller,Get Req } from '@nestjscommon';
import { Request } from 'express';
@Controller('cats')
export class CatsController{
    @Get
    findAll(@Req() request:Request):string{
        return 'this action returns all cats';
    }
}
~~~

>   为了在express中使用Typescript，需要安装`@types/express`。

| 装饰器                 | 对象                            |
| ---------------------- | ------------------------------- |
| @Request(). @Req()     | req                             |
| @Response(). @Res()    | res                             |
| @Next                  | next                            |
| @Session()             | req.session                     |
| @Param(key?:string)    | req.params / req.params[key]    |
| @Body(key?:string)     | req.body / req.body[key]        |
| @Query(key?:string)    | req.query / req.query[key]      |
| @Headers(name?:string) | req.headers / req.headers[name] |
| @Ip()                  | req.ip                          |
| @HostParam()           | req.hosts                       |

## 路由通配符

例如：<kbd>*</kbd>星号用作通配符将匹配任何字符组合

~~~ ts
@Get('ab*cd')
findAll(){
    return 'test';
}
~~~

字符 `?` 、`+` 、 `*` 以及 `()` 是它们的正则表达式对应项的子集。连字符（`-`） 和点（`.`）按字符串路径逐字解析。

## Code 状态码

默认情况下，状态码总是默认为**200**

可以通过`@HttpCode(...)`来修改状态码

~~~ ts
@Post()
@HttpCode(204)
create(){
    return 'test';
}
~~~

>   HttpCode 需要从@nestjs/common导入

## Headers 响应头

指定自定义响应头，可以使用`@header()`装饰器或类库特有的响应对象(并直接调用`res.header()`)。

~~~ ts
@Post()
@Header('Cache-Control','none')
create(){
    return 'test';
}
~~~

>   Header 需要从`@nestjs/common`导入

## Redirect 重定向

`@Redirect()`装饰器有两个可选参数,`url`和`statusCode`，如果省略，则`statusCode`默认为 302

~~~ ts
@Get()
@Redirect('https://nestjs.com',301)
~~~

如果要动态决定HTTP状态码和URL，可以通过路由处理方法来返回一个如下格式的对象

~~~ json
{
    "url":string,
    "statusCode":number
}
~~~

返回的值将覆盖传递给`@Redirect()`装饰器的所有参数

~~~ ts
@Get('docs')
@Redirect('https://docs.nestjs.com', 302)
getDocs(@Query('version') version) {
  if (version && version === '5') {
    return { url: 'https://docs.nestjs.com/v5/' };
  }
}

~~~

## 路由参数

路由中使用动态数据`/cat/1`

~~~ ts
@Get(':id')
findOne(@Param() params):string{
    console.log(params.id);
}
~~~

直接引入某个属性

~~~ ts
@Get(':id')
findOne(@Param('id') id):string{
    return id;
}
~~~

## 子域路由

`@Controller`装饰器可以接受一个host选项，以要求传入请求的HTTP主机匹配某个特定值。

~~~ ts
@Controller({admin.example.com})
export class AdminController {
    @Get
    index():string{
        return 'Admin page';
    }
}
~~~

>   Fastify 缺乏对嵌套路由器的支持

#### 动态值

~~~ ts
@Controller({host:':account.example.com'})
export class AccountController {
    @Get
    getInfo(@HostParam('account') account:string)
    return account;
}
~~~

## 作用域

@Todo

## 异步性 Async / await

每个异步函数都必须返回一个 Promise。这意味着可以返回延迟值，而Nest将自行解析它。

两种方式

~~~ ts
@Get
async findAll():Promise<any[]> {
    return [];
}
~~~

~~~ ts
@Get
findAll():Observable<any[]>{
    return of([]);
}
~~~

## 请求负载

~~~ ts
export class CreateCatDto {
    readonly name:string;
    readonly age:number;
    readonly breed:string;
}
~~~

~~~ ts
@Post()
async create(@Body() createCatDto:CreateCatDto) {
	return 'this action adds a new cat';
}
~~~

## 示例

~~~ ts
import { Controller, Get, Query, Post, Body, Put, Param, Delete } from '@nestjs/common';
import { CreateCatDto, UpdateCatDto, ListAllEntities } from './dto';

@Controller(cats)
export class CatsController {
    @Post
    create(@Body() createCatDto:CreateCatDto) {
        return 'this action adds a new cat';
    }
    
    @Get()
    findAll(@Query() query:ListAllEntities) {
        return query.limit;
    }
    
    @Get(':id')
    findOne(@Param('id') id:string) {
        return 'this action returns a #' + id;
    }
    
    @Put(':id')
    update(@Param('id') id:string,@Body() updateCatDto:UpdateCatyDto) {
        return id;
    }
    @Delete(':id')
    remove(@Param('id') id:string){
        return id;
    }
}
~~~

>   Nest cli 中提供了能够自动生成模板的生成器，可以不用手写

控制器已经就绪，但是Nest还不知道CatsController是否存在，需要在`@Module()`中添加

app.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { CatsController } from './cats/cats.controller';

@Module({
    controllers:[CatsController],
})
export class AppModule{}
~~~







