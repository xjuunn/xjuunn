[toc]

## vue 创建项目

~~~ shell
vue create name
~~~

*   名称不能使用大写
*   vscode 代码无色，安装插件 Vetur

#### 关闭Eslint

文件vue.config.js添加

~~~ js
lintOnSave:false
~~~

## 安装 element-puls

~~~ shell
npm install element-puls --save
~~~

~~~ js
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
createApp(App)
.use(ElementPlus)
.mount('#app')
~~~

## Vue3

### 组合式 API setup

<kbd><script setup></kbd> 就像直接写JS代码。

自动导入顶层变量/函数，自动导入组件，形式更自由、灵活。

### 响应式 ref() reactive()

#### ref()

~~~ vue
<template>
{{ a }} 直接使用，不用a.value vue会自动解包拿到value
</template>
<script setup>
import { ref } from 'vue'
let a = ref(0);
function test() {
  a.value++;
}
</script>
~~~

#### reactive()

>   reactive()的值必须是一个对象，否则不具有响应式！
>
>   ref()的本质也是reactive()
>
>   ~~~ js
>   reactive({
>       value:1
>   })
>   ~~~

~~~ vue
<template>
<el-button @click="test">test</el-button> {{ a }}
</template>
<script setup>
import { reactive, ref } from "vue";
let a = reactive({
    name:"test",
    age:20,
});
function test(){
    a.age++; // 不用 a.value
    console.log(a.age);
}
</script>
~~~

### 计算属性 computed()

~~~ vue
<template>
  <div class="box">
    <el-button @click="addBooks">按钮</el-button><br />
    书的个数：{{ booksCount }} <br />
    <span> {{ txt }} </span>
  </div>
</template>
<script setup>
import { computed, ref } from "vue";
let booksCount = ref(1);
function addBooks() {booksCount.value++;}
let txt = computed(() => {
  if (booksCount.value < 2) return "一点";
  else if (booksCount.value < 5) return "还行";
  else return "很多";
});
</script>
~~~

计算属性会被缓存，只有响应式依赖更新时才会重新计算。这意味着，booksCount不改变，无论txt被使用多少次，都是使用之前的值，不用重新计算。

### 生命周期

![组件生命周期图示](https://cn.vuejs.org/assets/lifecycle_zh-CN.W0MNXI0C.png)

~~~ js
import { onMounted } from "vue";
onMounted(()=>{
    console.log("onMounted");
})
~~~

### 侦听器 watch

~~~ vue
<template>
  <el-button @click="test">测试</el-button> {{ i }}
</template>

<script setup>
import { ref, watch } from "vue";
let i = ref(1);
function test (){
    i.value++;
}
watch(i,(newValue,oldValue)=>{
    console.log(newValue,oldValue);
})
</script>
~~~

### 模板引用 ref

~~~ vue
<template>
  <span ref="test">test</span>
</template>
<script setup>
import { onMounted, ref } from "vue";
let test = ref(null);
onMounted(() => {
  console.log(test.value);
});
</script>
~~~

#### 获取DOM的数组

~~~ vue
<template>
  <ul>
    <li ref="itemlist" v-for="item in listdata" :key="item">{{ item }}</li>
  </ul>
</template>
<script setup>
import { onMounted, ref } from "vue";
let listdata = ref([1, 2, 3, 4, 5]);
let itemlist = ref(null);
onMounted(()=>{
    itemlist.value.forEach(item => {
        console.log(item);
    });
})
</script>
~~~

### 组件

test.vue

~~~ vue
<template>
组件
</template>
~~~

App.vue

~~~ vue
<template>
<testVue></testVue>
</template>
<script setup>
//setup 组合式编程 引入组件，就可以直接用
import testVue from "./components/test.vue";
</script>
~~~

### Props

test.vue

~~~ vue
<template>
{{ title }}
</template>
<script setup>
defineProps(["title"])
</script>
~~~

App.vue

~~~ vue
<template>
<testVue title="标题"></testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
</script>
~~~

### 事件

test.vue

~~~ vue
<template>
  <el-button @click="onButtonClick">子组件的按钮</el-button>
</template>
<script setup>
const emit = defineEmits(["onSubmit"]);
function onButtonClick() {
  emit("onSubmit");
}
</script>
~~~

App.vue

~~~ vue
<template>
  <testVue @on-submit="submit()"></testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
function submit() {
  console.log("提交");
}
</script>
~~~

子组件使用<kbd>\$emit</kbd>方法可以省略Script。test.vue

~~~ vue
<template>
  <el-button @click="$emit('onSubmit')">子组件的按钮</el-button>
</template>
~~~

#### emit 传值

test.vue

~~~ vue
<template>
<el-button @click="$emit('test',33)">按钮</el-button>
</template>
~~~

App.vue

~~~ vue
<template>
<testVue @test="test"></testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
function test(i){
    console.log("test",i);
}
</script>
~~~

### 插槽

test.vue

~~~ vue
<template>
  <div>页头</div>
  <div>
    <slot>默认内容</slot>
  </div>
  <div>页尾</div>
</template>
~~~

App.vue

~~~ vue
<template>
  <testVue>内容</testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
</script>
~~~

#### 具名插槽

test.vue

~~~ vue
<template>
    标题：<slot name="title">标题</slot> <br>
    内容：<slot name="content">内容</slot> <br>
    页脚：<slot name="footer">页脚</slot>
</template>
~~~

App.vue

~~~ vue
<template>
  <testVue>
    <template v-slot:title> title </template>
    <template v-slot:content> content </template>
    <template v-slot:footer> footer </template>
  </testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
</script>
~~~

#### 作用域插槽

test.vue

~~~ vue
<template>
    <slot :txt="txt"></slot>
</template>
<script setup>
let txt = "子组件的数据";
</script>
~~~

App.vue   ==v-slot="data"==

~~~ vue
<template>
  <testVue v-slot="data">
    父组件获取到了子组件的值：{{ data }}
  </testVue> <br/>
  <testVue v-slot="{txt}">
    直接解构出数据： {{ txt }}
  </testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
</script>
~~~

#### 具名作用域插槽

test.vue

~~~ vue
<template>
    <slot name="test" :txt="txt"></slot>
</template>
<script setup>
let txt = "子组件的数据";
</script>
~~~

App.vue ==#test="data"==

~~~ vue
<template>
  <testVue>
    <template #test="data"> 父组件获取到了子组件的值：{{ data }} </template>
  </testVue>
  <br />
  <testVue>
    <template #test="{ txt }"> 直接解构出数据： {{ txt }} </template>
  </testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
</script>
~~~

### 透传

“透传 attribute”指的是传递给一个组件，却没有被该组件声明为 [props](https://cn.vuejs.org/guide/components/props.html) 或 [emits](https://cn.vuejs.org/guide/components/events.html#defining-custom-events) 的 attribute 或者 `v-on` 事件监听器。最常见的例子就是 `class`、`style` 和 `id`。

test.vue

~~~ vue
<template>
<el-button>test</el-button>
</template>
~~~

App.vue

~~~ vue
<template>
  <testVue @click="test"></testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
function test(){
    console.log('test');
}
</script>
~~~

多个跟节点透传，使用<kbd>v-bind:”$attrs”</kbd>显示绑定

~~~ vue
<template>
<el-button>test</el-button>
<el-button v-bind="$attrs">test2</el-button>
<el-button>test3</el-button>
</template>
~~~

在JS中使用 attrs

test.vue

~~~ vue
<template>test</template>
<script setup>
import { useAttrs } from 'vue';
const attrs = useAttrs();
attrs.onTest();  // 函数名是父组件中的事件名前加“on”，事件名首字母大写。
</script>
~~~

App.vue

~~~ vue
<template>
  <testVue @Test="test"></testVue>
</template>
<script setup>
import testVue from "./components/test.vue";
function test(){
    console.log('test');
}
</script>
~~~

#### 禁用透传

~~~ js
defineOptions({
    inheritAttrs:false,
})
~~~

### 依赖注入 provide inject

适用于多层级组件间传递数据

App.vue 父组件提供数据 provide

~~~ vue
<template>
  <test></test>
</template>
<script setup>
import { provide } from "vue";
import test from "./components/test.vue";
let data = 20;
provide("data", data);
</script>
~~~

test.vue 子组件 注入数据 inject

~~~ vue
<script setup>
import { inject } from "vue";
let data = inject("data");
console.log(data)
</script>
~~~

inject 设置默认值 

~~~ js
let data = inject("data","null");
~~~

如果不想让子组件修改父组件的数据时，可以用<kbd>readonly()</kbd>来把数据设置成只读数据。

~~~ js
provide("data", readonly(data));
~~~

---

### 组合式函数 hook

src目录下创建<kbd>hooks</kbd>文件夹，里面用来存放所有的hook文件

test.js

~~~ js
let i = 0;
export function useTest(){
    i++;
    return i;
}
~~~

App.vue

~~~ vue
<template>
  父组件：<el-button @click="addi">按钮</el-button>
  <br>
  <test></test>
</template>

<script setup>
import test from './components/test.vue';
import {useTest} from './hooks/test';
function addi(){
  console.log("按钮被点击了"+useTest()+"次");;
}
</script>
~~~

### 自定义指令

~~~ vue
<template>
  <span v-test>test</span>
</template>
<script setup>
const vTest = {
  mounted:(el)=>{
    console.log(el);
  }
}
</script>
~~~

#### 所有参数

~~~ vue
<template>
  <span v-test="a">test</span>
</template>
<script setup>
let a = "传递的值"
const vTest = {
  mounted:(el,binding,vnode,preVnode)=>{
    console.log(el,binding,vnode,preVnode);
  }
}
</script>
~~~

-   `el`：指令绑定到的元素。这可以用于直接操作 DOM。
-   `binding`：一个对象，包含以下属性。
    -   `value`：传递给指令的值。例如在 `v-my-directive="1 + 1"` 中，值是 `2`。
    -   `oldValue`：之前的值，仅在 `beforeUpdate` 和 `updated` 中可用。无论值是否更改，它都可用。
    -   `arg`：传递给指令的参数 (如果有的话)。例如在 `v-my-directive:foo` 中，参数是 `"foo"`。
    -   `modifiers`：一个包含修饰符的对象 (如果有的话)。例如在 `v-my-directive.foo.bar` 中，修饰符对象是 `{ foo: true, bar: true }`。
    -   `instance`：使用该指令的组件实例。
    -   `dir`：指令的定义对象。
-   `vnode`：代表绑定元素的底层 VNode。
-   `prevNode`：代表之前的渲染中指令所绑定元素的 VNode。仅在 `beforeUpdate` 和 `updated` 钩子中可用。

#### 所有钩子

~~~ js
const myDirective = {
  // 在绑定元素的 attribute 前
  // 或事件监听器应用前调用
  created(el, binding, vnode, prevVnode) {
    // 下面会介绍各个参数的细节
  },
  // 在元素被插入到 DOM 前调用
  beforeMount(el, binding, vnode, prevVnode) {},
  // 在绑定元素的父组件
  // 及他自己的所有子节点都挂载完成后调用
  mounted(el, binding, vnode, prevVnode) {},
  // 绑定元素的父组件更新前调用
  beforeUpdate(el, binding, vnode, prevVnode) {},
  // 在绑定元素的父组件
  // 及他自己的所有子节点都更新后调用
  updated(el, binding, vnode, prevVnode) {},
  // 绑定元素的父组件卸载前调用
  beforeUnmount(el, binding, vnode, prevVnode) {},
  // 绑定元素的父组件卸载后调用
  unmounted(el, binding, vnode, prevVnode) {}
}
~~~

#### 简写形式

~~~ js
const vTest = (el,binding)=>{
  // 在 'mounted' 和 'updated' 时都调用
  console.log(el,binding);
}
~~~

### 插件

在src下创建 <kbd>plugins</kbd> 插件文件夹
test.js

~~~ js
export default {
  install: (app, options) => {
    app.config.globalProperties.$test = (args) => {
      return "接收到的args: " + args;
    };
  },
};
~~~

App.vue

~~~ vue
<template>
  <span>{{ $test("test") }}</span>
</template>
~~~



## 路由 Router

在 <kbd>src/router/index.js</kbd> 中添加配置

~~~ json
{
    path: "/test",
    name: "test3",
    component: () => import("../components/test.vue"),
  },
  {
    path: "/test",
    name: "test2",
    component: () => import("../components/test2.vue"),
  },
~~~

#### 使用

router-link

~~~ html
  <router-view></router-view> <br>
  <router-link to="/test3">跳转</router-link>
~~~

js

~~~ js
import { useRouter } from "vue-router";
let router = useRouter();
router.push("/test")
~~~

#### 传参

query传参    /test?id=1&name=名字

~~~ js
import { useRouter } from "vue-router";
let router = useRouter();
function test() {
  router.push({
    path:"/test",
    query:{
      id:1,
      name:"名字"
    }
  })
}
~~~

组件接收参数
~~~ js
import { useRoute } from "vue-router";
let route = useRoute();
console.log(route.query);
~~~

params 传参  /test/2

配置<kbd>src/router/index.js</kbd>

~~~ json
{
    path: "/test/:id",
    name: "test",
    component: () => import("../components/test.vue"),
},
~~~

使用
~~~ js
router.push("/test/2")
// 或者
router.push({
    params:{
      id:99
    }
  })
~~~

接收

~~~ js
import { onMounted, onUpdated} from "vue";
import { useRoute } from "vue-router";
let route = useRoute();
onUpdated(() => {
  console.log(route.params);
});
onMounted(()=>{
    console.log(route.params)
})
~~~

## 网络请求框架 axios

### get请求

~~~ js
onMounted(()=>{
  getdata();
})
async function getdata(){
  let {data} = await axios.get('http://localhost:7788')
  console.log(data);
}
~~~

post 请求传递query参数
~~~ js
onMounted(()=>{
  getdata();
})
async function getdata(){
  let {data} = await axios.post('http://localhost:7788/getUser?id=2')
  console.log(data);
}
~~~

post请求传递params参数

~~~ js
onMounted(()=>{
  getdata();
})
async function getdata(){
  let {data} = await axios.post('http://localhost:7788/getUser2/2')
  console.log(data);
}
~~~

post请求传递body参数

~~~ js
onMounted(() => {
  getdata();
});
async function getdata() {
  let { data } = await axios.post("http://localhost:7788/getUser3", {
    id: 2,
  });
  console.log(data);
}
~~~

## Element-Plus 组件

### Button 按钮

##### Type

*   primary
*   success
*   info
*   warning
*   danger

##### 样式

*   Default 默认样式
*   plain 半透明背景
*   round 胶囊
*   icon 图标  <kbd>:icon=“Edit”</kbd>

~~~ html
 <el-button type="danger">Danger</el-button>
 <el-button type="primary" :icon="Edit" circle />
~~~

### Input 输入框

使用<kbd>v-model</kbd>绑定数据。

~~~ html
 <el-input v-model="input" disabled placeholder="禁用的按钮"/>
~~~

*   disabled 禁用
*   clearable 显示清空按钮
*   placeholder 提示文本
*   show-password 密码框
*   size 尺寸
    *   large
    *   small

### Select 下拉菜单

~~~ vue
<el-select v-model="value" class="m-2" placeholder="Select" size="large">
  <el-option
    v-for="item in options"
    :key="item.value"
    :label="item.label"
    :value="item.value"
  />
</el-select>
~~~

### Form 表单

~~~ html
<template>
  <el-form inline label-width="120px">
    <el-form-item label="昵称">
      <el-input placeholder="请输入"></el-input>
    </el-form-item>
    <el-form-item label="出生地">
      <el-input placeholder="请输入"></el-input>
    </el-form-item>
    <el-form-item label="下拉框" style="width: 310px;">
      <el-select placeholder="请选择">
        <el-option value="option1" label="选项1">
          选项1
        </el-option>
        <el-option value="option1" label="选项2">
          选项2
        </el-option>
        <el-option value="option1" label="选项3">
          选项2
        </el-option>
      </el-select>
    </el-form-item>
  </el-form>
</template>
~~~

### Table 表格

~~~ html
<el-table :data="tableData" style="width: 100%">
    <el-table-column prop="date" label="Date" width="180" />
    <el-table-column prop="name" label="Name" width="180" />
    <el-table-column prop="address" label="Address" />
 </el-table>
~~~

*   data 要绑定的数据
*   border 显示边框

*   prop 绑定的数据名
*   label 表头显示的文本

#### 要修改某一项数据是，使用<kbd>scope</kbd>拿到本行数据

~~~ html
<template #default="scope">
        <el-button size="small" type="primary" @click="update(scope)">修改</el-button>
        <el-button size="small" type="danger" @click="del(scope)">删除</el-button>
</template>
~~~



### Dialog 对话框

~~~ html
<el-dialog title="标题" v-model="showupdate">   <!-- showupdate 控制对话框是否显示 -->
    <!-- 内容 -->
    <template #footer>   <!-- 对话框底部插槽 -->
      <!-- 底部 -->
    </template>
  </el-dialog>
~~~

### Message 消息提示

~~~ js
 ElMessage({
    message: '消息',
    type: 'warning',
  })
~~~

### Messageu Box 消息弹出框

~~~ js
ElMessageBox.confirm("你确定要删除吗？",{
    confirmButtonText:"确定",
    cancelButtonText:"取消",
  }).then((e)=>{
    tableData.value.splice(rowindex,1);
  }).catch((e)=>{   })
~~~

## 简单应用

~~~ vue
<template>
  <el-table :data="tableData" border>
    <el-table-column prop="id" label="ID" width="60"></el-table-column>
    <el-table-column prop="name" label="姓名"></el-table-column>
    <el-table-column prop="sex" label="性别" :formatter="sexformatter" width="60"></el-table-column>
    <el-table-column prop="phone" label="电话"></el-table-column>
    <el-table-column label="操作" width="150" align="center">
      <template #default="scope">
        <el-button size="small" type="primary" @click="update(scope)">修改</el-button>
        <el-button size="small" type="danger" @click="del(scope)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog title="修改用户" v-model="showupdate"> 
    <el-form v-model="rowdata" label-width="60" inline align="center">
      <el-form-item label="id">
        <el-input placeholder="id" v-model="rowdata.id" disabled suffix-icon="xxxx"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input placeholder="姓名" v-model="rowdata.name" suffix-icon="edit"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select placeholder="选择性别" v-model="rowdata.sex">
          <el-option :value="0" label="男">男</el-option>
          <el-option :value="1" label="女">女</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电话">
        <el-input placeholder="电话" v-model="rowdata.phone" suffix-icon="xxxx"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button type="primary" @click="doupdate">修改</el-button>
      <el-button @click="()=>showupdate = false">取消</el-button>
    </template>
  </el-dialog>
</template>
<script setup>
import { ElMessageBox } from "element-plus";
import { ref } from "vue";
let tableData = ref([
  {
    id: "1",
    name: "Tom",
    sex:0,
    phone: "15043335432",
  },
  {
    id: "2",
    name: "Bob",
    sex:0,
    phone: "15043354323",
  },
  {
    id: "3",
    name: "LiHua",
    sex:0,
    phone: "15034335432",
  },
  {
    id: "4",
    name: "Jim",
    sex:1,
    phone: "15043000323",
  },
]);
let showupdate = ref(false);
let rowdata = ref(null);
let rowindex = 0;
function update(row) {
  showupdate.value = true;
  rowdata.value = Object.assign({},row.row);
  rowindex = row.$index;
}
function del(row) {
  ElMessageBox.confirm("你确定要删除吗？",{
    confirmButtonText:"确定",
    cancelButtonText:"取消",
  }).then((e)=>{
	// 网络请求 ，修改数据库数据
  }).catch((e)=>{   })
}
function sexformatter(row,column){
  if(row.sex == '0') return "男";
  else if(row.sex =='1') return "女";
  else return '未知';
}
function doupdate(){
  // 网络请求 ，修改数据库数据
  tableData.value[rowindex] = rowdata.value;
  showupdate.value = false
}
</script>
~~~
