# TypeScript

[toc]

* 以javaScript为基础构建的语言
* 可以在任何支持javaScript的平台中执行
* 一个javaScript的超集
* 扩展了JavaScript并添加了类型

TS不能被js解析器直接执行，需要编译

~~~ shell
tsc test.ts
~~~

## 基本类型

~~~ ts
// 声明一个变量a，同时指定他的类型为number
let a: number;
a = 10;
// a = 'hello' 报错，因为a的类型是number，不能赋值字符串
let b: string;
b = 'hello';
// 声明完变量直接赋值
let c: boolean = false;
// 如果变量的声明和赋值时同时进行的，TS可以自动对变量进行类型检测
let d  = false;

// 函数的使用
function sum(a: number, b: number): number{
    return a+b;
}
~~~

| 类型    | 例子             | 描述                             |
| ------- | ---------------- | -------------------------------- |
| number  | 1,-33,2.4        | 任意数字                         |
| string  | ‘hi’,”hi”,\`hi\` | 任意                             |
| boolean | true,false       | 布尔值                           |
| 字面量  | 其本身           | 限制变量的值就是该字面量的值     |
| any     | \*               | 任意类型                         |
| unknown | \*               | 类型安全的any                    |
| void    | 空值(undefined)  | 没有值(或者undefined)            |
| never   | 没有值           | 不能是任何值                     |
| object  | {name:”test”}    | 任意js对象                       |
| array   | [1,3,4]          | 任意js数组                       |
| tuple   | [4,5]            | 元素，TS新增类型，固定长度的数组 |
| enum    | enum{A,B}        | 枚举，TS中新增类型               |

#### 字面量类型

~~~ ts
let a:10;
a = 10;
~~~

可以使用<kbd>|</kbd>来连接多个类型（联合类型）

~~~ ts
let b: "male" | "female";
b = "male";
b = "female";
~~~

#### any 任意值

any 表示的是任意类型，一个变量设置类型为any后相当于该变量关闭了TS的类型检测.

使用TS时，不建议使用any类型

~~~ ts
let d: any;
~~~

声明变量如果不指定类型，则TS解析器会自动判断变量的类型为any(隐式的any)

~~~ ts
let d;
d = 10;
d = "hello";
~~~

#### unknown 表示未知类型的值

~~~ ts
let e: unknown;
e = 10;
e = 'hello';
e = true;
~~~

>   ### any 和 unknown 的区别
>
>   any可以赋值给任意类型
>
>   ~~~ ts
>   let s: string;
>   let b: any;
>   s = b;
>   ~~~
>
>   unknown 实际上就是类型安全的any，不能直接赋值给其他变量
>
>   ~~~ ts
>   let s: string;
>   let e: unknown;
>   if(typeof e === "string"){
>       s = e;
>   }
>   // 类型断言
>   s = e as string;
>   s = <string>e;
>   ~~~

#### void 空

用来表示空，以函数为例，表示没有返回值的函数

~~~ ts
function fn():void{
    return null;
}
~~~

#### never 没有值

函数为例，表示永远不会有返回值

~~~ ts
function fn2(): never{
    throw new Error('报错了');
}
~~~

#### object 对象

表示一个对象

~~~ ts
let a :object;
a = {};
a = function(){};

let b: {name:string}; // 必须只有一个name属性
b = {name:"test"}
~~~

<kbd>{ }</kbd> 用来指定对象中可以包含哪些属性

语法: {属性名: 属性值, 属性名: 属性值};

在属性名后面加上?,表示属性是可选的。

~~~ ts
let b: {name: string, age?:number};
b = {name:"test",age:20}; // age属性是可选的
~~~

any表示任意类型的属性（类型，个数）

~~~ ts
let c: {name: string,[propName: string]:any};
c = {name: "test", age:20,gender:"男"};
~~~

#### 函数

设置函数结构的类型声明：语法：(形参:类型,形参:类型…)=>返回值

~~~ ts
let d: (a:number,b:number)=>number;
d = function (n1:string,n2:string):number{
    return 10;
}
~~~

#### 数组

表示字符串数组

~~~ ts
let e:string[];
e = ['a','b','c'];
~~~

表示数值数组

~~~ ts
let f:number[];
let g:Array<number>;
g = [1,2,3];
~~~

#### 元组

固定长度的数组   语法：[类型,类型,类型]

~~~ ts
let h: [string,number];
h = ['hello'，123];
~~~

#### 枚举

~~~ ts
enum Gender{
    Male,
    Female
}
let i: {name:string,gender:string};
i = {
    name:"test",
    gender:Gender.Male
}
~~~

>   \$表示同时  同时拥有两个属性
>
>   ~~~ ts
>   let j: {name:string} & {age:number};
>   j = {name:"test",age:20};
>   ~~~
>
>   类型别名
>
>   ~~~ ts
>   type myType = 1 | 2 | 3 | 4 | 5;
>   let k : myType;
>   let l : myType;
>   k = 2;
>   ~~~

## 编译选项

创建<kbd>tsconfig.json</kbd>

>   tsconfig.json 是ts编译器的配置文件，ts编译器可以根据它的信息来对代码进行编译

执行命令 `tsc` 即可编译所有文件   -w自动编译

tsconfig选项

~~~ json
{
    // “include" 用来指定哪些ts文件需要被编译，
    // 路径 ** 表示任意目录  * 表示任意文件
    "include":["./src/**/*"],
    // "exclude" 不需要被编译的文件目录 默认值["node_modules","bower_components","jspm_packages"]
    "exclude":["./src/hello/**/*"]
}
~~~

>   extends 定义被继承的的配置文件
>
>   files 指定被编译文件的列表，只有需要编译的文件少时才会用到

### compilerOptions 编译器选项

~~~ json
{
    "compilerOptions":{
        "target":"es6", // 指定ts被编译为的ES的版本
        "module":"es6", // 指定要使用的模块化的规范
        "libs":["dom"], // lib用来指代橡木中要使用的库 用来代码提示
       	"outDir": "./dist", // 用来指代编译后文件所在的目录
        "outFile": "./dist/app.js", // 将代码合并为一个文件 
        "allowJs":true, // 是否对js文件进行编译
        "checkJs": true, // 是否检查js代码是否复核语法规范，默认为false
        "removeComments":true, // 是否移除注释
        "noEmit":true, // 不生成编译后的文件
     	"noEmitOnError":true, // 当有错误时不生成编译后的文件   
        "strict":true, // 所有严格检查的总开关
        "alwaysStrict":true, // 是否编译后的文件使用严格模式
        "noImplicitAny": true, // 不允许隐式的any类型
        "noImplicitThis": true, // 不允许不明确的类型的this
        "strictNullChecks":true // 严格的检查空值
    }
}
~~~

## 使用webpack打包ts代码

在npm项目目录下，

~~~ shell
npm i -D webpack webpack-cli typescript ts-loader
~~~

package.json

~~~ json
"scripts":{
 	"build":"webpack",   
}
~~~

创建webpack.config.js

~~~ js
const path = require('path');
module.exports = {
    entry:"./src/index.ts",// 指定入口文件
    output:{ // 指定打包文件所在目录
        path: path.resolve(__dirname,'dist'), // 指定打包文件的目录
        filename:"bundle,js" // 打包后文件的文件
    },
    module:{ // 指定webpack打包时要用的模块
        rules: [ // 制动要加载的规则
            {
                test: /\.ts$/, // 指定规则生效的文件
                use: 'ts-loader', // 要使用的loader
                exclude: /node-modules/, // 要排除的文件
            }
        ]
    }
}
~~~

### 自动引入编译后的文件

~~~ shell
npm i -D htaml-webpack-plugin
~~~

在webpack.config.js中引入

~~~js
const HTMLWebpackPlug = require('html-webpack-plugin');
module.exports = {
    // 配置webpack插件
    plugins: [
        new HTMLWebpackPlugin({
            template:"./src/index.html"  // 使用html模板
        })
    ]
}
~~~

### 运行服务器

~~~ shell
npm i -D webpack-dev-server
~~~

package.json

~~~json
"start":"web pack server --open chrome.exe"
~~~

编译前自动清空旧的文件

~~~ shell
npm i -D clean-webpack-plugin
~~~

webpack.config.js引入

~~~js
const { ClearWebpackPlugin } = require("clean-webpack-plugin");
...
plugins:[
    new CleanWebpackPlugin(),
]
~~~

>   设置引用模板
>
>   ~~~ js
>   module.exports = {
>       resolve:{
>           extensions: ['.ts','.js']
>       }
>   }
>   ~~~

## 面向对象

### class 类

~~~ ts
class Person{
    // name: string = "test"; // 可以简写
    name:"test",
    static age:number, // 使用static关键字可以定义静态属性
    readonly sex:1,  // 使用readonly定义只读属性
    sayHello(){
        console.log("hello world");
    }
}
const per = new Person();
~~~

### constructor 构造函数

~~~ ts
class Dog {
    name: string;
    age: number;
    // constructor 被称为构造函数 构造函数会在对象的创建时调用
    constructor(name:string,age:number){
        // 在实例方法中，this就表示当前实例
        // 在构造函数中当前对象就是当前创建的哪个对象
        // 可以用过this向新建的对象中添加属性
        this.name = name;
        this.age = age;
    }
    bark(){
		console.log(this.name);
    }
}
const dog = new Dog("小黑",4);
dog.bark();
~~~

### extends 继承

~~~ts
class Animal{
    name: string;
    age: number;
    constructor(name:string,age:number){
        this.name = name;
        this.age = age;
    }
    sayHello(){
        console.log("动物在叫")；
    }
}
// 定义一个狗类
class Dog extends Animal{
    run(){
        console.log(`${this.name}在跑`);
    }
    sayHello(){
        console.log("汪汪汪");
    }
}
class Cat extends Animal{}
~~~

#### super 父类

~~~ ts
class Dog extends Animal{
    age:number;
    constructor(name:string,age:number){
        // 子类中的构造函数重写了父类的构造函数，需要手动调用父类的构造函数
        super(name);
        this.age = age;
    }
}
~~~

### abstract 抽象类

以<kbd>abstract</kbd>开头的类就是抽象类，抽象类不难用来创建方法，专门用来继承的类。

~~~ ts
abstract class Animal{
    name:string;
    constructor(name:string){
        this.name = name;
    }
    // 定义抽象方法，必须由子类重写
    abstract sayHello():void;
}
class Cat extends Animal{
    sayHello(){
        console.log("喵喵喵");
    }
}
~~~

### interface 接口

>   接口只能在TS中使用

接口用来定义一个类结构

*   接口可以重复声明，最后会合并
*   接口中所有的属性都不能由实际的值
*   接口中所有的方法都是抽象方法

~~~ ts
interface myInterface{
    name: string;
    age: number;
    sayHello():void;
}
class MyClass implements myInter{
    name:string;
    constructor(name:string){
        this.name = name;
    }
    sayHello(){
        console.log('hello');
    }
}
~~~

#### getter setter 封装

*   public 修饰的属性可以在任意位置方位 默认
*   private 私有属性，只能在类内部进行访问
*   protected 受保护的，只有类内部和子类可以访问

~~~ ts
class Person{
    private _name:string;
    private _age:number;
    get name(){
        return this._name;
    }
    set name(value:string){
        this._name = value;
    }
}
~~~

>   ### 简化构造函数
>
>   ~~~ ts
>   constructor(public name:string,public age:number){
>       
>   }
>   ~~~

### \<T\> 泛型

在定义函数或是类时，如果遇到类型不明确就可以使用泛型

~~~ ts
function fn<T>(a:T):T{
    return a;
}
let result = fn(10) // 不指定泛型，TS可以自动类型检查
let reault2 = fn<string>('hello'); // 指定泛型

// 泛型可以有多个
function fn2<T,K>(a:T,b:K):T{
    return a;
}
fn2<number,string>(123,'hello');

// 特殊类型的泛型
interface Inter {
    length:number;
}
function fn3<T extends Inter>(a:T):number{
    return a.length;
}
fn3({length:10});

// 类中使用泛型
class MyClass<T>{
    name:T;
    constructor(name:T){
        this.
    }
}
~~~

