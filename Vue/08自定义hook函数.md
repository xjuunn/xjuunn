# 自定义hook函数

本质是一个函数，把setup函数中使用的Composition API进行了封装。

类似于Vue2中的mixin，自定义hook复用了代码，让setup中的逻辑更清楚易懂。

hook文件userPoint.js
~~~js
import {reactive,onMounted,onBefoureUnmounte} from 'vue';
export default function(){
    let point = reactive({
        x:0,
        y:0
    })
    function savePoint(event){
        point.x = event.pageX
        point.y = event.pageY
        console.log(event.pageX,event.pageY)
    }
    onMounted(()=>{
        window.addEventListener('click',savePoint);
    })
    onBeforeUnmounte(()=>{
        window.removeEventListener('click',savePoint)
    })
    reture point;
}
~~~

### 使用

~~~ js
import usePoint from '../hooks/usePoint'
export default{
    name:'Demo',
    setup(){
        let sum = ref(0);
        let point = usePoint();
        return {sum,point}
    }
}
~~~

