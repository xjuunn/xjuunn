# jQue基础语法

###### 导入百度的Jquery

*> <script src=*"https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"*></script>*

###### JQue的入口函数

~~~ javascript
 
*//1.*

$(document).ready(function(){

*//JQuery语句*

});

*//2.*

$(function(){

*//JQuery语句*

});

*//等着DOM结构渲染完毕即可执行内部代码，不必等到所有外部资源加载完成，JQuery帮我们完成了封装*

*//相当于原生js中的MOMContentLoaded*

*//不同于原生js中的load事件是等页面文档，外部js文件，css文件，图片加载完毕才执行内部代码。*

~~~

*> $("#btn1").click(function(){*

*>           $("h1").hide();*

*>   });//隐藏h1标签    .show();显示*

#### jQuery的顶级对象 $

$是jQuery的别称，在代码总，可以使用jQuery代替$,但一般为了方便，通常都直接使用$

$是jQuery的顶级对象，相当于原生JavaScript中的window。把元素利用$包装成jQuery对象，就可以调用jQuery的方法

#### jQuery对象和DOM对象

1. 用原生js获取来的对象就是DOM对象

2. jQuery方法获取的元素就是jQuery对象

3. jQuery对象的本质是：利用$对DOM对象包装后产生的对象(为数组形式储存)

4. jQuery对象只能使用jQuery的方法，DOM对象只能使用原生的JavaScript属性和方法

##### 相互转换

DOM对象与jQuery对象之间是可以相互转换的。

因为原生js比jQuery更大，原生的一些属性和方法jQuery没有进行封装，想要使用这些属性和方法需要把jQuery对象转换为DOM对象才能使用。

1. DOM对象转换为jQuery对象：$(DOM对象)

*> $(‘div’)*

2. jQuery对象转换为DOM对象：加索引

*> $(‘div’)[*0*]*

*>*

*> $(‘div’).get(0)*

#### 隐式迭代

遍历内部DOM元素（伪数组形式储存）的过程叫做隐式迭代

简单理解：给匹配到的所有元素进行循环遍历，执行相应的方法，而不用我们再进行循环，简化我们额操作，方便我们调用

*> 给四个div设置背景颜色*

*>*

*> $(“div”).css(“background”,”pink”);*

#### 筛选选择器

| 语法       | 用法          | 描述                               |

| ---------- | ------------- | ---------------------------------- |

| :first     | $(‘li:first’) | 获取第一个li元素                   |

| :list      | $(‘li:list’)  | 获取最后一个元素                   |

| :eq(index) | $(‘li:eq(2)’) | 获取到li元素中，索引为2的元素      |

| :odd       | $(‘li:odd’)   | 获取到的li元素中，索引为奇数的元素 |

| :even      | $(‘li:even’)  | 获取到的li元素中，索引为偶数的元素 |

筛选方法

| 语法               | 用法                           | 说明                                                 |

| ------------------ | ------------------------------ | ---------------------------------------------------- |

| parent()           | $(‘li’).parent();              | 查找父级                                             |

| children(selector) | $(‘ul’).children(‘li’)         | 相当于$(‘ul>li’),最近一级，亲儿子                    |

| find(selector)     | $(‘ul’).find(‘li’)             | 相当于$(ul li),后代选择器                            |

| siblings(selector) | $(‘.first’).siblings(‘li’)     | 查找兄弟节点，不包括自己本身                         |

| nextAll([expr])    | $(‘.first’).nextAll()          | 查找当前元素之后所有的同辈元素                       |

| prevtAll([expr])   | $(‘.last’).prevAll()           | 查找当前元素之前所有的同辈元素                       |

| hasClass(class)    | $(‘div’).hasClass(‘protected’) | 检查当前的元素是否含有某个特定的类，如果有，返回true |

| eq(index)          | $(‘li’).eq(2)                  | 相当于$(‘li:eq(2)’)                                  |

#### 样式操作

##### 操作css的方法

jQuery可以使用css方法里修改简单元素样式，也可以操作类，修改多个样式。

1. 参数只写属性名，则是返回属性值

*> $(this).css(“color”)*

2. 参数是属性名，属性值，用逗号分隔，是设置一组样式，属性必须加引号，值如果是数字，可以不加引号和单位

*> $(this).css(“color”,”red”);*

3. 参数可以是对象的方式，方便设置多组样式。属性名和属性值用冒号隔开，属性可以不加引号

*> $(this).css({“color”:”white”,“font-size”:”20px”});*

##### 设置类样式方法

作用等同意classList，可以操作类样式，注意操作类里面的参数不要加点。

只对指定的类名执行操作，不会覆盖原来的类名

1. 添加类

*> $(“div”).addClass(“类名”);*

2. 移除类

*> $(“div”).removeClass(“类名”);*

3. 切换类

*> $(“div”).toggleClass(“类名”);*

tab标签切换案例

~~~ js

$(function(){

*//1. 点击上部的li，当前的li添加current类，其余兄弟移除类*

$(".tab_list li").click(function(){

*//链式编程操作*

$(*this*).addClass("current").siblings().removeClass("current");

*//2. 点击的同时，得到当前的li的索引号*

var index = $(*this*).index();

*//3. 让下部里面相应索引号的item显示，其余的隐藏*

$(".tab_con .item").eq(index).show().siblings().hide();

})

})

~~~

#### 事件

1. 鼠标事件

* click

* dblclick

* mouseenter

* mouseleave

* hover

2. 键盘事件

* keyperss

* keydown

* keyup

3. 表单事件

* submit

* change

* focus

* blur

4. 文档/窗口事件

* load

* resize

* scroll

* unload

###### 事件方法

~~~ js

$("p").click(function(){

*//方法体*

});

~~~

###### 常用的jQuery事件方法

1. $(document).ready()在文档完全加载完后执行函数

2. click点击事件

3. dblclick双击事件

4. mouseenter鼠标指针穿过元素事件

5. mouseleave鼠标指针离开元素事件

6. mousedown鼠标指针移动到元素上方，并按下鼠标按键

7. mouseup元素上松开鼠标按钮

8. hover模拟光标悬停事件

~~~ js

*//鼠标移动到元素上时，会触发第一个函数，当鼠标移出这个元素时，会触发指定的第二个函数*

$("#p1").hover(

function(){

alert("进入了p1");

},

function(){

alert("离开了p1");

}

);

~~~

9. focus当元素获得焦点时

10. blur当元素失去焦点时

g
