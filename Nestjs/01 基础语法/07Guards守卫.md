[toc]

# 守卫

守卫是一个使用`@Injectable()`装饰器的类。守卫应该实现`CanActivate`接口。

守卫会根据运行时出现的某些条件(例如权限，角色，访问控制列表等)来确定给定的请求是否由路由器处理程序处理。这通常成为**授权**。

>   守卫在每个中间件执行之后执行，但在任何拦截器或管道之前执行。

## 授权守卫

只有当调用者(通常是经过身份验证的特定用户)具有足够的权限时，特定的路由才可用。它将提取和验证`token`，并使用提取的信息来确定请求是否可以继续。

auth.guard.ts

~~~ ts
import { Injectable,CanActivate,ExecutionContext } from '@nestjs/common';
import { Observable } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate {
    canActivate(
    	context:ExecutionContext,
    ): boolean | Promise<boolean> | Observable<boolean> {
            const request = context.switchToHttp().getRequest();
            return validateRequest(request);
        }
}
~~~

*   返回true，将处理用户的调用
*   返回false，忽略用户的请求

## 基于角色认证

只允许具有特定角色的用户访问。

先从最基础的构建，目前允许所有请求通过

roles.guard.ts

~~~ ts
import { Injectable, CanActivate, ExecutionContext } from '@nestjs/common';
import { Observable } from 'rxjs';

@Injectable()
export class RolesGuard implements CanActivate {
    canActivate(
    	context:ExcutionContext,
    ): boolean | Promise<boolean> | Obsevable<boolean> {
            return true
        }
}
~~~

## 绑定路由

使用`@UseGuards()`装饰器，可以使用单个参数，也可以使用逗号分隔的参数列表。

~~~ ts
@Controller('cats')
@UseGuards(RoldsGuard)
export class CatsController {}
~~~

也可以传递一个实例

~~~ ts
@Controller('cats')
@UserGuards(new RolesGuard())
export class CatsController {}
~~~

上面的构造将守卫附加到此控制器的每个处理程序。如果希望只应用于单个方法，则使用方法级别的`@UseGuards()`装饰器。

## 全局守卫

~~~ ts
const app = await NestFactory.create(AppModule);
app.useGlobalGuards(new RolesGuard());
~~~

无法依赖注入，因为不属于任何模块。可以为每一个模块设置一个守卫

app.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { APP_GUARD } from '@nestjs/core';

@Module({
    providers:[{
        provide:APP_GUARD,
        useClass:RolesGuard,
    },],
})
export class AppModule {}
~~~

## 为每一个处理器设置角色

使用`@SetMetadata()`装饰器将定指的元数据附加到路由处理程序。

cats.controller.ts

~~~ ts
@Post()
@SetMetadata('roles',['admin'])
async create(@Body() createCatDto:CreateCatDto) {
    this.catsService.create(createCatDto);
}
~~~

将`roles`元数据(`roles`是一个键，而`['admin']`是一个特定的值)附加到`create()`方法。

创建自己的装饰器

roles.decorator.ts

~~~ ts
import { SetMetadata } from '@nestjs/common';
export const Roles = (...roles:string[]) => SetMetadata('roles',roles);
~~~

```typescript
@Post()
@Roles('admin')
async create(@Body() createCatDto: CreateCatDto) {
  this.catsService.create(createCatDto);
}
```

## 使用

roles.guard.ts

~~~ ts
import { Injectable,CanActivate,ExecutionContext } from '@nestjs/common';
import { Reflector } from '@nestjs/core';

@Injectable()
export class RolesGuard implements CanActivate {
    constructor(private reflector: Reflector) {}
	canActivate(context: ExecytionContext): boolean {
        const roles = this.reflector.get<string[]>(
        'roles',context.getHandler());
        if (!roles) {
            return true;
        }
        const request = context.switchToHttp().getRequest();
        const user = request.user;
        return matchRoles(roles,user.roles);
    }
}
~~~

