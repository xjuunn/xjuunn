# jQue基本效果

[TOC]

### 隐藏和显示

```
$("#hide").click(function(){  $("p").hide();});$("#show").click(function(){  $("p").show();});
```

### 语法

> $(selector).hide(speed,callback);
> 
> 
> $(*selector*).show(*speed,callback*);
> 
> show([speed,[easing],[fn]])
> 

speed参数规定隐藏/显示的速度[“slow”/“fast”/毫秒数]

callback执行显示或隐藏后执行的函数名称

easing:指定切换效果，默认是swing，可用参数linear

使用回调函数

```
$(document).ready(function(){  $(".hidebtn").click(function(){    $("div").hide(1000,"linear",function(){      alert("Hide() 方法已完成!");    });  });});
```

### 在显示和隐藏之间切换 Toggle

```
$("button").click(function(){  $("p").toggle();});
```

语法

> $(selector).toggle(speed,callback);
> 

### 淡入淡出

### 淡入

> $(selector).fadeIn(speed,callback);
> 

```
$("button").click(function(){  $("#div1").fadeIn();  $("#div2").fadeIn("slow");  $("#div3").fadeIn(3000);});
```

### 淡出

> $(selector).fadeOut(speed,callback);
> 

```
$("button").click(function(){  $("#div1").fadeOut();  $("#div2").fadeOut("slow");  $("#div3").fadeOut(3000);});
```

### 淡入和淡出之间切换

> $(selector).fadeToggle(speed,callback);
> 

```
$("button").click(function(){  $("#div1").fadeToggle();  $("#div2").fadeToggle("slow");  $("#div3").fadeToggle(3000);});
```

### 渐变不透明度

> $(selector).fadeTo(speed,opacity,callback);
> 

```
$("button").click(function(){  $("#div1").fadeTo("slow",0.15);  $("#div2").fadeTo("slow",0.4);  $("#div3").fadeTo("slow",0.7);});
```

### 滑动

### 向下滑动

> $(selector).slideDown(speed,callback);
> 

### 向上滑动

> $(selector).slideUp(speed,callback);
> 

### 向上和向下交替滑动

> $(selector).slideToggle(speed,callback);
> 

### 动画

### animate() 方法

> $(selector).animate({params},speed,callback);
> 
- params参数定义形成动画的CSS属性
- 可选的speed参数规定效果时长
- 可选的callback参数是动画完成后所执行的函数名称

向右平移250px //position: absolute;

```
$("button").click(function(){  $("div").animate({left:'250px'});});
```

操作多个属性

```
$("button").click(function(){  $("div").animate({    left:'250px',    opacity:'0.5',    height:'150px',    width:'150px'  });});
```

相对值

```
$("button").click(function(){  $("div").animate({    left:'250px',    height:'+=150px',    width:'+=150px'  });});
```

预定值

```
$("button").click(function(){  $("div").animate({    height:'toggle'  });});
```

队列，逐步执行动画

```
$("button").click(function(){  var div=$("div");  div.animate({height:'300px',opacity:'0.4'},"slow");  div.animate({width:'300px',opacity:'0.8'},"slow");  div.animate({height:'100px',opacity:'0.4'},"slow");  div.animate({width:'100px',opacity:'0.8'},"slow");});
```

### 停止动画

### stop() 方法

> $(selector).stop(stopAll,goToEnd);
> 

可选的 stopAll 参数规定是否应该清除动画队列。默认是 false，即仅停止活动的动画，允许任何排入队列的动画向后执行。

可选的 goToEnd 参数规定是否立即完成当前动画。默认是 false。

因此，默认地，stop() 会清除在被选元素上指定的当前动画。

### 链(Chaining)

### 方法链接

> $(“#p1”).css(“color”,“red”).slideUp(2000).slideDown(2000);
> 

### 队列

1. 动画或者效果一旦触发就会执行，如果多次触发，就会造成多个动画或者效果排队执行

### 停止排队

> stop()
> 
1. stop()方法用于停止动画或效果
2. 注意：stop()写到动画或者效果前面，相当于停止结束上一次动画。
