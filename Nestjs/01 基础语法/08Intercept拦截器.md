[toc]

# Intercept 拦截器

拦截器是使用`@Injectable()`装饰器注解的类。拦截器应该实现`NestInterceptor`接口。

*   在函数执行之前/之后绑定额外的数据
*   转换从函数返回的结果
*   转换从函数抛出的异常
*   扩展基本函数行为
*   根据所选条件完全重写函数(例如，缓存目的)

## 基础

每个拦截器都有`intercept()`方法，它接收两个参数。

1.   ExecutionContext
2.   CallHandler

## 截取切面

使用拦截器在函数执行之前或者之后添加额外的逻辑。

logging.interceptor.ts

~~~ ts
import { Injectable,NestInterceptor,ExecutionContext,CallHandler } from '@nestjs/common';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class LoggingInterceptor implements NestInterceptor {
    intercept(context:ExecutionContext,next:CallHandler): Observable<any> {
        console.log('Befor...');
        
        const now Date.now();
        return next.handle().pipe(
        	tap(()=> console.log(`After...${Date.now() - now } ms`))
        )
    }
}
~~~

## 绑定拦截器

#### 传递类

~~~ ts
@UseInterceptors(LoggingInterceptor)
export class CatsController{}
~~~

#### 传递实例

~~~ ts
@UseInterceptors(new LoggingInterceptor())
export class CatsController {}
~~~

### 全局拦截器

~~~ ts
const app = await NestFectory.create(ApplicationModule);
app.useGlobalInterceptors(new LoggingInterceptor());
~~~

无法注入依赖，因为不属于任何模块。为了解决这个问题，可以绑定app模块

app.module.ts

~~~ ts
import { Module } from '@nestjs/common';
import { APP_INTERCEPTOR } from '@nestjs/core';

@Module({
    providers: [
        {
            provide:APP_INTERCEPTOR,
            useClass:LoggingInterceptor,
        }
    ]
})
export class AppModule {}
~~~

## 响应映射

因为`handle()`返回一个`Observable`。此流包含从路由处理程序返回的值，因此，可以使用`map()`运算符对其进行改变。

>   响应映射功能不适用于特定于库的响应策略(禁止直接使用`@Res()`对象)

创建一个`TransformInterceptor`，将打包响应并分配给data属性

transform.interceptor.ts

~~~ ts
import { Injectable,NestInterceptor,ExecutionContext,CallHandler } from '@nestjs/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface Response<T> {
    data:T;
}

@Injectable() 
export class TransformInterceptor<T> implements NestInterceptor<T,Response<T>> {
    intercept(context:ExecutionContext,nest:CallHandler):Observable<Response<T>> {
        return next.handler().pipe(map(data=>({data})))
    }
}
~~~

将每一个`null`转换为`’‘`空字符串。

~~~ ts
import { Injectable,NestInterceptor,ExecutionContext,CallHandler } from '@nestjs/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class ExcludeNullInterceptor implements NestInterceptor {
    intercept(context:ExecutionContext,next:CallHandler):Observable<any> {
        return next.handler().pipe(map(value => value===null ? '' : value));
    }
}
~~~

## catchError 异常映射

exception.interceptor.ts

~~~ ts
import {
    Injectable,
    NestInterceptor,
    ExecutionContext,
    BadGatewayException,
    CallHandler,
} from '@nestjs/common';
import { Observable,throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ErrorsInterceptor implements NestInterceptor {
    intercept(context: ExecutionContext,next:CallHandler): Observable<any>{
        return next.handle().pipe(
        	catchError(err => throwError(new BadGatewayException()));
        )
    }
}
~~~

## Stream 重写

阻止调用处理程序并返回不同的值

例如, 由于性能问题而从缓存中获取

```typescript
import { Injectable, NestInterceptor, ExecutionContext, CallHandler } from '@nestjs/common';
import { Observable, of } from 'rxjs';

@Injectable()
export class CacheInterceptor implements NestInterceptor {
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    const isCached = true;
    if (isCached) {
      return of([]);
    }
    return next.handle();
  }
}
```

这是一个 `CacheInterceptor`，带有硬编码的 `isCached` 变量和硬编码的响应 `[]` 。我们在这里通过 `of` 运算符创建并返回了一个新的流, 因此路由处理程序**根本不会被调用**。

## RxJS更多操作符

处理路由请求超时

```typescript
import { Injectable, NestInterceptor, ExecutionContext, CallHandler, RequestTimeoutException } from '@nestjs/common';
import { Observable, throwError, TimeoutError } from 'rxjs';
import { catchError, timeout } from 'rxjs/operators';

@Injectable()
export class TimeoutInterceptor implements NestInterceptor {
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    return next.handle().pipe(
      timeout(5000),
      catchError(err => {
        if (err instanceof TimeoutError) {
          return throwError(new RequestTimeoutException());
        }
        return throwError(err);
      }),
    );
  };
};
```

5秒后，请求处理将被取消。还可以在抛出之前添加自定义逻辑`RequestTimeoutException`（例如，释放资源）。