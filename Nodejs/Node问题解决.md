# Node问题解决

- 跨域问题
  
    ```jsx
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
    res.header('Access-Control-Allow-Headers', 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild');
    res.header("X-Powered-By",' 3.2.1')
    res.header("Content-Type", "application/json;charset=utf-8");
    ```
    
- Post请求不到参数
  
    ```jsx
    app.use(express.urlencoded({ extended: true }))
    app.use(express.json())
    ```
    
    Post请求接收数据不是用query，而是req.body
    
- 页面通过 HTTPS 加载，但请求了不安全的 XMLHttpRequest 端点 - Page loaded over HTTPS but requested an insecure XMLHttpRequest endpoint
  
    `<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests" />`
    
    
    
    ## 跨域的第二中解决方案  cors
    
    ~~~ js
    const express = require("express");
    const cors = require("cors");
    const app = express();
    app.use(cors({
      origin: ["http://localhost:8080"],
      allowedHeaders: ["Content-Type"],
    }));
    app.get("/getUser3", (req, res) => {
      res.send({
        id: 2,
        name: "John Doe",
      });
    });
    app.listen(7788);
    ~~~
    
    
    
    
