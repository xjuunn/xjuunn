# jQue元素操作

### 元素操作

### 遍历元素

jQuery隐式迭代是对同一个元素做了同样的操作，如果想要给同一类元素做不同操作时，就需要用到遍历

### 语法1

> $(“div”).each(function(index,doEle){
> 
> 
> })
> 
1. each()方法遍历匹配每一个元素。主要用DOM处理。each每一个
2. 里面回到函数有两个参数：index是每个元素的索引号，demele是每个DOM元素对象，不是jQuery对象
3. 所以要想使用jQuery方法，需要给dom元素转换为jQuery对象$(domEle)

### 语法2

> $.each(object,function(index,element){
> 
> 
> })
> 
1. $.each()方法可以用于遍历任何对象。主要用于数据处理，比如数组，对象。
2. 里面的函数有两个参数：index是每个元素的索引号；element遍历内容

### 创建元素

语法

> $(“
> 
> - 
> 
> ”);
> 

动态的创建一个li

### 添加元素

### 1. 内部添加

> element.append(“内容”)；//把内容放入匹配元素内部的最后面，类似于原生appendChild. element.before(“内容”)；//把内容放入目标元素前面
> 

### 2.外部添加

> element.after(“内容”); // 把元素放到目标元素后面
> 
> 
> element.before(“内容”); //把内容放到目标元素的前面
> 

内部添加元素，生成后，他们是父子关系。

外部添加元素，生成后，他们是兄弟关系。

### 删除元素

> element.remove() //删除匹配的元素（本身）
> 
> 
> element.empty() //删除匹配元素集合中的所有子节点
> 
> element.html(“”) // 清空匹配元素的内容
>
