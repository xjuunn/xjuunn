# Nestjs

Nestjs 是一个用于构建高效、可扩展的Nodejs服务器端应用的框架。

## 安装和创建项目

~~~ shell
npm i -g @nestjs/cli
nest new project-name
~~~

```
src
 ├── app.controller.spec.ts
 ├── app.controller.ts
 ├── app.module.ts
 ├── app.service.ts
 └── main.ts
```

*   app.controller.ts 带有单个路由的基本控制器示例
*   app.controller.spec.ts 对于基本控制器的单元测试样例
*   app.module.ts 应用程序的根模块
*   app.service.ts 带有单个方法的基本服务
*   main.ts 程序的入口文件。使用`NestFactory`来创建Nest应用实例

~~~ ts
// main.ts
import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(3000);
}
bootstrap();
~~~

## 启动项目

~~~ shell
npm run start
~~~

启动热加载的服务器

~~~ shell
npm run start:dev
~~~

