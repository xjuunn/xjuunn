[TOC]

# ES7

## includes

判断数组元素是否存在

~~~ js
const test = ['test','test2','test3','test4'];
console.log(test.includes('test1'))//> true
~~~

## \*\* 指数运算

~~~ js
console.log(2**10);// > 1024
~~~

# ES8

## async 函数

~~~ js
async function fn(){
    return new Promise((resolve,reject)=>{
        resolve("成功的数据");
    });
}
result.then(value=>{
    console.log(value);
},reason =>{
    console.warn(reason);
})
~~~

## await 表达式

* await 必须卸载async函数中
* await右侧的表达式一般为promise
* await返回的是promise成功的值
* await的promise失败了，就会抛出异常，需要通过try…catch捕获异常处理

~~~js
const p = new Promise((resolve,reject) =>{
    resolve('data');
    //reject('err');
})
async function main(){
    try {
        let result = await p;
        console.log(result);
    } catch (e){
        console.log(e);
    }
}
~~~

### await 读取文件

~~~ js
function readTest1(){
    return new Promise((resolve,reject)=>{
        fs.readFile("./test1.md",(err,data)={
            if(err) reject(err);
            resolve(data);
        })
    })
}
//使用
async function main(){
    let test1 = await readTest1();
}
main();
~~~

### await 发送AJAX请求

~~~ js
//封装AJAX
function sendAJAX(url){
    return new Promise((resolve,reject) =>{
        const x = new XMLHttpRequest();
        x.open('GET',url);
        x.send();
        x.onreadystatechange = function(){
            if(x.readState === 4){
                if(x.status >= 200 && x.status <300){
                    resolve(x.response);
                }else{
                    reject(x.status);
                }
            }
        }
    })
}

//使用promise then调用
sendAJAX("http://127.0.0.1").then(value=>{},reason=>{});
//使用async await调用
async function main(){
    let result = await sendAJAX('http://127.0.0.1');
}
~~~

## 对象方法扩展

* Object.values()方法返回一个给定对象的所有可枚举属性的值的数组
* Object.entries()方法返回一个给定对象自身可遍历属性[key,value]的数组
* Object.getOwnPropertyDescriptors方法返回指定对象所有自身属性的描述对象

~~~ js
const school = {
    name:'schoolname',
    city:['test1','test2','test3'],
    student:['test4','test5']
}
Object.keys(school);// 获取对象所有的键
Object.values(school);//获取对象所有的值
Object.entries(school);//获取键和值的数组
//创建Map
const m = new Map(Object.entries(school));
console.log(m.get('city'));
~~~

# ES9

## rest 参数

~~~ js
function connect({host,port,...user}){
    console.log(host);
    console.log(port);
    console.log(user);
}
connect({
    host:'127.0.0.1',
    port:8008,
    username:'root',
    pass:'root',
    type:'admin'
})
~~~

## … 扩展运算符

~~~ js
const test1 = {
    a:'test1'
}
const test2 = {
    b:'test2'
}
const test3 = {
    c:"test3"
}
const Test = {...test1,...test2,...teset3};
~~~

## 命名捕获分组

 ~~~ js
 let str = "<a href='http://baidu.com'>百度</a>";
 //提取 url 与 标签文本  以前写法
 const reg = /<a href='(.*)'>(.*)<\/a>/;
 const result = reg.exec(str);
 console.log(result[1]);
 console.log(result[2]);
 //新写法
 const reg = /<a href='(?<url>.*)'>(?<text>.*)<\/a>/;
 const result reg.exec(str);
 console.log(result);
 ~~~



