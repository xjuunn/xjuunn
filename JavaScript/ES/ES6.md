[TOC]

## let

let变量不能重复声明

let是块级作用域，只在代码块中有效

不存在变量提升

不影响作用域链

## const 常量

常量必须赋初始值

一般常量是使用大写

常量的值不能修改

块级作用域

对于数组的元素修改，不算对常量的修改

## 变量解构赋值

允许按照一定模式，从数组或对象中提取值

~~~ js
//数组结构
const test = ['aaa','bbb','ccc','ddd'];
let [t1,t2,t3,t4] = test;
console.log(t1);
//> aaa 
~~~

~~~ js
//对象写法
const people = {
    name:'lihua',
    age:20,
    play:function(){
        console.log("玩");
    }
}
let {name,age,play} = people;
console.log(name);
play();
//> lihua 玩
let {play} = people;
play();
//> 玩
~~~

## 模板字符串

声明字符串的方式

~~~ js
let str = `this is a str`;
~~~

内容中可以直接出现换行符

变量拼接

~~~ js
let test = 'aaa';
console.log(`${test}test`)
//> aaatest
~~~

## 对象简化写法

在大括号里面直接写入变量，作为对象的属性和方法

~~~ js
//原始写法
const people = {
    name:name,
    age:age,
    play:function(){ }
}
//简化后
const people = {
    name,
    age,
    play(){}
}
~~~

## 箭头函数

~~~ js
//原始写法
let fn = function(){ };
// 简化后
let fn = () => { };
~~~

this是静态的，this始终指向函数声明时所在作用域下的this的值

不能作为构造函数实例化对象

不能使用arguments变量

箭头函数的简写 

* 省略小括号：当形参只有一个的时候
* 省略花括号：当代码只有一条语句的时候，此时，return也必须省略，执行结果就是函数的返回值

> let a = n => 1+1

~~~ js
// 返回偶数
const arr = [1,2,3,4,5,6];
const result = arr.filter(item => item % 2 === 0);
console.log(result);
~~~

> 适合于this无关的回调，定时器，数组的方法回调
>
> 不适合于this有关的回调，事件回调，对象的方法

## 函数参数的默认值设置

~~~ js
function add(a,b,c=10){
    //当不传c时，默认为10，默认值的参数，一般放在最后
}
~~~

与解构赋值结合使用

~~~ js
function connect({ host=“127.0.0.1”,username,password,port}){
	console.log(host);
}
connect({
    host:'baidu.com'，
    username:'root',
    password:'123456',
    port:8080
})
//当不传host的时候，默认为127.0.0.1
~~~

## rest参数

~~~ js
test('test1','test2','test3');
//原始写法
function test(){
    console.log(arguments);// 得到的是一个对象
}
//rest参数
function test(...args){
    console.log(args);//得到的是一个数组
}
~~~

> rest参数必须要放在最后

## 扩展运算符

<kbd>…</kbd>扩展运算符能将数组转换为**参数序列**

~~~ js
const test = [1,2,3,4];
method(...test);
//相当于method（1，2，3，4）
~~~

数组合并

~~~ js
const test1 = ['a','b','c'];
const test2 = ['d','e','f'];
//原始写法
const result = test1.concat(test2);
//使用...
const result = [...test1,...test2];
~~~

数组克隆

~~~ js
const test = ['a','b','c'];
const result = [...test];
~~~

将为数组转换为真正的数组

~~~ js
const divs = document.querySelectorAll('div');
const divarr = [...divs];
~~~

## Symbol 

新的数据类型，表示独一无二，是一种类似于字符串的数据类型

* Symbol的值是唯一的，用来解决命名冲突问题
* Symbol值不能和其他数据进行运算
* Symbol定义的对象属性不能使用for…in循环遍历，但是可以使用Reflect.ownKeys来获取对象的所有键名

## 迭代器

迭代器(lterator)是一种接口，为各种不同的数据结构提供统一的访问机制。温和数据结构只要部署lterator接口，就可以完成遍历操作。

> 接口是对象里面的一个属性

~~~ js
const test = ['a','b','c','d'];
//使用for...of遍历数组
for(let v of test){
    console.log(v);//a,b,c,d  键值
}
// 使用for...in遍历数组
for(let v in test){
    console.log(v);//1，2，3，4 键名
}
~~~

## 生成器

生成器是一个特殊的函数

异步编程，纯回调函数

~~~ js
function * test(){    
    yield 'test1';
    yield 'test2';
    let a = yield 'test3';
    yield 'test4';
}
//调用
let ta = test();
ta.next();// test1
ta.next();// test2
ta.next();// test3
ta.next("test");// test4
//返回yield后面的语句
~~~

> next 第一次传参，作为整个方法的参数传入
>
> 后面的next传参，作为上一个yield的返回结果

异步事件的实例

~~~ js
function one(){
    setTImeout(()=>{
        console.log(111);
        test.next();
    },1000);
}
function two(){
    setTImeout(()=>{
        console.log(222);
        test.next();
    },1000);
}
function three(){
    setTImeout(()=>{
        console.log(333);
        test.next();
    },1000);
}
function * gen(){
    yield one();
    yield two();
    yield thrdd();
}
let test = gen();
test.next();
~~~

## Promise

Promise是ES6引入的异步编程的解决方案。语法上Promise是一个构造函数，用来封装异步操作并可以获取其成功或失败的结果。

~~~ js
//实例化Promise对象
const p = new Promise(function(resolve,reject){
	//模拟网络请求
	setTimeout(function(){
		let data = "数据库中的数据";
		resolve(data);//获取到数据执行
		let err = "错误";
		reject(err);
	},1000);
});

//调用 promise对象的 then 方法
p.then(function(value){
	console.log(value);//成功
},function(reason){
	console.log(reason);//失败
});
~~~

## 通过Promise，封装AJAX请求

~~~ js
const p = new Promise((resolve,reject)=>{
	//创建对象
	const xhr = new XMLHttpRequest();
	//初始化
	xhr.open("GET","http://127.0.0.1:10001/get");
	//发送
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status>=200 && xhr.status<300){
				resolve(xhr.response); //请求成功
			}else{
				reject(xhr.status); //请求失败
			}
		}
	}
})

//绑定回调
p.then(function(value){
	console.log(value)
},function(reason){
	console.log(reason)
})
~~~

> 调用then方法，then方法的返回结果是promise对象，对象的状态有回调函数的执行结果决定
>
> 1. 如果回调函数中返回结果是非promise类型的属性，状态为成功，返回值为对象的成功值
> 2. 是要给promise对象，promise的状态就是then的promise的状态

> promise可以链式调用
>
> ~~~ js
> p.then(value=>{
>     
> }).then(value=>{
>     
> }).then(value=>{
>     
> }).then(value=>{
>     
> })
> ~~~

## Set 集合

Es6提供了新的数据结构Set（集合）。它类似于数组，但成员的值都是唯一的，集合实现了iterator接口，所以可以使用扩展运算符和for…of进行遍历

* size 返回结合的元素个数
* add 增加一个新元素，返回当前集合
* delete 删除元素，返回boolean值
* has 检测集合中是否包含某个元素，返回boolean值

> 自动去除重复的元素

~~~ js
let s = new Set();
let s2 = new Set(['test','test2','test3','test3']);  // 会自动去掉一个test3
console.log(s2.size); //4
s2.add('test5');  // 添加
s2.delete('test');   //删除
s2.has('test2'); // 检测是否包含
s2.clear();//清空
//遍历
for(let v of s2){
    console.log(v);
}
~~~

~~~ js
let arr = [1,2,3,4,5,6,7,8,8,8,8];
let arr2 = [4,4,5,6,0];
// 数组去重
let result = [...new Set(arr)];
// 交集
let result = [...new Set(arr)].filter(item =>{
    let s2 = new Set(arr);
    if(s2.has(item)){
        return true;
    }else{
        return false;
    }
})
//交集简写
let result = [...Set(arr)].filter(item => new Set(arr).has(item));
//求并集
let result = [...new Set([...arr,...arr2])];
//求差集
let result = [...new Set(arr)].filter(item =》 !(new Set(arr).has(item)));
~~~

## Map

类似于对象，也是键值对的集合。但是“建”的范围不限于字符串，各种类型的值（包括对象）都可以当作键。

Map也实现了iterator接口，可以使用扩展运算符和for…of…进行遍历。

* size 返回Map元素个数
* set 增加一个新元素，返回当前Map
* get 返回键名对象的键值
* has 检测Map中是否包含某个元素，返回boolean
* clear 清空集合，返回undefined

~~~ js
let m = new Maop();
//添加元素
m.set('name',"test");
m.set('met',function(){
    console.log("方法被调用了");
})
let key = {
    keyname: 'keyname';
}
m.set(key,['test1','test2','test3']);
console.log(m.size);
m.delete('name');
console.log(m.get(key));
m.clear();
for(let v of m){
    console.log(v);//输出的是一个数组，第一个元素是键，后面的元素是值
}
~~~

## Class 类

作为对象的模板。通过class关键字，可以定义类

Es5通过构造函数来实例化对象

~~~ js
//手机
function Phone(brand,price){
    this.brand = brand;
    this.price = price;
}
//添加方法
Phone.prototype.call = function(){
    console.log("打电话");
}
//实例化对象
let Huawei = new Phone('华为',6000);
Huawei.call();
console.log(Huawei);
~~~

Es6 class

~~~ js
class Phone{
    //构造方法，名字不能修改
    constructor(brand,price){
        this.brand = brand;
        this.price = price;
    }
	call(){
        console.log("打电话");
    }
}

let Huawei = new Phone("Huawei",5000);
console.log(Huawei);
~~~

静态属性

~~~ js
class Phone{
    //静态属性
    static name = "手机";
    static call(){
        console.log("打电话");
    }
}
let Huawei = new Phone();
console.log(Huawei.name);//undefined
console.log(Phone.name);//手机
~~~

继承

~~~ js
class Phone{
    constructor(brand,price){
        this.brand = brean;
        this.price = price;
    }
    call(){
        console.log("打电话");
    }
}
class SmartPhone extends Phone {
	constructor(brand,price,color,size){
        super(brand,price);
        this.color = color;
        this.size = size;
    }
    call(){   //重写
        console.log("视频通话");
    }
    Photo(){
        console.log("拍照");
    }
    playGame(){
        console.log("玩游戏");
    }
}
const xiaomi = new SmartPhont('小米'，4000，'黑'，'9');
xiaomi.call();
~~~

get 和 set

~~~ js
class Phone{
    get price(){
        console.log("价格被读取了");
        return '3';
    }
    set price(newVal){
        console.log("价格被修改了");
    }
}
let s = new Phone();
s.price = 'free';
~~~

## 数值扩展

~~~ js
// Number.EPSILON 是javascript 表示的最小精度
function equal(a,b){   
    if(Math.abs(a-b)<Number.EPSILON){
    	return true;   
  	}else{
        return false;
    }
}
//二进制和八进制
let b = 0b1010;
let o = 0o777;
let d = 100;
let x = 0xff;
//Number.isFinite 检测一个数值是否为有限数
console.log(Number.isFinite(100/0));//> false
// Number.isNan 检测一个数值是否为NAN
console.log(Number.isNAN(123));
//字符转换为整数
console.log(Number.parseInt('23423sdf'));
// Number.isInteger 判断是否为整数
console.log(Number.isInterger(5));//> true
// Math.istrunc 将数字的小鼠部分抹掉
console.log(Math.trunc(3.5));
// Math.sign 正负或是0
console.log(Math.sign(100));// > 1
~~~

## 对象方法的扩展

~~~ js
// Object.is 判断两个值是否完全相等
console.log(Object.is(120,120));
// Object.assign 对象合并
const config1 = {
    host: 'localhost',
    port: 8080,
    name: 'root',
    pass: 'root',
    test: 'test'
}
const config2 = {
    host: 'http://127.0.0.1',
    port:8081,
    name:'test',
    pass:'123123',
    teste2:'test2'
}
//config2会覆盖config1里面相同的属性
console.log(Object.assign(config1,config2));
//Object.setPrototypeOf 设置原型对象
const school = {
    name:'school'
}
const cities = {
    name:['石家庄','邢台','衡水','邯郸']
}
Object.setPrototypeOf(school,cities);
console.log(Object.getPrototypeOf(school));
~~~

## 模块化

模块化是指将一个大的程序文件拆分成许多小的文件，然后将小的文件合并起来。

* 防止命名冲突
* 代码复用
* 高维护性

模块化的语法

* export命令用于规定模块的对外接口
* import命令用于输入其他模块提供的功能

~~~ js
//test.js
export let name = 'test';
export function play(){
    console.log("play");
}
~~~

~~~ html
<script type='module'>
	//引入test.js模块内容
    import * as test from "./src/js/test.js";
    //解构赋值的形式引入
    import {name,play} from './src/js/test.js';
    import (name as name1,play as play1) from './src/js/test2.js';// 使用as来设置别名
    import {default as testd} from './str/js/test3.js';
    //简便形式，针对默认暴露
    import test4 from './str/js/test3.js';
    console.log(test);
</script>
~~~

暴露方法

~~~ js
//分别暴露
export let name = '';
export function test(){};
//统一暴露
let name - '';
function test(){};
export {name,test}
//默认暴露
export default{
    name:'test',
    play:function(){}  //调用方法在default内
}
~~~



*test*