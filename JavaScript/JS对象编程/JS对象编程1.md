# JS对象编程1

### 对象编程

- new 创建一个对象
- delete 删除一个对象

### 得到对象的属性值

- 通过对象名.
- 通过对象名[“属性名”]
- 通过循环语句

### 对象分类

- 内置对象
- 自定义对象
- 浏览器对象

> Date,Math.String.Aeeay.Number.Boolean.Function,Global,Object
> 

### 日期对象 Date

```
var mydate = new Date()var mydate1 = new Date(2009,2,3,4)
```

- getDate 返回对象中月份中的天数
- getDay 星期
- getHours
- getMinutes
- getSexonds
- getMonth
- getFullYear
- getTime

### Math对象

不需要创建对象，直接可以Math.sqrt(y)使用

- ceil 向上取整
- floor向下取整
- round 四舍五入
- random()随机数

### Number对象

不需要创建

- toFixed(1)四舍五入保留1位小数

### String对象

需要创建

var newstr = “字符串”;

var newstr = new String(“字符串”)；

- link(url)创建一个以url为地址的链接
- big()放大字体
- fontsize(20)字体大小
- fontcolor(‘red’)字体颜色
- strike下划线
- sub下标
- sup上标
- length返回字符串长度
- split分离成数组
- indexOf(“子串”,其实位置)从前往后找子串的位置，查不到返回-1
- lastindexOf从后往前查找
- charAt(index)返回指定位置的字符
- substr(start,[length])从start开始，length长度
- substring(start,stop)取两个下标值之间的字符
- replace(‘str1’,’str2’)替换
- toLowerCase转换为小写
- toUpperCase转换为大写

> 通过选择器找元素
> 
> 
> document.querySelectory(‘#id’);
> 
> onfouse
> 
> onblur
>
