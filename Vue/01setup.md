# setup

> Vue3中的一个新的配置项，值为一个函数

setup是所有`composition Api`(组合API)的**‘表演舞台’**。
组件中所用到的数据，方法等，均要配置在setup中。

setup有两种返回值

1. 返回一个对象，则对象中的属性，方法，在模板中可以直接使用]
2. *返回一个渲染函数，可以自定义渲染内容*

> setup尽量不要和vue2配置混用
>
> setup不能是一个async函数，因为返回值不再是一个return对象，而是promise，模板看不到return对象中的属性

~~~ vue
<template>
    <h1>{{ name }}</h1>
    <button @click="dowork">工作</button>
</template>
<script>
export default{
  name:'App',
  setup(){
    let name = 'zhangsan';
    let age = 19;
    function dowork(){
      console.log('工作');
    }
    return{
      name,
      age,
      dowork
    }
  }
}
</script>


~~~

