[TOC]

# Flex 布局

flex是flexible Box的缩写，意为“弹性布局”，用来为盒状模型提供最大的灵活性，任何容器都可以指定为flex布局。父容器设置为flex布局后，子元素的float,clear和vertical-align属性将失效。

## 父容器属性

* flex-direction 设置主轴的方向
* justify-content 设置主轴上的子元素的排列方式
* flex-wrap 设置子元素是否换行
* align-content 设置侧轴上的子元素的排列方式（多行）
* align-items 设置侧轴上的子元素的排列方式（单行）
* flex-flow 复合属性，相当于同时设置了flex-deirection 和 flex-wrap

### flex-direction 主轴方向

| 属性           | 说明             |
| -------------- | ---------------- |
| row            | 默认值，从左向右 |
| row-reverse    | 从右向左         |
| column         | 从上到下         |
| column-reverse | 从下到上         |

### justify-content 主轴排列方式

| 属性           | 说明               |
| -------------- | ------------------ |
| flex-start     | 默认值，从头部开始 |
| flex-end       | 从尾部开始         |
| content        | 居中对齐           |
| space-around   | 平分剩余空间       |
| spance-between | 两边贴边平分空间   |

### flex-wrap 元素换行

| 属性   | 说明           |
| ------ | -------------- |
| nowrap | 默认值，不换行 |
| wrap   | 换行           |

### align-items 侧轴排列

| 属性值     | 说明                       |
| ---------- | -------------------------- |
| flex-start | 默认值，从上到下           |
| flex-end   | 从下到上                   |
| center     | 居中                       |
| stretch    | 拉伸（高度或宽度不能设置） |

### align-content 侧轴排列 多行

只能用于子项出现换行的情况，单行是没有效果的

| 属性          | 说明                             |
| ------------- | -------------------------------- |
| flex-start    | 默认值，在侧轴的头部开始         |
| flex-end      | 在侧轴的尾部开始排列             |
| center        | 在侧轴的中间显示                 |
| space-around  | 子项在侧轴平分剩余空间           |
| space-between | 子项先贴边，再平分剩余空间       |
| stretch       | 设置子项元素高度平分父元素的高度 |

### flex-flow 复合属性

~~~ js
flex-flow:row wrap;
~~~

## 子项属性

* flex **子项占的份数**
* aligh-self 控制子项自己在侧轴的排列方式
* order 子项的排列顺序