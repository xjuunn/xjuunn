# JWT认证机制

JWT（Json Web Token）是目前最流行的跨域认证解决方案。

# 工作原理

![Untitled](JWT%E8%AE%A4%E8%AF%81%E6%9C%BA%E5%88%B6%20fcedcf5928524ce2ad630ce172a75316/Untitled.png)

用户的信息通过Token字符串的形式，保存在客户端浏览器中。服务器通过还原Token字符串的形式来认证用户的身份。

# JWT字符串的组成部分

JWT通常由三部分组成，分别是Header（头部）、Payloaad（有效荷载）、Signature（签名）。

三者之间使用英文的“.”分隔。

> Header.Payload.Signature
> 
- Payload部分是真正的用户信息，是用户信息经过加密之后生成的字符串。
- Header和Signature是安全性相关的部分，只是为了保证Token的安全性。

# JWT的使用方式

客户端收到服务器返回的JWT之后，通常会将它储存在localStorage或sessionStorage中

此后，客户端每次与服务器通信，都要带上这个JWT的字符串，从而进行身份认证。推荐的做法是把JWT放在HTTP请求头的Authorization字段中

> Authoization: Bearer<token>

### jsonwebtoken

~~~ js
const jwt= require('jsonwebtoken');
const secret_key = "test01"; // 密钥
// 生成token
let token = jwt.sign({data:"test0000",test:"aaa"},secret_key,{algorithm:"HS256"});
// 解密token
jwt.verify(token,secret_key,(err,data)=>{
    console.log(err,data)
});
~~~

