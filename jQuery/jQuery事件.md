# jQuery事件

### 事件

### 事件处理on()绑定事件

on()方法才匹配元素上绑定一个或多个事件的事件处理函数

语法

> element.on(events,[selector],fn)
> 
- events: 一个或多个空格分隔的事件类型，如click或keydown
- selector：元素的子元素选择器
- fn：回调函数，即绑定在元素身上的侦听函数。

```
$("div").on({    mouseenter:function(){    },    click:function(){    }});
```

```
$("div").on("mouseenter mouseleave",function(){});
```

可以事件委托操作，事件委托的定义是，把原来嫁给子元素身上的事件绑定在父元素身上，就是把事件委托给父元素

> $(“ul”).on(‘click’,’li’,function(){
> 
> 
> });
> 

动态创建的元素，click()没有办法绑定事件，on()可以给动态生成的元素绑定事件

### 事件处理0ff()解绑事件

off()方法可以移除通过on方法添加的事件处理程序

> $(“p”).off() //解绑p元素所有的事件处理程序。
> 
> 
> $(“p”).off(“click”) //解绑p元素上面的点击事件 后面的foo是侦听函数名
> 
> $(“p”).off(“click”,”li”) //解绑事件委托
> 

如果有的事件只想触发一次，可以使用one()来绑定事件。

### 自动触发事件

有些事件希望自动触发，比如轮播图自动播放功能跟点击右侧按钮一致，可以利用定时器自动触发右侧按钮的点击事件，不必鼠标点击触发

> element.click() //第一种简写形式
> 
> 
> element.trigger(“type”) //第二种自动触发模式
> 
> element.triggerHandler(type) //第三种自动触发 不会触发元素的默认行为
> 

### 事件对象

事件被触发，就会有事件对象的产生

> element.on(events,[selector],function(event)(
> 
> 
> ));
> 

阻止默认行为：event.preventDefault()或者return false

阻止冒泡：event.stopPropagation()
