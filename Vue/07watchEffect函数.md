# watchEffect函数

不用指明监视哪个属性，监视的回调中用到哪个属性，就监视哪个属性。

watchEffect有点像computed，但computed注重计算出来的值，所以必须要写返回值，而watchEffect更注重过程，不用写返回值。

~~~ js
//watchEffect所指定的回调中用到的数据只要发生变化，则直接重新执行回调。
watchEffect(()=>{
    const x1 = sum.value;
    const x2 = person.age;
    console.log("watchEffect配置的回调执行了")
})
~~~

