[toc]

# 提供者 Providers

Providers 是Nest的一个基本概念，许多基本的Nest类都可以被视为provider - service，repository， factory， helper等等

可以通过constructor注入依赖关系。这意味着对象可以彼此创建各种关系，并且连接对象实例的功能在很大程度上可以委托给Nest运行时系统。

**Provider指示一个用`@injectable()`装饰器注释的类。**

控制器应处理HTTP请求并将更复杂的任务委托给providers。

## service 服务

~~~ ts
import { Injectable } from '@nestjs/common';
import { Cat } from './interfaces/cat.interface';

@Injectable()
export class catsService {
    private readonly cats:Cat[] = [];
    
    create(cat:Cat) {
        this.cats.push(cat);
    }
    
    findAll():Cat[] {
        return this.chats;
    }
}
~~~

>   使用cli创建服务类
>
>   ~~~ shell
>   nest g service cats
>   ~~~

cat 接口

~~~ ts
export interface cat {
    name:string;
    age:number;
    breed:string;
}
~~~

在CatsController里使用

~~~ ts
import { Controller,Get,Post,Body } from '@nestjs/common';
import { CreateCatDto } from './dto/create-cat.dto';
import { CatsService } from './cats.service';
import { Cat } from './interfaces/cat.interface';

@Controller('cats')
export class CatsController {
    constructor(private catsService:CatsService) {}
    
    @Post()
    async create(@Body() creatCatDto:CreateCatDto) {
        this.catsService.create(createCatDto);
    }
    
    @Get()
    async findAll():Promise<Cat[]> {
        return this.catsService.findAll();
    }
}
~~~

CatsService 是通过构造函数注入的。

## 依赖注入

~~~ ts
constructor(private readonly catesService:CatsService) {}
~~~

## 作用域

Provider 通常具有与应用程序生命周期同步的生命周期。

## 可选提供者

如果一个类依赖于一个配置对象，但没有传递，应该使用默认值。

~~~ ts
import { Injectable,Optional,Inject } from '@nestjs/common';

@Injectable()
export class HttpService<T> {
    constructor(
    	 @Optional()
         @Inject('HTTP_OPTIONS')
          private readonly httpClient:T
    ) {}
}
~~~

## 基于属性的注入

~~~ ts
import { Injectable,Inject } from '@nestjs/common';

@Injectable(){
    export class HttpService<T> {
        @Inject('HTTP_OPTIONS')
        private readonly httpClient:T;
    }
}
~~~

>   如果类没有扩展其他提供者，应该使用基于构造函数的注入

## 注册提供者

app.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { CatsController } from './cats/cats.controller';
import { CatsService } from './cats/cats.service';

@Module(({
    controllers:[CatsController],
    providers:[CatsService],
}))
export class AppModule {}
~~~

