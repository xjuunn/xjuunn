[toc]

<img src="https://nitro.unjs.io/icon.svg" alt="icon" style="zoom:150%;" />

# Nitro

Nitro基于H3和许多内置功能构建Web服务器。

## 安装、运行和部署

~~~ shell
npx giget@latest nitro nitro-app --install
~~~

运行

~~~ shell
npm run dev
~~~

部署

~~~ shell
npm run build
~~~

预览

~~~ shell
npm run preview
~~~

## 目录结构

*   routers 路由文件，文件名是路由路径
*   api 带有api/前缀的路由
*   utils 自动导入的应用实用程序
*   plugins 自定义的nitro插件
*   nitro.config.ts Nitro配置
*   tsconfig.json TypeScript 配置

## 服务器 Utils

>   自动导入

#### H3 Utils

utils/sum.ts

~~~ ts
export function useSum(a:number,b:number) {return a+b}
~~~

在文件直接使用，不用导入

router/index.ts

~~~ ts
export default defineEventHandler(()=>{
	const sum = useSum(1,3);
    return { sum };
})
~~~

#### Nitro Utils

内置的Nitro Util

*   defineCacheFunction(fn,options) / cachedFunction(fn,options)
*   defineCachedEventHandler(handler,options) / cachedEventHandler(handler,options)
*   defineRenderHandler(handler)
*   defineRouteMeta(options) *(实验性的)*
*   useRuntimeConfig(event?)
*   useAppConfig(event?)
*   useStorage(base?)
*   useNitroApp()
*   defineNitroPlugin(plugin)
*   nitroPlugin(plugin)
*   getRouteRules(event)

#### 手动导入

对于某些情况(IDE支持和库),不可能依赖自动导入

可以从虚拟文件中显式导入 <kbd>#imports</kbd>

~~~ ts
import { useStorage } from '#imports'
~~~

#### 异步上下文  （实验性）

将应用程序拆分为更小的“可组合”程序，并且可以直接评估到共享上下文(请求事件)，而无需传递它。

为了启用异步上下文功能，需要启动标志：<kbd>asyncContext</kbd>

nitro.config.ts

~~~ ts
export default defineNitroConfig({
    experimental: {
        asyncContext:true
    }
})
~~~

如果使用的是Nuxt： nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    nitro:{
		experimental:{
            asyncContext:true
        }
    }
})
~~~

启用后，可以在任何程序或组合项中使用(自动导入)访问请求事件，而无需手动传递它: <kbd>useEvent()</kbd>

没有异步上下文

~~~ ts
// routes/index.ts
export default defineEventHandler(async event=>{
    const user = await useAuth(event);
})

// utils/auth.ts
export function useAuth(event) {
    return useSession(event);
}
~~~

使用异步上下文

~~~ ts
// routes/index.ts
export default defineEventHandler(async ()=>{
    const user = await useAuth();
})

// utils/auth.ts
export function useAuth() {
    return useSession(useEvent())
}
~~~

## 服务器路由

Nitro 支持文件系统路由，自动将文件映射到h3路由。

### 事件处理程序

事件处理程序是一个函数，将绑定到路由，并在路由器与传入请求的路由匹配时执行。

### 文件系统路由

~~~ 
api/
	test.ts     /api/test
routes/
	hello.get.ts      GET /hello
	hello.post.ts     POST /hello
nitro.config.ts
~~~

>   如果使用的nuxt，需要移动到server/api/ 和 server/routes 中。
>
>   一些提供商(如Vercel)使用顶级目录作为功能，因此，放置在其中的路由不起作用，只能将api放入routes/api/中。

#### 简单路由

在routes中创建一个文件，文件名是路由路径

/routes/test.ts

~~~ ts
export default defineEventHandler(()=>{
    return {hello:"hello"}
})
~~~

#### 带参数路由

##### 一个参数

/routes/hello/[name].ts

~~~ ts
export default defineEventHandler(event=>{
    const name = getRouterParam(event,'name');
    return name;
})
~~~

调用 /hello/test 将返回 test

##### 多个参数

/routes/[name]/[age].ts

~~~ ts
export default defineEventHandler(event=>{
    const name = getRouterParam(event,'name');
    const age = getRouterParam(event,'age');
    return { anme,age }
})
~~~

##### 捕获所有参数

/routes/[…name].ts

~~~ ts
export default defineEventHandler(event=>{
    const name = getRouterParam(Event,'name');
    return name;
})
~~~

调用 /hello/world/test 响应：hello/world/test

#### 请求方式

hello.post.ts

~~~ts
export default defineEventHandler(async event=>{
    const body = await readBody(event);
    return { updated:true }
})
~~~

#### 全部捕获路由

与任何其他路由不匹配的所有路由匹配

/routes/[…].ts

~~~ ts
export defalut defineEventHandler(event=>{
    const url = getRequestURL(event);
    return url;
})
~~~

#### 特定于环境的路由

>   只能在夜间频道使用

*   .dev
*   .prod
*   .prerender

/routes/test.get.dev.ts

/routes/test.get.prod.ts

## 中间件

Nitro中间件可以挂钩到请求的生命周期中

中间件在 <kbd>middleware/</kbd>中自动注册

### 简单的中间件

中间件的定义和路由的定义相同，但是中间件不返回任何内容。

server/middleware/auth.ts

~~~ ts
export default defineEventHandler(event=>{
    event.context.user = {name:"Nitro"}
})
~~~

目录中的中间件自动注册所有的路由。

### 路由 meta 元数据

可以生成时使用micro在事件处理程序文件中定义路由处理程序

>   只能在夜间模式中使用

/api/test.ts

~~~ ts
defineRouteMeta({
    openAPI:{
        tags:["test"],
        description:"Test route description",
        parametes:[{in:"query",name:'test',required:true}],
    }
})
export default defineEventHandler(()=>"OK");
~~~

>   此功能目前可以用于指定OpenAPI的元数据，[参阅swagger规范](https://swagger.io/specification/v3/)

### 执行顺序

中间件按目录列表顺序执行，中间件前加上一个数字，可以控制其执行顺序

1.logger.ts

>   执行顺序是按字符串的顺序排列的，所以1.test.ts执行完将会执行10.test.ts，为了避免这种情况，可以使用01.test.ts来作为文件名

### 请求筛选

中间件在每个请求上执行，应用自定义逻辑以将其分为限定为特定条件

middleware/auth.ts

~~~ ts
export default defineEventHandler(event=>{
    if(getRequestURL(event).pathname.startsWith('/auth')){
        event.context.user = {name:'Nitro'}
    }
})
~~~

### 路由规则

在每条路由的顶层添加逻辑，可以用于重定向、代理、缓存和向路由添加标头。

>   swr: true|number 是 cache: {swr: true,maxAge: number}的简便写法

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  routeRules: {
    '/blog/**': { swr: true },
    '/blog/**': { swr: 600 },
    '/blog/**': { static: true },
    '/blog/**': { cache: { /* cache options*/ } },
    '/assets/**': { headers: { 'cache-control': 's-maxage=0' } },
    '/api/v1/**': { cors: true, headers: { 'access-control-allow-methods': 'GET' } },
    '/old-page': { redirect: '/new-page' },
    '/old-page/**': { redirect: '/new-page/**' },
    '/proxy/example': { proxy: 'https://example.com' },
    '/proxy/**': { proxy: '/api/**' },
  }
})
~~~

next.config.ts

~~~ ts
export default defineNuxtConfig({
  routeRules: {
    '/blog/**': { swr: true },
    '/blog/**': { swr: 600 },
    '/blog/**': { static: true },
    '/blog/**': { cache: { /* cache options*/ } },
    '/assets/**': { headers: { 'cache-control': 's-maxage=0' } },
    '/api/v1/**': { cors: true, headers: { 'access-control-allow-methods': 'GET' } },
    '/old-page': { redirect: '/new-page' },
    '/old-page/**': { redirect: '/new-page/**' },
    '/proxy/example': { proxy: 'https://example.com' },
    '/proxy/**': { proxy: '/api/**' },
  }
})
~~~

## WebSocket

### 启用配置

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  experimental: {
    websocket: true
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    experimental: {
      websocket: true
    }
  }
})
~~~

#### websocket处理程序

~~~ ts
export default defineWebSocketHandler({
  open(peer) {
    console.log("[ws] open", peer);
  },

  message(peer, message) {
    console.log("[ws] message", peer, message);
    if (message.text().includes("ping")) {
      peer.send("pong");
    }
  },

  close(peer, event) {
    console.log("[ws] close", peer, event);
  },

  error(peer, error) {
    console.log("[ws] error", peer, error);
  },
});
~~~

#### 使用客户端连接服务器

~~~ ts
export default defineEventHandler(() => {
  return $fetch("https://raw.githubusercontent.com/unjs/crossws/main/examples/h3/public/index.html");
});
~~~

### 服务器发送事件(SSE)

~~~ ts
export default defineEventHandler(async (event) => {
  const eventStream = createEventStream(event)
  const interval = setInterval(async () => {
    await eventStream.push(`Message @ ${new Date().toLocaleTimeString()}`)
  }, 1000)
  eventStream.onClosed(async () => {
    clearInterval(interval)
    await eventStream.close()
  })
  return eventStream.send()
})
~~~

#### 客户端连接SSE

~~~ ts
const eventSource = new EventSource('http://localhost:3000/sse')
eventSource.onmessage = (event) => {
  console.log(event.data)
}
~~~

## KV存储

Nitro内置了`unjs/unstorage`，提供与运行时无关的持久层。

### 使用

~~~ ts
await useStorage().setItem("test:foo",{hello:"world"});
await useStorage().setItem("test:foo");
await useStorage('test').setItem("foo",{hello:"world"});
const dataStorage = useStorage("data").setItem("test","world");
await useStorage().getItem("data:test");
// 使用泛型定义类型
await useStorage<{hello:string}>("test").getItem("foo");
await useStorage("test").getItem<{hello:string}>('foo');
~~~

### 配置

可以使用配置挂在一个或多个自定义存储驱动。

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  storage: {
    redis: {
      driver: 'redis',
      /* redis connector options */
    },
    db: {
      driver: 'fs',
      base: './data/db'
    }
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    storage: {
      redis: {
        driver: 'redis',
        /* redis connector options */
      },
      db: {
        driver: 'fs',
        base: './.data/db'
      }
    }
  }
})
~~~

### 运行时配置

plugins/strage.ts

~~~ ts
import redisDriver from 'unstorage/drivers/redis'

export default defineNitroPlugin(() => {
  const storage = useStorage()

  // Dynamically pass in credentials from runtime configuration, or other sources
  const driver = redisDriver({
      base: 'redis',
      host: useRuntimeConfig().redis.host,
      port: useRuntimeConfig().redis.port,
      /* other redis connector options */
    })

  // Mount driver
  storage.mount('redis', driver)
})
~~~

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  runtimeConfig: {
    redis: { // Default values
      host: '',
      port: 0,
      /* other redis connector options */
    }
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  runtimeConfig: {
    redis: { // Default values
      host: '',
      port: 0,
      /* other redis connector options */
    }
  }
})
~~~

## SQL 数据库

默认连接使用的是`SQLite`预配置



### 启用

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  experimental: {
    database: true
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    experimental: {
      database: true
    }
  }
})
~~~

安装依赖

~~~ shell
npm i -D better-sqlite3
~~~

### 用法

~~~ ts
export default defineEventHandler(async () => {
  const db = useDatabase();

  // Create users table
  await db.sql`DROP TABLE IF EXISTS users`;
  await db.sql`CREATE TABLE IF NOT EXISTS users ("id" TEXT PRIMARY KEY, "firstName" TEXT, "lastName" TEXT, "email" TEXT)`;

  // Add a new user
  const userId = String(Math.round(Math.random() * 10_000));
  await db.sql`INSERT INTO users VALUES (${userId}, 'John', 'Doe', '')`;

  // Query for users
  const { rows } = await db.sql`SELECT * FROM users WHERE id = ${userId}`;

  return {
    rows,
  };
});
~~~

### 配置

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  database: {
    default: {
      connector: 'sqlite',
      options: { name: 'db' }
    },
    users: {
      connector: 'postgresql',
      url: 'postgresql://username:password@hostname:port/database_name'
    }
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    database: {
      default: {
        connector: 'sqlite',
        options: { name: 'db' }
      },
      users: {
        connector: 'postgresql',
        url: 'postgresql://username:password@hostname:port/database_name'
      }
    }
  }
})
~~~

## 缓存

### 缓存事件处理程序

routes/cached.ts

~~~ ts
export default defineCacheEventHandler(event=>{
    // handler
},{maxAge:60 * 60 }) // 一小时
~~~

### 缓存函数

对于缓存不是事件处理函数，但属于事件处理程序的函数结果以及在多个处理程序中重用。 `defineCachedFunction`

~~~ ts
// utils/github.ts
export cachedGHStars = defineCachedFunction(async (repo:string)+>{
    const data:any = await $fetch('https://api.github.com/repos/'+repo);
	return data.stargazers_count
},{
    maxAge: 60 * 60,
    name:'ghStars',
    getKey;(repo:string)=>repo
})
// api/stars/[...repo].ts
export default defineEventHandler(async event=>{
    const repo = event.context.params.repo;
    const stars = await cachedGHStar(repo).catch(()=0);
    return {repo,stars};
})
~~~

### Edge Workers

在Edge Workers线程中，示例在每次请求后都会被销毁。Nitro将响应发送到客户端时更新缓存时保持实例处于活跃状态

utils/github.ts

~~~ ts
import type { H3Event } from 'h3'

export const cachedGHStars = defineCachedFunction(async (event: H3Event, repo: string) => {
  const data: any = await $fetch(`https://api.github.com/repos/${repo}`)

  return data.stargazers_count
}, {
  maxAge: 60 * 60,
  name: 'ghStars',
  getKey: (event: H3Event, repo: string) => repo
})
~~~

api/stars/[…repo].ts

~~~ ts
export default defineEventHandler(async (event) => {
  const repo = event.context.params.repo
  const stars = await cachedGHStars(event, repo).catch(() => 0)
  return { repo, stars }
})
~~~

### 路由缓存

将所有的博客路由缓存1小时

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  routeRules: {
    "/blog/**": { cache: { maxAge: 60 * 60 } },
  },
});
~~~

nuxt.config.ts

~~~ ts
export default defineNitroConfig({
  routeRules: {
    "/blog/**": { cache: { maxAge: 60 * 60 } },
  },
});
~~~

自定义存储安装点

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  storage: {
    redis: {
      driver: "redis",
      url: "redis://localhost:6379",
    },
  },
  routeRules: {
    "/blog/**": { cache: { maxAge: 60 * 60, base: "redis" } },
  },
});
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    storage: {
      redis: {
        driver: "redis",
        url: "redis://localhost:6379",
      },
    },
  },
  routeRules: {
    "/blog/**": { cache: { maxAge: 60 * 60, base: "redis" } },
  },
});
~~~

### 自定义缓存存储

Nitro将数据存储在挂载点中

*   生产环境中，将默认使用内存驱动程序。
*   开发环境中，将使用文件系统驱动程序写入临时目录。

设置挂载点

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  storage: {
    cache: {
      driver: 'redis',
      /* redis connector options */
    }
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    storage: {
      cache: {
        driver: 'redis',
        /* redis connector options */
      }
    }
  }
})
~~~

#### 开发环境覆盖挂载点

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  devStorage: {
    cache: {
      driver: 'redis',
      /* redis connector options */
    }
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    devStorage: {
      cache: {
        driver: 'redis',
        /* redis connector options */
      }
    }
  }
})
~~~

### 缓存键和失效

缓存键使用以下模式生成

~~~ js
`${options.group}:${options.name}:${options.getKey(...args)}.json`
~~~

例如

~~~ ts
const getAccessToken = defineCachedFunction(() => {
  return String(Date.now())
}, {
  maxAge: 10,
  name: 'getAccessToken',
  getKey: () => 'default'
})
~~~

>   nitro:functions:getAccessToken:default.json

#### 失效

~~~ ts
await useStorage('cache').removeItem('nitro:functions:getAccessToken:default.json')

~~~

## Fetch

用于从服务器端或其他来源获取数据，基于`unjs/ofetch`。

### 用法

~~~ ts
export default defineEventHandler(async event=>{
    const data = await $fetch('https://ungh.cc/orgs/unjs/repos');
    return data;
})
~~~

传递类型来获得更好的类型判断

~~~ ts
import { Repo } from '~/types';
export default defineEventHandler(async event =>{
    const data = await $fetch<Repo[]>('https://ungh.cc/orgs/unjs/repos');
    return data;
})
~~~

### 选项

~~~ ts
import { Repo } from '~/types';
export default defineEventhandler(async event=>{
    const data = await $fetch<Repop[]>('https://api.github.com/markdown',{
        method:'POST',
        headers: {
			'Content-Type':'application/json'
        },
        body: {
            text:'hello **world**!'
        }
    })
    return data;
})
~~~

### 服务器内获取

可以使用`$fetch`向其他处理程序发出内部请求

~~~ ts
export default defineEventHandler(async event=>{
    const data = await $fetch('/api/users');
    return data;
})
~~~

>   实际上，没有发出任何请求，直接调用的处理程序。

## 资源

### 公共资源 public/

目录中所有的资源都将自动提供，可以直接从浏览器中访问，无需任何操作。

### 生产公共资源

在构建Nitro应用程序时，public/ 目录将被复制到.output/public/,并创建一个包含元数据的清单并将其嵌入到服务器的捆绑包中。

~~~ json
{
  "/image.png": {
    "type": "image/png",
    "etag": "\"4a0c-6utWq0Kbk5OqDmksYCa9XV8irnM\"",
    "mtime": "2023-03-04T21:39:45.086Z",
    "size": 18956
  },
  "/robots.txt": {
    "type": "text/plain; charset=utf-8",
    "etag": "\"8-hMqyDrA8fJ0R904zgEPs3L55Jls\"",
    "mtime": "2023-03-04T21:39:45.086Z",
    "size": 8
  },
  "/video.mp4": {
    "type": "video/mp4",
    "etag": "\"9b943-4UwfQXKUjPCesGPr6J5j7GzNYGU\"",
    "mtime": "2023-03-04T21:39:45.085Z",
    "size": 637251
  }
}
~~~

### 服务器资源

assets/目录中的所有资源都将添加到服务器的捆绑包中。生成应用程序后，可以在./output/server/chunks/raw/目录中找到。

可以由挂载点使用`assets:server`存储层

例如，可以将json文件存储在assets/data.json中，并在处理程序中检索它。

~~~ ts
export default defineEventHandler(async ()=>{
    const data = await useStorage('assets:server').getItem('data.json');
})
~~~

### 自定义服务器资源

从自定义目录添加资源

nitro.config.ts

~~~ ts
export default defineNitroConfig({
    serverAssets:[{
        baseName:'my_directory',
        dir:'./my_directory'
    }]
})
~~~

例如添加一个带有html模板的目录

nitro.config

~~~ ts
export default defineNitroConfig({
    serverAssets:[{
		baseName:'templates',
        dir:'./templates'
    }]
})
~~~

然后使用 asset:templates 库来检索资源。

handler/success.ts

~~~ ts
export default defineEventHandler(async event =>{
    return await useStorage('assets:template').getItem('success.html');
})
~~~

## Plugins

使用插件来扩展Nitro的运行时行为。

Nitro插件**执行一次**在服务器启动期间，用来扩展Nitro的运行时行为。接收上下文，可用于挂在到Nitro生命周期事件。

插件从plugins目录中自动注册，并在第壹次Nitro初始化时同步运行(按文件名顺序)。

plugins/test.ts

~~~ ts
export default defineNitroPlugin((nitroApp)=>{
    console.log("Nitro plugin",nitroApp);
})
~~~

如果在另一个目录中存在插件，可以使用以下配置

nitro.config.ts

~~~ts
export default defineNitroConfig({
    plugins:['my-plugins/hello.ts']
})
~~~

### Nitro 运行时钩子

~~~ ts
export default defineNitroPlugin(nitro =>{
    nitro.hooks.hook('close',async ()=>{
        
    })
})
~~~

#### 可用的钩子

*   close, ()=>{}
*   error, (error,{event?})=>{}
*   render:response, (response,{event})=>{}
*   request, {event}=>{}
*   beforeResponse, (event,{body})=>{}
*   afterResponse, (event,{body})=>{}

例子：使用插件捕获所有的程序错误。

~~~ ts
export default defineNitroPlugin(nitro=>{
    nitro.hooks.hook('error',async (error,{event})=>{
        console.error(event.paht,error);
    });
})
~~~

例子： 正常关闭，使用插件注册是个钩子，该钩子在Nitro关闭时解析。

~~~ ts
export default defineNitroPlugin(nitro=>{
    nitro.hooks.hookOnce('close',async ()=>{
        console.log("正在关闭Nitro服务器...");
        await new Promise(resolve=>setTimeout(resolve,500));
        console.log("任务结束！");
    })
})
~~~

### 请求和响应生命周期

~~~ ts
export default defineNitroPlugin((nitroApp) => {
  nitroApp.hooks.hook("request", (event) => {
    console.log("on request", event.path);
  });

  nitroApp.hooks.hook("beforeResponse", (event, { body }) => {
    console.log("on response", event.path, { body });
  });

  nitroApp.hooks.hook("afterResponse", (event, { body }) => {
    console.log("on after response", event.path, { body });
  });
});
~~~

### 渲染器响应

这仅适用于使用 renderer 定义的渲染处理程序，不会用于其他 api/服务器路由。在Nuxt中，这个钩子将被调用给服务器端渲染的页面

~~~ ts
export default defineNitroPlugin((nitro) => {
  nitro.hooks.hook('render:response', (response, { event }) => {
    // 。。。
    console.log(response)
  })
})

~~~

## 任务   :warning:(实验阶段)

[任务task](https://nitro.unjs.io/guide/tasks)

支持嵌套目录。任务名称将与 ： 联接。（示例: tasks/db/migrate.tstask name 将为 db：migrate）

### 启用

nitro.config.ts

~~~ ts
export default defineNitroConfig({
  experimental: {
    tasks: true
  }
})
~~~

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  nitro: {
    experimental: {
      tasks: true
    }
  }
})
~~~

### 定义任务

可以在文件中定义任务  tasks/[name].ts

支持嵌套目录。任务名称与目录一致  tasks/db/migrate.ts -> db:migrate

tasks/db/migrate.ts

~~~ ts
export default defineTask({
    meta: {
        name:'db:migrate',
        description:"运行数据库迁移"，
    }，
    run({payload,context}){
    	console.log('运行数据库迁移任务...');
    	return {result:"success"};
	}
})
~~~

### 计划任务

~~~ ts
export default defineNitroConfig({
    scheduledTasks: {
        '* * * * *':[cms:update] // 每分钟运行
    }
})
~~~

https://crontab.guru/

### 以编程的方式运行任务

~~~ ts
export default eventHandler(async event=>{
    const payload = {...getQuery(event)};
    const { result } = await runTask("db:migrate",{ payload });
    return { result };
})
~~~

### 使用开发服务器运行任务

#### 使用API路由

`/_nitro/tasks` 此端点返回可用任务名称及其元的列表。

~~~ json
// [GET] /_nitro/tasks
{
  "tasks": {
    "db:migrate": {
      "description": "Run database migrations"
    },
     "cms:update": {
      "description": "Update CMS content"
    }
  },
  "scheduledTasks": [
    {
      "cron": "* * * * *",
      "tasks": [
        "cms:update"
      ]
    }
  ]
}
~~~

`——nitro/tasks/:name`执行任务

tasks/echo/payload.ts

~~~ ts
export default defineTask({
    meta: {
        name: 'echo:payload',
        description:'返回提供的有效荷载 ，,
    },
    run({ payload,context }) {
        console.log("运行回显任务...");
        return { result:payload };
    }
})
~~~

### 使用CLI

只能在Dev Server 运行，在第二个中断运行命令

列出所有任务

~~~ shell
nitro task list
~~~

运行任务

~~~ shell
nitro task run db:migrate --payload "{}"
~~~





>   并发！
>
>   每一个任务可以由一个正在运行的实例，在多次调用同名任务时，结果只调用一次，所有调用的方法都将获得相同的返回值

## 配置

[参考](https://nitro.unjs.io/config)

nitro.config.ts

~~~ ts
expoft default defineNitroConfig({
	//  ... 
})
~~~

nuxt.config.ts

~~~ ts
expoft default defineNuxtConfig({
	nitro: {
        //  ...
    }
})
~~~

### 运行时配置

能够在运行时通过设置环境变量来更新配置。

~~~ ts
export default defineNitroConfit({
    runtimeConfig:{
		apiToken:"dev_token",
    }
})
~~~

代码中使用配置

~~~ ts
export default defineEventHandler(event=>{
    return useRuntimeConfig(event).apiToken // dev_token
})
~~~

### 本地开发

在根目录创建 .env 文件

~~~ ts
NITRO_API_TOKEN="123"
~~~

需要重启服务器

### 生产环境

所有变量都必须使用前缀NITRO_才能应用。将覆盖nitro.config.ts文件中定义的运行时配置变量

~~~ 
NITRO_API_TOKEN="123"  
~~~

>   Nuxt中使用前缀 NUXT_

在运行时配置中，使用camelCase定义键。在环境变量中，使用snake_case和大写字母定义键。

~~~ 
{
	helloWorld:'foo'
}

NITRO_HELLO_WORLD="foo"
~~~

## TypeScript

`tsconfig.json`

如果要在项目中使用类型提示，需要创建一个扩展自动生成类型的文件（默认存在）

~~~ json
{ 
	"extends":"./.nitro/types/tsconfig.json"
}
~~~

### 准备类型

可以使用命令自动生成类型。

~~~ shell
npx nitro prepare
~~~

## 夜间频道

夜间发布频道，每次提交到主分支时都会自动发布以尝试最新更改。

nitro

~~~ json
{
  "devDependencies": {
--    "nitropack": "^2.0.0"
++    "nitropack": "npm:nitropack-nightly@latest"
  }
}
~~~

nuxt

~~~ ts
{
  "devDependencies": {
--    "nuxt": "^3.0.0"
++    "nuxt": "npm:nuxt-nightly@latest"
  }
}
~~~

删除锁定文件（package-lock.json、yarn.lock、pnpm-lock.yaml 或 bun.lockb）并重新安装依赖项。
