# Watch监视属性

> 监视reactive定义的响应式数据时，oldValue无法正确获取、强制开启了深度监视（deep配置失效）。
>
> 监视reactive定义的响应式数据中某个属性时，deep配置有效

## 监视ref

~~~ js
import {ref,watch} from 'vue';

export default {
    name:'Demo',
    setup(){
        let sum = ref(0);
        let msg = ref('hello');
        // 监视ref定义的一个响应式数据
        watch(sum,(newValue,oldValue)=>{
            console.log('sum变了',newValue,oldValue)
        });
        // 监视ref定义的多个响应式数据
        watch([sum,msg],(newValue,oldValue)=>{
            console.log('sum或msg变了',newValue,oldValue);
        })
        // watch 配置
        watch(sum,(newValue,oldValue)=>{
            
        },{immediate:true})
        //监视reactive所定义的一个响应式数据中的某个属性
        watch(()=>person.name,(newValue,oldValue)=>{
            console.log("person的name属性变化了",newValue,oldValue);
        })
        
    }
}
~~~





