# JS事件

## 事件

事件三要素

- 事件源
- 事件类型
- 事件处理程序

事件绑定方式

- 行内绑定式
- 动态绑定式
- 事件监听式

### 事件监听

可以给同一个DOM对象的同一个事件添加多个事件处理程序

DOM对象.addEventListener(type,callback,[capture]);

```
btn.addEventListener('click',function(){    alert('one')})
```

- type式指DOM对象绑定的事件类型
- callback表示事件处理程序
- capture为false，表示再冒泡阶段完成

解绑动态绑定

btn.onclick = null

解绑事件监听

btn.removeEventLIstener(‘click’,fn)

### 事件的冒泡和默认

阻止冒泡

stopPropagation()或cancelBubble=true属性

阻止默认行为

perventDefault()，returnValue
