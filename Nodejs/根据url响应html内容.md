# 根据url响应html内容

## 根据不同的url相应不同的html内容

### 实现步骤

1. 获取请求的url地址
2. 设置默认的响应内容
3. 判断用户请求是否为/或者/index.html首页
4. 判断用户请求的是否为/about.html
5. 设置Content-Type响应头，防止中文乱码
6. 使用res.end()把内容响应给客户端

``` js
    const http = require('http');
    const server = http.createServer();
    server.on('request',(req,res)=>{
        // 1. 获取请求的url地址
        const url = req.url;
        // 2. 设置默认的响应内容
        let content = "<hi>404 Not found!</hi>";
        // 3. 判断用户请求是否为/或者/index.html首页
        // 4. 判断用户请求的是否为/about.html
        if (url==='/'||url==='/index.html'){
            content = "<hi>首页</hi>";
        }else if(url==='/about.html'){
            content = "<hi>关于</hi>";
        }    // 5. 设置Content-Type响应头，防止中文乱码
        res.setHeader("Content-Type","text/html; charset=utf-8");
        // 6. 使用res.end()把内容响应给客户端
        res.end(content);
    })
    server.listen(8080,function (){
        console.log('服务器启动成功')
    })
```

### 磁盘和浏览器映射

把文件实际的存放路径，作为每个资源的url地址

### 实现步骤

1. 导入需要的模块
2. 创建基本的web服务器
3. 将资源的请求url地址映射为文件的存放路径
4. 读取文件的内容，并且响应给客户端
5. 优化资源的请求路径

> url路径中的中文会自动转换为百分号编码格式，在代码中需要转换为中文格式
> 
> 
> ``` js
>   const url = decodeURI(req.url);
> ```
