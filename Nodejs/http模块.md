# http模块

## http模块

http模块是Node.js官方提供的、用来创建web服务器的模块。通过http模块提供的http.createServer()方法，就能方便的把一台普通的电脑，变成一台web服务器，从而对外提供web资源服务。

> 导入
> 
> 
> const http = require(‘http’);
> 

### 创建最基本的web服务器

1. 导入http模块
2. 创建web服务器实例
3. 为服务器实例绑定request事件，监听客户端的请求
4. 启动服务器

```js
// 1. 导入http模块
const http = require('http');
// 2. 创建web服务器实例
const server = http.createServer();
// 3. 为服务器实例绑定request事件，监听客户端的请求
server.on('request',function (req,res){
    console.log("有人访问了你的服务器")
})
// 4. 启动服务器
server.listen(8080,function (){
    console.log('服务器启动')
})
```

### req请求对象

只要服务器接收到了客户端的请求，就会调用通过server.on()为服务器绑定的request事件处理函数。

如果想在事件处理函数中，访问与客户端相关的数据或属性，可以使用一下方式：

```js
server.on('request',(req,res)=>{
    console.log("有人访问了你的服务器");
    console.log("客户端请求的地址"+req.url);
    console.log("客户端请求类型"+req.method);
})
```

### res响应对象

在服务器的request事件处理函数中，如果想访问与服务器相关的数据或属性，可以使用一下的方式

```js
server.on('request',(req,res)=>{
    console.log("有人访问了你的服务器");
    console.log("客户端请求的地址"+req.url);
    console.log("客户端请求类型"+req.method);
    res.end("<p>"+req.url+"</p>")   //相应
})
```

### 解决中文乱码问题

> res.setHeader('Content-Type','text/html; charset=utf-8');
