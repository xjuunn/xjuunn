# computed计算属性

用法

~~~ js
import {computed} from 'vue';

setup(){
    //简写
	let fullName = computed(()=>{
        return person.firstName + '-' + person.laseName;
    }) 
    //完整写法
    let fullname = computed({
        get(){
            return person.firstName + '-' + person.lastName
        },
        set(value){
            const nameArr = value.split('-');
            person.firstName = nameArr[0];
            person.lastName = nameArr[1];
        }
    })
}
~~~

