# ref

想要让setup中的数据变成响应式，需要将数据用ref函数处理成RefImpl对象

~~~ js
import {ref} from 'vue';
export default {
    name;'App',
    setup(){
        let name = ref('张三');
        function work(){
            console.log(name.value)
        }
        return{
            name,
            work
        }
    }
}
~~~

>  对象的数据会被处理成proxy对象，对象的属性不需要获取value
>
> 模板中也不需要value，vue3会自动获取value的值

~~~ js
import {ref} from 'vue';
export default{
    name:'App',
    setup(){
        let school = ref({
            name:'xuexiao',
            renshu:100
        });
        function wsname(){
            console.log('学校的名字是'+school.value.name)
        }
        return{
            school,
            wsname
        }
    }
}
~~~

对象类型的数据，内部*求助*了vue3中的一个新函数 `reactive`函数