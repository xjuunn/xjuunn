# 01 vue

# Vue

vue是一个JavaScript框架，简化Dom操作，响应式数据驱动

### 基础语法

```html
<div id="app">    {{message}}</div>
<script src=""></script><script>  
    var app = new Vue({    
        el:"#app",       
        data:{    
            message:"hello Vue!"  
        }  
    })
</script>
```

> hello Vue！
> 

### el：挂载点

Vue会管理el选项命中的元素及其内部的后代元素，Vue不能挂载在html和body标签，建议挂载到div标签上。

### data：数据对象

{{}}中可以直接使用变量，支持表达式和字符串的拼接

### 常用命令

- v-text 填充文本
- v-html 填充html
- v-on 绑定事件
- v-show 操纵显示状态 修改display属性（适合频繁切换的元素）
- v-if 操纵显示状态 直接删除DOM树，性能消耗比较大
- v-bind 设置元素属性
- v-for 根据数据生成列表
- v-model 获取和设置表单元素的值（双向数据绑定）

### v-on绑定事件

```html
<input type="button" id="app" v-on:click="doIt" value="按钮">   
<script src=""></script>   
<script>     
    var app = new Vue({      
        el:"#app",       
        methods:{      
            doIt:function(){        
                alert("hello"); 
            }    
        }   
    })  
</script>
```

> 简写
> 
> 
> ```html
> <input type="button" id="app" @click="doIt" value="按钮">
> ```
> 

可以通过this来获取data中的数据

### v-bind 设置元素属性

```html
<div id="app">   
    <img v-bind:src="imgSrc"/>//v-bind可以省略    
    <img :title="imgtitle+'!!!'"/>    
    <img :class="isActive?'active':''"/>  
    <img :class="{active:isActive}"/>
</div>
```

```js
var app = new Vue({  
    el:"#app",   
    data:{    
        imgSrc:"src",    
        imgTitle:"title",   
        isActive:false   
    }})
```

### v-for 根据数据生成列表

```html
<input type="button" value="按钮" id="btn1"/><div id="content">   
    <ul>      
        <li v-for="(it,index) in dizhi">test{{it}}</li>   
    </ul>  
    <ul>     
        <li v-for="item in dizhi2">地址:{{item.name}}</li>    
    </ul>
</div>
<script>  
        var app = new Vue({
            el:"#content",    
            data:{       
                dizhi:["北京","上海","广东","深圳"],   
                dizhi2:[{name:"北京1"},              
                        {name:"上海1"},         
                        {name:"广东1"},          
                        {name:"深圳1"}]   
            }  
        })
</script>
```
