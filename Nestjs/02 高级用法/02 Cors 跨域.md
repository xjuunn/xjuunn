# 使用Cors解决跨域问题

~~~ shell
npm install cors 
npm install @types/cors -D
~~~



main.ts

~~~ ts
import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import * as cors from 'cors'; // 引入cors

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.use(cors()); // 使用 放在所有use的第一个
  await app.listen(3000);
}
bootstrap();

~~~

