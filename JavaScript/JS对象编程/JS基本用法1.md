# JS基本用法1

### 对象定义

```js
window.onload=function(){    
var test = document.getElementById("test");              
    var personal = {          
        name:"张三",          
        age:"14",            
        work:"程序员",       
        reSetName:function(){//对象的方法       
            this.name = "李四";         
        }       
    }    
    personal.reSetName();//方法的调用         
    test.innerHTML = personal.name;//       test.innerHTML = personal["name"];两种使用方式   
}
```
