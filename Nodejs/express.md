# express

>   npm install express --save

## express

express 是基于Node.js平台，快速、开放、极简的Web开发框架

- Web网站服务器：专门对外提供Web网页资源的服务器
- API接口服务器：专门对外提供API接口的服务器

### 创建一个服务器

```js
//导入express,需要安装
const express = require('express');
//创建web服务器
const app = express();
//启动web服务器
app.listen(8080,()=>{
    console.log('服务器已启动');
})
```

### 监听Get请求

通过app.get()方法，可以监听客户端的GET请求，具体的语法格式如下：

```js
app.get('请求URL',function(req,res){
    //函数
})
```

- 参数1：客户端请求的URL地址
- 参数2：请求对应的处理函数
    - req：请求对象（包含了与请求相关的属性与方法）
    - res：响应对象（包含了与响应相关的属性与方法）

监听post请求同上

### 把内容响应给客户端

通过res.send()方法，可以把处理好的内容，发送给客户端

```js
app.get('/user',(req,res)=>{    //向客户端发送JSON对象
    res.send({name:'zs',age:20,gender:'男'})
})
app.post('/user',(req,res)=>{
    //向客户端发送文本内容
    res.send('请求成功')
})
```

### 获取URL中携带的查询参数

通过req.query对象，可以访问道客户端通过查询字符串的形式，发送到服务器的参数：

```js
app.get('/',(req,res)=>{
    //req.query是一个默认的空对象
    //客户端使用?name=zs&age=20这种查询字符串形式，发送到服务器的参数
    //可以通过req.query对象访问到，例如：
    //req.query.name     req.query.age
    console.log(req.query)
})
```

### 获取URL中的动态参数

通过req.params对象，可以访问到URL中，通过:匹配到的动态参数

```js
//URL地址中，可以通过:参数名的形式，匹配动态参数值
app.get('/user/:id',(req,res)=>{
    //req.params默认是一个空对象
    //里面存放着通过:匹配到的参数值
    console.log(req.params)
})
```

#### 获取post请求的参数

~~~ JS
const express = require("express");
const app = express();
app.use(express.json());
app.post("/ptest",(req,res)=>{
    console.log("post"+req.body);//使用req.body获取参数
    res.send("post");
})
app.listen(30030,()=>{
    console.log("server start...");
})
~~~



### 托管静态资源

### express.static()

expresss提供了一个非常好的函数，叫做express.static()，通过它，我们可以很方便地创建一个静态资源服务器，例如，通过如下代码可以创建一个public目录下的图片，css文件，JavaScript文件对外开放访问了

```
app.use(express.static('public'))
```

> Express在指定的静态资源目录中查找文件，并对外提供资源的访问路径。因此，存放静态文件的目录名不会出现在URL中
> 
> 
> 托管多个静态资源目录，多次调用express.static()函数就可以了，访问静态资源文件时，express.static()函数回根据目录添加顺序查询所需文件。
> 

### 挂载路径前缀

如果希望在托管的静态资源访问路径之前，挂在路径前缀，则可以使用如下的形式：

```js
app.use('/public',express.static('public'))
```

### 案例

```js
const fs = require('fs');
const path = require('path');
const express = require('express');
const exStatic = require('express-static');
const app = express();// z
app.get('/',(req,res)=>{
    fs.readFile(path.join(__dirname,'../../','index.html'),'utf8',(eer,datasrer)=>{
        res.send(datasrer)
    });
})
app.use(exStatic('./../../'));
app.listen(8080,()=>{
    console.log("服务器在8080端口上打开。http://127.0.0.1:8080")
})
```
