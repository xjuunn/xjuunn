[toc]

# Vue脚手架

## 脚手架的配置

~~~ shell
npm install -g @vue/cli  #全局安装脚手架
vue ceate xxxx  # 创建脚手架
npm run serve   # 启动项目
~~~

## 配置

定义 vue.config.js

~~~ js
module.exports = {
    pages: {
        index: {
            entry: 'src/main.js',
        },
    },
    lintOnSeve:false  //关闭语法检查
}
~~~



## ref 获取真实的DOM

用来给元素或子组件注册引用信息

~~~ html
<h1 ref="title"></h1>
~~~

~~~ js
console.log(this.$refs.title)//this是vm
~~~

>   给组件ref获取到的是组件的实例对象vc

## props

让组件接收外部传进来的数据

~~~ html
<!--传递数据-->
<Student name="xxxx"></Student>
~~~

~~~ json
//接收
props:['name']
//接收并类型限制
props:{
    name:Number
}
//接收并限制类型,必要性和指定默认值
props:{
    name:{
        type:String,//类型
        required:true,//必要性
        default:'张三'//默认值  
        //默认值和必要性不能同时设置
    }
}
~~~

props是只读的,如果确实要修改,请复制props的内容到data中,然后修改data中的数据.

## mixin 混入

多个组件公用的配置,取成一个混入对象

重复的data会使用原有的,生命周期钩子会同时使用,mixin中的生命周期钩子会先执行

#### 定义混入  mixin.js

~~~ js
export const hunhe = {
    methods:{
        showName(){
            alert(this.name)
        }
    }
    mounted(){
        console.log("test")
    }
}
~~~

#### 组件中引入

~~~ js
import {hunhe} from '../mixin'
//配置中
mixins:[hunhe]
~~~

>   全局混入
>
>   Vue.mixin(xxx)  会为每一个节点配置

## 插件

#### 插件定义 plugins.js

~~~ js
export default {
    install(Vue){
       	Vue.filter...
        Vue.directive...
        Vue.mixin...
        Vue.prototype.hello = ()>{...}
    }
}
~~~

#### 插件使用

~~~ js
import plugins from './plugins'
Vue.use(plugins);
~~~

## scoped

给组件的style配置scoped属性,作用域限制在组件内,防止冲突