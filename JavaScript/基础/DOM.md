# DOM

### DOM

document object model 文档对象模型 1. DOM对象 2. 获取元素 3. 操作元素内容 4. 操作元素属性、样式 5. DOM节点操作 6. 简易评论框 ### 1. DOM对象 DOM是HTML页面的模型，将每一个标签都组为一个对象，javascript通过调用DOM中的属性，方法就可以对网页中的文本框，层等严肃进行编程控制。 通过DOM可以动态改变网页的内容，结构和样式。 标签=元素=节点 ##### DOM HTML节点树 DOM HTML指的是DOM中为操作HTML文档提供的属性和方法 文档表示HTML文件 文档中的标签称为元素 根节点：

标签是整个文档的根节点，有且只有一个。 [[Pasted image 20220526083327.png]] [[Pasted image 20220526084152.png]]

### 2. 获取元素

- id
- name
- tagname
- classname querySelectot(‘.text’)方法用于返回文档中匹配的对象的第一个，用选择器进行选择 querySelectot()，方法用于返回文档中酦醅的对象集合。

### 4. 操作元素属性、样式

利用attributes属性可以获取一个HTML元素的所有属性，以及所有属性的个数length [[Pasted image 20220526092145.png]] ##### 获取元素样式 getComputedStyle(oul).color; #### 节点操作 documenmt.createElement(‘li’)//创建节点 appendChild();列表末尾追加 insertBefore();为当前节点增加一个子节点 removeChild()删除节点 remove();

![Pasted image 20220526084152.png](DOM%20c5af39c5d4cc486c811743f42e6cfee4/Pasted_image_20220526084152.png)

![Pasted image 20220526092145.png](DOM%20c5af39c5d4cc486c811743f42e6cfee4/Pasted_image_20220526092145.png)

![Pasted image 20220526083327.png](DOM%20c5af39c5d4cc486c811743f42e6cfee4/Pasted_image_20220526083327.png)
