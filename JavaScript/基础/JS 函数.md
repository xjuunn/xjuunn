# JS 函数

# 声明

```jsx
function afunction(){
	//code
}
```

函数不会立即执行，只有在需要的时候调用

```jsx
//函数可以储存在变量中
var x = function(){
	//code
}
//可以直接用变量来调用函数
x();
```

# 构造函数Function()

函数可以通过js的内置函数构造器（Function()）来定义

```jsx
var myFunction = new Function("a","b","return a*b");
var x = myFunction(4,3);
```

但是以上代码可以直接写成

```jsx
var myFunction = function(a,b){
	return a*b;
}
var x = myFunction(3,4);
```

# 函数的自调用

```jsx
(function(){
		console.log("test")
})()
```

# 箭头函数

```
(参数1, 参数2, …, 参数N) => { 函数声明 }

(参数1, 参数2, …, 参数N) => 表达式(单一)
// 相当于：(参数1, 参数2, …, 参数N) =>{ return 表达式; }
```

当只有一个参数时，圆括号是可选的：

```
(单一参数) => {函数声明}
单一参数 => {函数声明}
```

没有参数的函数应该写成一对圆括号:

```
() => {函数声明}
```
