# Vue简介

一套用于构建用户界面的渐进式JavaScript框架

渐进式：Vue可以自底向上逐层的应用

## 特点

1.  采用组件化，提高复用率，让代码更好维护
2.  声明式编码，让编码人员无需直接操作DOM，提高开发效率
3.  使用虚拟DOM+优秀的Diff算法，尽量复用DOM节点 



~~~ html
<div class="main">
        {{data1}}
    </div>
    <script>
        
        new Vue({
            el:'.main',
            data:{
                data1:'数据1',
                data2:'数据2'
            }
        })
~~~









