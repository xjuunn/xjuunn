# Vue组件通信

## 全局事件总线

任意组件间通信

### 方法1

~~~ js
const Demo = Vue.extend({})
const d = new Demo();
Vue.prototype.x = d;
~~~

~~~ js
supclick(){
    this.x.$on('getnum',(num)=>{
        console.log(num);
	})
}
~~~

~~~ js
methods:{
	oncomclick(){
		this.x.$emit('getnum',222);
	}
}
~~~



## 方法2

~~~ js
const app = new Vue({
  el:'#app',
  beforCreate(){
	  Vue.prototype.$bus = this;
  }
})
~~~

## 消息订阅与发布

pubsub.js库



