# express中使用JWT

# 安装JWT的包

> npm install jsonwebtoken express-jwt
> 
- jsonwebtoken用于生成JWt字符串。
- express-jwt用于将Jwt字符串解析还原成JSON对象。

## 导入

```jsx
const jwt = require('jsonwebtoken');
const expressJWT = require('express-jwt');
```

# 定义secret密钥

为了保证JWT字符串的安全性，防止JWT字符串在网络传输过程中被别人破解，需要专门定义一个用于加密和解密的secret密钥：

1. 当生成JWT字符串的时候，需要使用secret密钥对用户的信息进行加密，最终得到加密好的JWT字符串
2. 当把JWT字符串解析还原成JSON对象的时候，需要使用secret密钥进行解密。

```jsx
const secretKey = "字符串";
```

# 在登录成功后生成JWT字符串

调用jsonwebtoken包提供的sign()方法，将用户的信息加密成JWT字符串，响应给客户端：

```jsx
app.post("/api/login",function(req,res){
		res.send({
				status:200,
				message:"登录成功",
				token:jwt.sign({username:userinfo.username},secretKey,{expiresIn:30s});
		})
})
```

参数

- 用户的信息对象
- 加密的密钥
- 当前token的有效期

# 将JWT字符串还原成JSON对象

客户端每次访问那些有权限的接口的时候，都需要主动通过请求头中的Authorization字段，将Token字符串发送到服务端进行身份认证。

此时，服务器可以通过express-jwt这个中间件，自动将客户端发送过来的Token解析还原成JSON对象：

```jsx
//使用app.use()来注册中间件
//expressJWT({secret:secretKey})就是用来解析Token的中间件
//.unless({path:[/^\/api\//]})用来指定哪些接口不需要访问权限
app.use(expressJWT({secret:secretKey}).unless({path:[/^\/api\//]}));
```

## 使用req.user获取用户信息

当express-jwt这个中间件配置成功后，即可在那些有权限的接口中，使用req.user对象，来访问JWT字符串中解析出来的用户信息：

```jsx
app.get("/admin/getinfo",function(req,res){
		console.log(req.user);
		res.send({
				status:200,
				message:"获取用户信息成功",
				data:req.user
		})
})
```
