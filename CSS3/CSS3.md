# CSS3

![image-20220923144054384](CSS3.assets/image-20220923144054384.png)

## CSS3选择器

### 属性选择器

*   E[att^=value]开头att属性且属性值以value字符串开头的元素
*   E[att$=value] 结尾
*   E[att*=value] 包含

### 关系选择器

*   \> 第一代子元素

### 兄弟选择器

*   \+ 临近兄弟选择器 第二个元素紧跟第一个元素
*   ~ 普通兄弟选择器 位于该元素之后的兄弟

### 结构化伪类选择器

*   :nth-child(n) 选择父元素中第n个子元素
*   :nth-last-child(n)选择父元素中倒数第n个子元素
*   :first-child
*   :last-child
*   p:nth-of-type(n)父元素中第一个出现的p标签
*   p:nth-last-of-type(n)
*   :not(n)过滤掉父元素中所有匹配n选择符的元素

![image-20220927145655713](CSS3.assets/image-20220927145655713.png)

![image-20220927150514797](CSS3.assets/image-20220927150514797.png)

![image-20220927155510351](CSS3.assets/image-20220927155510351.png)

*   only-child 选择属于某父元素的唯一子元素的元素
*   empty 选择没有子元素或文本内容为空的所有元素
*   target 用于为页面中的某个target元素指定样式

## 伪元素选择器

### :before & :after

在被选元素的前面/后面插入元素

~~~ CSS
元素:before{
    content:"test"/url(img/test.jpg);
}
~~~

![image-20220930154949814](CSS3.assets/image-20220930154949814.png)



![ ](CSS3.assets/image-20220930155829524.png)















