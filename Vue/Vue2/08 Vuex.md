

# Vuex 组件间数据共享

## 环境搭建

~~~ js
// main.js
import Vuex from 'vuex'
import store from './store/index.js'
const app = new Vue({
  ...App,
  store
})
~~~

~~~ js
//store/index.js
import Vue from 'vue'
import Vuex from 'vuex'
const actions = {};//响应组件中的动作
const mutations = {};//操作数据
const state = {};//储存数据
Vue.use(Vuex)
export default new Vuex.Store({
	actions,
	mutations,
	state
})
~~~



## 使用

~~~ vue
<template>
	<view>
		<h2 class="num">{{this.$store.state.sum}}</h2>
		<button type="default" @click="jia">加</button>
		<button type="default" @click="jian">减</button>
		<button type="default" @click="cheng">乘</button>
	</view>
</template>

<script>
	export default { 
		data(){
			return{
				num:2
			}
		},
		methods:{
			jia(){
				this.$store.dispatch("jia",this.num)
			},
			jian(){
				this.$store.dispatch("jian",this.num)
			},
			cheng(){
				// this.$store.dispatch("cheng",this.num)
				this.$store.commit("CHENG",this.num)
			}
		},
	}
</script>
<style scoped>
	button{
		margin: 10px;
	}
	.num{
		text-align: center;
		padding: 30px;
	}
</style>
~~~

~~~ js
import Vue from 'vue'
import Vuex from 'vuex'
const actions = {
	jia(context,value){
		context.commit("JIA",value);
	},
	jian(context,value){
		context.commit("JIAN",value);
	},
	cheng(context,value){
		context.commit("CHENG",value);
	}
};
const mutations = {
	JIA(state,value){
		state.sum +=value;
	},
	JIAN(state,value){
		state.sum -= value; 
	},
	CHENG(state,value){
		state.sum *= value;
	}
	
};
const state = {
	sum :0
};
Vue.use(Vuex)
export default new Vuex.Store({
	actions,
	mutations,
	state
})

~~~

## getters

当state中的数据需要经过加工后再使用，可以使用getters加工

~~~ js
const state = {
	sum :0
};
const getters = {
	bigsum(state){
		console.log(state.sum)
		return state.sum*10;
	}
}
export default new Vuex.Store({
	actions,
	mutations,
	state,
	getters
})

//使用		<h2 class="num">{{this.$store.getters.bigsum}}</h2>

~~~

## vuex 自动生成计算属性

~~~ json
//原生
computed:{
    sum(){
        return this.$store.state.sum
    }，
    school(){
        return this.$store.state.school
    },
    subject(){
        return this.$store.state.subject
    }
}
//借助mapState申城计算属性，从state中读取数据（对象写法）
import {mapState,mapGetters} from 'vuex'
computed:{
    ...mapState({sum:'sum',school:'school',subject:'subject'})
}
//借助mapState生成计算属性，从state中读取数据（数组写法）
computed:{
    ...mapState(['sum','school','subject'])
}

~~~

> getters写法相同，借助mapGetters

## 自动执行commit

~~~ json
// 原生写法
method:{
    increment(){
        tis.$store.commit('JIA',this.n);
    },
    decrement(){
        this.$store.commit('JIAN',this.n);
    }
}
// 借助mapMutations生成对应的方法，方法中会调用commit去联系mutations 对象写法
import {mapMutations} from 'vuex'
...mapMutations({increment:'JIA',decrement:'JIAN'})
// 数组写法
...mapMutations(['JIA','JIAN'])
...mapActions(['jia',jian]);
~~~





