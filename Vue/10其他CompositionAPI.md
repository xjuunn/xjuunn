# 其他 Composition API

[toc]

## shallowReactive 和 shallowRef

* shallowReactive只处理对象最外层属性的响应式（浅响应式）
* shallowRef只处理基本数据类型的响应式，不进行对象的响应式处理

> 如果有一个对象数据，结构比较深，但变化只是最外层属性的变化    用shallowReactive
>
> 如果有一个对象数据，后续功能不会修改该对象中的属性，而是产生新的对象来替换  用shallowRef

## readonly 和 shallowReadonly

* readonly 让一个响应式数据变为只读的（深只读）
* shallowReadonly 让一个响应式数据变为只读的（浅只读）

## toRaw 和 markRaw

* toRaw
    * 作用：将一个由reactive生成的响应式对象转换为普通对象
    * 使用场景：用于读取响应式对象对应的普通对象，对这个普通对象的所有操作，不会引起页面的更新
* **markRaw**
    * 作用：标记一个对象，使其永远不会再成为响应式对象
    * 应用场景
        1. 有些值不应该设置为响应式的，例如复杂的第三方类库等。
        2. 当渲染具有不可变数据源的大列表时，跳过响应式转换可以提高性能。

## customRef 自定义ref

~~~ js
import {customRef} from 'vue';
export default {
	name;'App'
    setup(){
        function myRef(value){
            return customRef((track,trigger)=>{
                return {
                    get(){
                        track();//通知Vue追踪value的变化
                        return value;
                    },
                    set(newValue){
                        value = newValue;
                        trigger()//通知Vue去重新解析模板
                    }
                }
            })
        }
        let keyWord = myRef('hello');
        return {keyword};
    }
}
~~~

## provide 和 inject

实现**祖孙**组件间通信

用法：父组件有一个provide选项来提供数据，子组件有一个inject选项来使用这些数据

~~~ js
// 祖组件：
let car - reactive({name:'test1',price:'40'});
provide('car',car)
// 孙组件
const car = inject('car');
return{car}
~~~

## 响应式数据的判断

* isRef：检查一个值是否为一个ref对象
* isReactive：检查一个对象是否由reactive创建的响应式代理
* isReadonly：检查一个对象是否由readonly创建的只读代理
* isProxy：检查一个对象是否由reactive或者readonly方法创建的代理

