[toc]

# 模块 Module

模块是具有`@Module()`装饰的类。

每一个Nest应用程序至少有一个模块，即根模块。根模块是Nest开始安排应用程序树的地方。

`@Module()`装饰器接模块属性的对象

| providers   | 由Nest注入器实例化的提供者，并且可以至少在整个模块中共享 |
| ----------- | -------------------------------------------------------- |
| controllers | 必须创建的一组控制器                                     |
| imports     | 导入模块的列表，这些模块导出了此模块中所需提供者         |
| exports     | 由本模块提供并应在其他模块中可用的提供者的子集           |

## 功能模块

`CatsController` 和 `CatsService` 属于同一个应用程序域。应考虑将他们移动到一个功能模块下。

cats/cats.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { CatsController } from './cats.controller';
import { CatsService } from './cats.service';
@Module({
    controllers: [CatsController],
    providers: [CatsService],
})
export class ClassModule{}
~~~

>   使用cli创建模块
>
>   ~~~ shell
>   nest g module cats
>   ~~~

将这个模块导入根模块 `(ApplicationModule)`

app.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { CatsModule } from './cats/cats.module';
@Module({
    imports:[CatsModule],
})
export class ApplicationMoudle{}
~~~

项目结构

```text
src
├──cats
│    ├──dto
│    │   └──create-cat.dto.ts
│    ├──interfaces
│    │     └──cat.interface.ts
│    ├─cats.service.ts
│    ├─cats.controller.ts
│    └──cats.module.ts
├──app.module.ts
└──main.ts
```

## 共享模块

在Nest中，默认情况下，模块是**单例**，因此，可以在多个模块之间共享同一个提供者实例。

~~~ ts
import { Module } from '@nestjs/common';
import { CatsController } from './cats.controller';
import { CatsService } from './cats.service';

@Module({
    controllers:[CatsController],
    providers:[CatsService],
    exports:[CatsService]
})
export class CatsModule {}
~~~

现在，每一个导入`CatsModule`的模块都可以访问`CatsService`，并且他们将共享相同的`CatsService`实例。

## exports 模块导出

模块可以导出他们内部的提供者。可以导出自己导入的模块。

~~~ ts
@Module({
    imporats:[CommonMoudle],
    exports:[CommonModule],
})
export class CoreModule{}
~~~

## 依赖注入

提供者也可以注入到模块(类)中（例如，用于配置目的）

~~~ ts
import { Module } from '@nestjs/common';
import { CatsController } from './cats.controller';
import { CatsService } from './cats.service';

@Module({
    controllers:[CatsController],
    providers:[CatsService],
})
export class CatsModule{
    constructor(provate readonly catsService:CatsService){}
}
~~~

>   由于循环依赖性，模块类不能注入到提供者中。

## Global 全局模块

~~~ ts
import { Module,Global } from '@nestjs/common';
import { CatsController } from './cats.controller';
import { CatsService } from './cts.service';

@Global()
@Module({
    controllers:[CatsController],
    providers:[CatsService],
    exports:[CatsService],
})
export class CatsModule{}
~~~

`@Global` 装饰器使模块成为全局作用域。全局模块应该只注册一次，最好由根或者核心模块注册。

在上面的例子中，`CatsService`自建将无处不在，想要使用`CatsService`的模块在不需要在imports数组中导入`CatsModule`

## DynamicModule 动态模块

~~~ ts
import { Module,DynamicModule } from '@nestjs/common';
import { createDatabaseProviders } from './database.providers';
import { Connectrion } from './connection.provider';

@Module({
    providers:[Connection],
})
export class DatabaseModule{
    static forRoot(entities = [],options?):DynamicModule{
        const providers = createDatabaseProviders(options,entities);
        return {
            module:DatabaseModule,
            providers:providers,
            export:providers
        };
    }
}
~~~

>   `forRoot()`可以同步或异步`Promise`返回动态模块
>
>   动态模块返回的属性扩展（而不是覆盖）.

如果要在全局范围内注册动态模块，将`global`属性设置为true

~~~ json
{
    global:true,
    module:DatabaseModule,
    providers:providers,
    exports:providers,
}
~~~

>   但是将所有的内容全局化不是一个好的决策

使用动态模块

~~~ ts
import { Module } from '@nestjs/common';
import { DatabaseModule } from './database/datebase.module';
import { User } from './users/entities/user.entity';

@Module({
    imports:[DatabaseModule.forRoot([User])],
})
export class AppModule{}
~~~

如果要一次重新导出动态模块，则可以在导出数组中省略方法调用。

~~~ ts
import { Module } from '@nestjs/common';
import { DatabaseModule } from './database/database.module';
import { User } from './users/entities/user.entity';

@Module({
    imports:[DatabaseModule.forRoot([User])],
    exports:[DatabaseModule],
})
export class AppModule{}
~~~

