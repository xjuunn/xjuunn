# reactive

定义一个对象类型的响应式数据（基本类型用ref）

语法：const test  = reactive(tests); 接收一个对象或数组，返回一个代理对象（proxy对象）

reactive定义的响应式数据是深层次的。

内部基于ES6的Proxy实现，通过代理对象操作源对象数据进行操作

~~~ js
import {reactive} from 'vue';
export default{
    name:'App',
    setup(){
        let person = reactive({
            name:'张三'，
            age:19,
            job:{
            	type:'工程师',
            	salary:'30k'
        	}
        })
    }
    function whoarey(){
        person.name = '李四';
        person.age = 20;
        person.job.type = '前端工程师';
    }
    return{
        person,
        whoarey
    }
}
~~~

