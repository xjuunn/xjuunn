[toc]

<div style="text-align:center">
    <svg class="text-black dark:text-white block w-auto h-6" width="200" height="50" viewBox="0 0 800 200" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M377 200C379.16 200 381 198.209 381 196V103C381 103 386 112 395 127L434 194C435.785 197.74 439.744 200 443 200H470V50H443C441.202 50 439 51.4941 439 54V148L421 116L385 55C383.248 51.8912 379.479 50 376 50H350V200H377Z" fill="currentColor"></path><path d="M726 92H739C742.314 92 745 89.3137 745 86V60H773V92H800V116H773V159C773 169.5 778.057 174 787 174H800V200H783C759.948 200 745 185.071 745 160V116H726V92Z" fill="currentColor"></path><path d="M591 92V154C591 168.004 585.742 179.809 578 188C570.258 196.191 559.566 200 545 200C530.434 200 518.742 196.191 511 188C503.389 179.809 498 168.004 498 154V92H514C517.412 92 520.769 92.622 523 95C525.231 97.2459 526 98.5652 526 102V154C526 162.059 526.457 167.037 530 171C533.543 174.831 537.914 176 545 176C552.217 176 555.457 174.831 559 171C562.543 167.037 563 162.059 563 154V102C563 98.5652 563.769 96.378 566 94C567.96 91.9107 570.028 91.9599 573 92C573.411 92.0055 574.586 92 575 92H591Z" fill="currentColor"></path><path d="M676 144L710 92H684C680.723 92 677.812 93.1758 676 96L660 120L645 97C643.188 94.1758 639.277 92 636 92H611L645 143L608 200H634C637.25 200 640.182 196.787 642 194L660 167L679 195C680.818 197.787 683.75 200 687 200H713L676 144Z" fill="currentColor"></path><path d="M168 200H279C282.542 200 285.932 198.756 289 197C292.068 195.244 295.23 193.041 297 190C298.77 186.959 300.002 183.51 300 179.999C299.998 176.488 298.773 173.04 297 170.001L222 41C220.23 37.96 218.067 35.7552 215 34C211.933 32.2448 207.542 31 204 31C200.458 31 197.067 32.2448 194 34C190.933 35.7552 188.77 37.96 187 41L168 74L130 9.99764C128.228 6.95784 126.068 3.75491 123 2C119.932 0.245087 116.542 0 113 0C109.458 0 106.068 0.245087 103 2C99.9323 3.75491 96.7717 6.95784 95 9.99764L2 170.001C0.226979 173.04 0.00154312 176.488 1.90993e-06 179.999C-0.0015393 183.51 0.229648 186.959 2 190C3.77035 193.04 6.93245 195.244 10 197C13.0675 198.756 16.4578 200 20 200H90C117.737 200 137.925 187.558 152 164L186 105L204 74L259 168H186L168 200ZM89 168H40L113 42L150 105L125.491 147.725C116.144 163.01 105.488 168 89 168Z" fill="#00DC82"></path></svg>
</div>
# Nuxt

## 安装和运行

安装

~~~ shell
npx nuxi@latest init <project-name>
~~~

运行

~~~ shell
npm run dev
~~~

## 配置

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    // ...
})
~~~

### 环境覆盖

可以在nuxt.config中配置完全类型化的环境覆盖

~~~ ts
export default defineNuxtConfig({
  $production: {
    routeRules: {
      '/**': { isr: true }
    }
  },
  $development: {
    //
  }
})
~~~

### 环境变量和私有令牌

生成时不确定的变量。

`runtimeConfig`API将环境变量等值公开给应用程序，默认情况下，这些密钥仅在服务器端可以用。其中`runtimeConfig.public`的密钥也可以在客户端使用。

这些值可以在环境变量中定义，并且可以使用环境变量进行覆盖。

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    runtimeConfig:{
        apiSecret:"123",
        public: {
            apiBase:'/api'
        }
    }
})
~~~

.env

~~~
NUXT_APP_SECRET=api_secret_token  // 将会覆盖config中的值
~~~

这些变量将使用`useRuntimeConfig()`可组合

~~~ vue
<script>
	const runtimeConfig = useRuntimeConfig();
</script>
~~~

### 应用配置和约定

`app.config.ts`位与源目录中，用于公开可在生成时确定的公共变量。

app.config.ts

~~~ ts
export default defineAppConfig({
    title:'hello',
    theme:{
        dark:true,
        colors:{
            primary:"#ff0000"
        }
    }
})
~~~

~~~ vue
<script>
	const appConfig = useAppConfig();
</script>
~~~

[其他配置](https://nuxt.com/docs/getting-started/configuration)

### Views

*   app.vue 
*   组件 (components/)
*   页面 (pages/)
*   布局 (layouts/) [布局配置](https://nuxt.com/docs/guide/directory-structure/layouts)

#### 布局

##### 启用布局

通过添加<NuxtLayout>到app.vue

~~~ vue
<template>
	<NuxtLayout>
    	<NuxtPage/>
    </NuxtLayout>
</template>
~~~

要使用布局：

*   在网页中设置属性layout
*   设置 <NuxtLayout> 的 name 属性。

>   如果未指定布局，将使用layouts/default.vue
>
>   如果应用程序只有一个布局，建议使用app.vue
>
>   与其他组件不同，布局必须具有单个根元素以允许过渡，并且根元素不能为<slot/>

#### 默认布局

~/layouts/default.vue

~~~ vue
<tempalte>
	<div>
        <p>test</p>
        <slot/>
    </div>
</tempalte>
~~~

在布局文件中，页面的内容将显示在<slot/>中。

#### 布局命名

布局名就是布局文件名

~~~ vue
<script setup lang='ts'>
definePageMeta({
    layout:'custom'
})
</script>
~~~

或者使用<NuxtLayout>的name属性

~~~ vue
<script setup lang='ts'>
	const layout = 'custom';
</script>
<template>
	<NuxtLayout :name='layout'>
    	<NuxtPage/>
    </NuxtLayout>
</template>
~~~

如果布局位于嵌套目录中，则布局的名称将基于其自己的路径目录和文件名，并删除重复的段。

| 文件                              | 布局名称          |
| --------------------------------- | ----------------- |
| `~/layouts/desktop/default.vue`   | `desktop-default` |
| `~/layouts/desktop-base/base.vue` | `desktop-base`    |
| `~/layouts/desktop/index.vue`     | `desktop`         |

#### 动态更改布局

可以使用`setPageLayout`用于动态更改布局

~~~ vue
<script setup lang='ts'>
	function enableCustomLayout(){
        setPageLayout("custom")
    }
    definePageMeta({
        layout:false,
    })
</script>
<template>
	<div>
        <button @click=enableCustomLayout>update Layout</button>
    </div>
</template>
~~~

#### 基于每页覆盖布局

pages/index.vue

~~~ vue
<script setup lang="ts">
definePageMeta({
  layout: false,
})
</script>

<template>
  <div>
    <NuxtLayout name="custom">
      <template #header> Some header template content. </template>

      The rest of the page
    </NuxtLayout>
  </div>
</template>
~~~

layouts/custom.vue

~~~ vue
<template>
  <div>
    <header>
      <slot name="header">
        Default header content
      </slot>
    </header>
    <main>
      <slot />
    </main>
  </div>
</template>
~~~

### 扩展HTML模板

server/plugins/extend-html.ts

~~~ ts
export default defineNitroPlugin((nitroApp) => {
  nitroApp.hooks.hook('render:html', (html, { event }) => {
    console.log(html)
    html.head.push(`<meta name="description" content="My custom description" />`)
  })
  nitroApp.hooks.hook('render:response', (response, { event }) => { console.log(response) })
})

~~~

## 资源

Nuxt使用两个目录来处理样式表，字体或图像等资源

*   `public/`目录内容按照原样在服务器根目录下提供
*   `assets/`按照惯例，目录包含希望构建的工具(vite或wegbpack)处理的每个资源

### public/

`public/`目录作为静态资源公共服务器，这些资源在应用程序的自定义URL上公开可用。

例如，引用`public/img/`目录中的图像文件`img/nuxt.png`可以在静态URL上找到

~~~ vue
<template>
	<img src="/img/nuxt.png" alt="test"/>
</template>
~~~

### 资源目录

使用`assets/`目录来存储样式表、字体或SVG，但此目录没有自动扫描功能，可以为其使用任何其他名称

可以使用`~/assets/`来引用`assets/`目录

~~~ vue
<template>
	<img src="~/assets/img/nuxt.png" alt="test"/>
</template>
~~~

>   无法在静态资源中访问`/assets/`

### 全局样式引入

使用sass   assets/_colors.sass

~~~ scss
$primary: #49240F
$secondary: #E4A79D
~~~

nuxt.config

~~~ ts
export default defineNuxtConfig({
    vite: {
        css: {
            preprocessorOptions: {
                sass: {
                    additionalData:'@use "~/assets/_colors.sass" as *\n'
                }
            }
        }
    }
})
~~~

## 样式

### 本地样式

放置在`assets/`目录

### 在组件导入

~~~ vue
<script>
	import '~/assets/css/first.css';  // 静态导入
    import('~/assets/css/first.css');  // 动态导入
</script>
<style>
	@import url('~/assets/css/second.css')
</style>
~~~

>   样式表将内嵌在Nuxt呈现的HTML中

### 使用配置引入

将样式表放入`asstes/`目录中，可以在配置中引用其路径，Nuxt会自动将其包含在所有页面中

~~~ ts
export default defineNuxtConfig({
	css: ['~/assets/css/main.css']
})
~~~

>   全局注入，包含在所有页面中

### 使用字体

将字体放在`~/public/`目录中，使用`~/public/fonts`来引用他们

~~~ css
@font-face {
  font-family: 'FarAwayGalaxy';
  src: url('/fonts/FarAwayGalaxy.woff') format('woff');
  font-weight: normal;
  font-style: normal;
  font-display: swap;
}
~~~

使用

~~~ css
<style>
h1 {
  font-family: 'FarAwayGalaxy', sans-serif;
}
</style>
~~~

### 使用npm分发的样式表

~~~ shell
npm install animate.css
~~~

直接在页面中使用

~~~ vue
<script>
	import 'animate.css'
</script>
<style>
	@import url('animate.css')
</style>
~~~

也可以作为配置引入

~~~ ts
export default defineNuxtConfig({
    css: ['animate.css']
})
~~~

### 外部样式表

通过在nuxt.config文件的head部分添加link元素，可以在应用程序中包含外部样式表。

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    app: {
        head: {
            link: [{rel: 'stylesheet',href:"https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"}]
        }
    }
})
~~~

### 动态添加样式表

可以使用useHead可组合项在代码中动态设置head中的值

~~~ ts
useHead({
    link: [{rel:'stylesheet',href:"https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"}]
})
~~~

### 使用Nitro插件修改渲染的头部

`~/server/plugins/my-plugin.ts`

~~~ ts
export default defineNitroPlugin((nitro)=>{
    nitro.hooks.hook('reder:html',(html)=>{
        html.head.push('<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">')
    })
})
~~~

### 使用预处理器

~~~ shell
npm install -D sass
~~~

编写样式的位置是`assets`，可以使用预处理器的语法将元文件导入到app.vue中

~~~ vue
<style lang="scss">
	@use "~/assets/scss/main.scss";
</style>
~~~

或者可以使用nuxt配置的属性

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    css: ["~/assets/scss/main.scss"]
})
~~~

如果需要在预处理文件中注入代码，例如带有颜色的变量，可以使用vite预处理器选项来实现

assets/_colors.sass

~~~scss
$primary: #49240F
$secondary: #E4A79D
~~~

nuxt.config 

~~~ ts
export default defineNuxtConfig({
  vite: {
    css: {
      preprocessorOptions: {
        sass: {
          additionalData: '@use "~/assets/_colors.sass" as *\n'
        }
      }
    }
  }
})
~~~

## 路由

Nuxt文件系统为`pages/`目录中的每个文件创建一个路由。

### 导航

pages/app.vue

~~~vue
<template>
  <header>
    <nav>
      <ul>
        <li><NuxtLink to="/about">About</NuxtLink></li>
        <li><NuxtLink to="/posts/1">Post 1</NuxtLink></li>
        <li><NuxtLink to="/posts/2">Post 2</NuxtLink></li>
      </ul>
    </nav>
  </header>
</template>
~~~

### 路由参数

使用`useRoute()`选项可以在Vue组件的快或方法中使用，可以访问当前路由的详细信息

pages/posts/[id].vue

~~~ vue
<script setup lang="ts">
const route = useRoute()
console.log(route.params.id)
</script>
~~~

### 路由中间件

路由中间件由三种

1.   匿名(内联)路由中间件，直接在使用他们的页面中定义。
2.   命名路由中间件，它们放置在`middleware/`目录中，并在页面上使用时通过异步导入自动加载。（路由中间件名称已规范化为大小写，因此 someMiddleware 变成了 some-middleware）
3.   全局路由中间件，它们放置在`middleware/`目录，带`.global`后缀，每次路由更改都会调用

保护页面的中间件示例

middleware/auth.ts

~~~ ts
export default defineNuxtRouteMiddleware((to,form)=>{
    if(isAuthenticated() === false) {
        return navigateTo('/login');
    }
})
~~~

### 路由验证

pages/posts/[id].vue

~~~ vue
<script setup lang='ts'>
	definePageMeta({
        validate: async router => {
            return typeof route.params.id === 'string' &&  /^\d+$/.test(route.params.id)
        }
    })
</script>
~~~

## SEO 和 Meta

### 默认

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  app: {
    head: {
      charset: 'utf-8',
      viewport: 'width=device-width, initial-scale=1',
    }
  }
})
~~~

### useHead

~~~ vue
<script setup lang="ts">
useHead({
  title: 'My App',
  meta: [
    { name: 'description', content: 'My amazing site.' }
  ],
  bodyAttrs: {
    class: 'test'
  },
  script: [ { innerHTML: 'console.log(\'Hello world\')' } ]
})
</script>
~~~

### useSeoMeta

~~~ vue
<script setup lang="ts">
useSeoMeta({
  title: 'My Amazing Site',
  ogTitle: 'My Amazing Site',
  description: 'This is my amazing site, let me tell you all about it.',
  ogDescription: 'This is my amazing site, let me tell you all about it.',
  ogImage: 'https://example.com/image.png',
  twitterCard: 'summary_large_image',
})
</script>
~~~

### 组件

Nuxt提供`Title`、`Base`、`NoScript`、`Style`、`Meta`、`Link`、`Body`、`Html`、`Head`组件，一遍可以直接与组件中的元数据进行交互

由于这些组件的名称与本机元素匹配，因此必须在模板中将其大写

>   `<Head>`接收嵌套标记(出于美观原因)，但这不会影响嵌套的元标记在最终的HTML中的呈现位置

~~~vue
<script setup lang="ts">
const title = ref('Hello World')
</script>

<template>
  <div>
    <Head>
      <Title>{{ title }}</Title>
      <Meta name="description" :content="title" />
      <Style type="text/css" children="body { background-color: green; }" ></Style>
    </Head>

    <h1>{{ title }}</h1>
  </div>
</template>
~~~

### 类型

~~~ ts
interface MetaObject {
  title?: string
  titleTemplate?: string | ((title?: string) => string)
  templateParams?: Record<string, string | Record<string, string>>
  base?: Base
  link?: Link[]
  meta?: Meta[]
  style?: Style[]
  script?: Script[]
  noscript?: Noscript[];
  htmlAttrs?: HtmlAttributes;
  bodyAttrs?: BodyAttributes;
}
~~~

### 特征

#### 响应式

通过提供计算值，getter，或者响应对象，所有属性都支持响应式。

~~~ vue
<script setup lang="ts">
const description = ref('My amazing site.')

useHead({
  meta: [
    { name: 'description', content: description }
  ],
})
</script>
~~~

### 标题模板

可以使用该选项提供动态模板，用于自定义网站的标题。

~~~ vue
<script setup lang="ts">
useHead({
  titleTemplate: (titleChunk) => {
    return titleChunk ? `${titleChunk} - Site Title` : 'Site Title';
  }
})
</script>
~~~

### 正文标签

将标签追加到末尾

~~~ vue
<script setup lang="ts">
useHead({
  script: [
    {
      src: 'https://third-party-script.com',
      // valid options are: 'head' | 'bodyClose' | 'bodyOpen'
      tagPosition: 'bodyClose'
    }
  ]
})
</script>
~~~

## 过渡

使用vue或原生浏览器view transitions在页面和布局之间应用过渡

>   Nuxt利用vue的transition组件，用于在页面和布局之间的过渡

### 页面过渡

启用对所有页面的过渡 nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    app:{
        pageTransition:{name:'page',mode:'out-in'}
    }
})
~~~

在页面之间添加过渡，需要将以下css添加到app.vue中

~~~ vue
<template>
	<NuxtPage/>
</template>
<style>
	.page-enter-active,
    .page-leave-active {
        transition:all 0.4s;
    }
	.page-enter-from,
    .page-leave-to {
        opacity:0;
        filter:blur(1rem);
    }
</style>
~~~

为页面设置不同的过渡

about.vue

~~~ vue
<script setup lang='ts'>
	definePageMeta({
        pageTransition:{
            name:'rotate'
        }
    })
</script>
~~~

app.vue

~~~ vue
<template>
	<NuxtPage/>
</template>
<style>
.rotate-enter-active,
    .rotate-leave-active {
        transition:all 0.4s;
    }
    .rotate-enter-from,
    .rotate-leave-to {
        opacity:0;
        transform:rotate3d(1,1,1,15deg)
    }
</style>
~~~

### 布局过渡

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    app:{
        layoutTransition:{name:'layout',mode:'out-in'}
    }
})
~~~

app.vue

~~~vue
<templage>
	<NuxtLayout>
    	<NuxtPage/>
    </NuxtLayout>
</templage>
<style>
	.layout-enter-active,
    .layout-leave-active{
        transition:all 0.4;
    }
    .layout-enter-from,
    .layout-leave-to {
        filter:grayscale(1);
    }
</style>
~~~

应用于页面组件

~~~ vue
<script setup lang='ts'>
	definePageMeta({
        layout:'orange',
        layoutTransition:{
            name:'slide-in'
        }
    })
</script>
~~~

### 全局设置

可以使用 nuxt.config 全局自定义这些默认转换名称。
pageTransition 和 layoutTransition 都接受 TransitionProps 作为 JSON 可序列化值，可以在其中传递自定义 CSS 过渡的name、mode和其他有效的过渡道具。

nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
  app: {
    pageTransition: {
      name: 'fade',
      mode: 'out-in' // default
    },
    layoutTransition: {
      name: 'slide',
      mode: 'out-in' // default
    }
  }
})
~~~

要覆盖全局过渡属性，定义单个页面的页面或布局。

~~~ vue
<script setup lang='ts'>
	definePageMeta({
        pageTransition: {
            name:'bounce',
            mode:'out-in'
        }
    })
</script>
~~~

### 禁用过渡

~~~ vue
<script setup lang='ts'>
definePageMeta({
    pageTransition:false,
    layoutTransition:false,
})
</script>
~~~

或者全局在nuxt.config.ts

~~~ ts
export default defineNuxtConfig({
    app:{
        pageTransition:false,
        layouTransition:false,
    }
})
~~~

### JavaScript 钩子

~~~ vue
<script setup lang='ts'>
	definePageMeta({
        pageTransition:{
            name:'custom-flip',
            mode:'out-in',
            onBeforeEnter:(el)=>{
                console.log('Before enter')
            },
            onEnter:(el,done) => {},
            onAfterEnter:(el) => {}
        }
    })
</script>
~~~

### 动态转换

可以利用内敛中间件将不同的转换名称分配给`to.meta.pageTransition`

pages/[id].vue

~~~ vue
<script setup lang="ts">
definePageMeta({
  pageTransition: {
    name: 'slide-right',
    mode: 'out-in'
  },
  middleware (to, from) {
    if (to.meta.pageTransition && typeof to.meta.pageTransition !== 'boolean')
      to.meta.pageTransition.name = +to.params.id! > +from.params.id! ? 'slide-left' : 'slide-right'
  }
})
</script>

<template>
  <h1>#{{ $route.params.id }}</h1>
</template>

<style>
.slide-left-enter-active,
.slide-left-leave-active,
.slide-right-enter-active,
.slide-right-leave-active {
  transition: all 0.2s;
}
.slide-left-enter-from {
  opacity: 0;
  transform: translate(50px, 0);
}
.slide-left-leave-to {
  opacity: 0;
  transform: translate(-50px, 0);
}
.slide-right-enter-from {
  opacity: 0;
  transform: translate(-50px, 0);
}
.slide-right-leave-to {
  opacity: 0;
  transform: translate(50px, 0);
}
</style>
~~~

layouts/default.vue

~~~ vue
<script setup lang="ts">
const route = useRoute()
const id = computed(() => Number(route.params.id || 1))
const prev = computed(() => '/' + (id.value - 1))
const next = computed(() => '/' + (id.value + 1))
</script>

<template>
  <div>
    <slot />
    <div v-if="$route.params.id">
      <NuxtLink :to="prev">⬅️</NuxtLink> |
      <NuxtLink :to="next">➡️</NuxtLink>
    </div>
  </div>
</template>
~~~

### 使用NuxtPage进行转换

~~~ vue
<template>
	<div>
        <NuxtLayout>
    		<NuxtPage :transition="{
                                   name:'bounce',
                                   mode:'out-in'
                                   }"
    	</NuxtLayout>
    </div>
</template>
~~~

## 数据获取

Nuxt带有两个可组合文件和一个内置库，用于在浏览器或服务器环境中执行数据获取。`useFetch` `useAsyncData` `$fetch`

*   useFetch 在组件设置函数中处理数据获取的最直接方法。
*   \$fetch 适合根据用户交互发出网络请求。
*   useAsyncData 与\$fetch结合使用，可以提供更精细的控制。

### useFetch

~~~ vue
<script setup lang='ts'>
	const { data:count } await useFetch('/api/count')
</script>
<template>
	<p>page visits: {{ count }}</p>
</template>
~~~

### $fetch

~~~ vue
<script setup lang='ts'>
	async function addTodo(){
        const todo = await $fetch('/api/todos',{
            method:'POST',
            body:{
                //  data
            }
        })
    }
</script>
~~~

### useAsyncData

负责包装异步逻辑，并在解析后返回结果

在某些情况下，使用`useFetch`可组合项不合适，例如：当CMS或第三方提供自己的查询层时。这种情况下，可以使用`useAsyncData`来包装，同时可以保留可组合项提供的好处

~~~ vue
<script setup lang='ts'>
	const { data,error } = await useAsyncData('user',()=>{ myGetFunction('user')});
    const { data,error } = await useAsyncData(()=>{myGetFunctions('user')})
</script>
~~~

~~~ vue
<script setup lang="ts">
const { id } = useRoute().params

const { data, error } = await useAsyncData(`user:${id}`, () => {
  return myGetFunction('users', { id })
})
</script>
~~~

可组合项时包装并等待多个请求完成，然后处理结果的好方法

~~~ vue
<script setup lang="ts">
const { data: discounts, pending } = await useAsyncData('cart-discount', async () => {
  const [coupons, offers] = await Promise.all([
    $fetch('/cart/coupons'),
    $fetch('/cart/offers')
  ])

  return { coupons, offers }
})
</script>
~~~

### 返回值

useFetch和useAsync都有以下返回值

*   data 传入的异步函数的结果
*   pending 指示是否仍在提取数据的布尔值
*   refresh / execute 可用于刷新函数返回值的函数 在handler函数
*   clear 可用于将数据设置为未定义，将错误设置为 null，将挂起设置为 false，将状态设置为空闲，并将任何当前挂起的请求标记为取消
*   status 指示数据请求状态的字符串  idle、pending、success、error

## 状态管理

Nuxt 提供[`useState`](https://nuxt.com/docs/api/composables/use-state)可组合，用于跨组件创建响应式且对 SSR 友好的共享状态。

### 例子

#### 基本用法

~~~ vue
<script setup lang="ts">
const counter = useState('counter', () => Math.round(Math.random() * 1000))
</script>
<template>
  <div>
    Counter: {{ counter }}
    <button @click="counter++">
      +
    </button>
    <button @click="counter--">
      -
    </button>
  </div>
</template>
~~~

### 初始化状态

大多数情况下，需要使用异步解析的数据来初始化状态。可以石笋app.vue组件与`callOnce`来执行此操作

~~~ vue
<script setup lang="ts">
const websiteConfig = useState('config')

await callOnce(async () => {
  websiteConfig.value = await $fetch('https://my-cms.com/api/website-config')
})
</script>
~~~

### 与Pinia一起使用

~~~ shell
npx nuxi@latest module add pinia
~~~

stores/website.ts

~~~ ts
export const useWebsiteStore = defineStore('websiteStore', {
  state: () => ({
    name: '',
    description: ''
  }),
  actions: {
    async fetch() {
      const infos = await $fetch('https://api.nuxt.com/modules/pinia')

      this.name = infos.name
      this.description = infos.description
    }
  }
})
~~~

app.vue

~~~vue
<script setup lang="ts">
const website = useWebsiteStore()

await callOnce(website.fetch)
</script>

<template>
  <main>
    <h1>{{ website.name }}</h1>
    <p>{{ website.description }}</p>
  </main>
</template>
~~~

### 高级用法

composables/locale.ts

~~~ ts
import type { Ref } from 'vue'

export const useLocale = () => {
  return useState<string>('locale', () => useDefaultLocale().value)
}

export const useDefaultLocale = (fallback = 'en-US') => {
  const locale = ref(fallback)
  if (import.meta.server) {
    const reqLocale = useRequestHeaders()['accept-language']?.split(',')[0]
    if (reqLocale) {
      locale.value = reqLocale
    }
  } else if (import.meta.client) {
    const navLang = navigator.language
    if (navLang) {
      locale.value = navLang
    }
  }
  return locale
}

export const useLocales = () => {
  const locale = useLocale()
  const locales = ref([
    'en-US',
    'en-GB',
    ...
    'ja-JP-u-ca-japanese'
  ])
  if (!locales.value.includes(locale.value)) {
    locales.value.unshift(locale.value)
  }
  return locales
}

export const useLocaleDate = (date: Ref<Date> | Date, locale = useLocale()) => {
  return computed(() => new Intl.DateTimeFormat(locale.value, { dateStyle: 'full' }).format(unref(date)))
}
~~~

app.vue

~~~ vue
<script setup lang="ts">
const locales = useLocales()
const locale = useLocale()
const date = useLocaleDate(new Date('2016-10-26'))
</script>

<template>
  <div>
    <h1>Nuxt birthday</h1>
    <p>{{ date }}</p>
    <label for="locale-chooser">Preview a different locale</label>
    <select id="locale-chooser" v-model="locale">
      <option v-for="locale of locales" :key="locale" :value="locale">
        {{ locale }}
      </option>
    </select>
  </div>
</template>
~~~

### 连接数据库

~/server/utils/mysql.ts

~~~ ts
import mysql, { RowDataPacket, FieldPacket } from 'mysql2/promise';

const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '123123',
  database: 'test',
});

export const query = async <T>(sql: string, values?: any[]): Promise<T[]> => {
  const [rows]: [RowDataPacket[], FieldPacket[]] = await pool.execute(sql, values);
  return rows as T[];
};

export const close = async () => {
  await pool.end();
};
~~~

~/server/api/test

~~~ ts
import { query } from "../utils/mysql";

type User = {
  id: number,
  name: string,
  age: number,
}

export default defineEventHandler(async (event) => {
  try {
    const users = await query<User>('SELECT * FROM user');
    return users[0].age;
  } catch (error) {
    console.error('Error querying the database:', error);
    return { error: 'Error querying the database' };
  }
});
~~~

