# express中的Sesson身份认证

# 在express中使用Session认证

## 安装express-session中间件

> npm Install express-session
> 

## 配置express-session中间件

```jsx
var session = resquire("express-session");
app.use(session({
		secret:"keyboard cat",//可以是任意字符串,负责加密
		resave:false,
		saveUninitialized:true
}))
```

## 向session中存数据

当sxpress-sesion中间件配置成功后，即可通过req.session来访问使用session对象，从而存储用户的关键信息：

```jsx
app.post("/api/login",(req,res)=>{
		if(req.body.username!=="admin"||req.body.password!=="123456"){
				return res.send({status:1,msg:"登录失败"})；
		}
		req.session.user = req.body;//将用户的信息存储到sesion中
		req.session.islogin = true;//将用户的登录状态存储到session中
		res.send({status:0,msg:"登录成功"});
})
```

## 从session中取数据

```jsx
app.get("/api/username",(req,res)=>{
		if(!req.session.islogin){
				return res.send({status:1,msg:"fail"});
		}
		res.send({status:0,msg:"success",username:req.session.user.username});
})
```

## 清空session

调用req.session.destroy()函数，即可清空服务器保存的session信息。

```jsx
app.post("/api/logout",(req,res)=>{
		req.session.destroy();
		res.send({
				status:0,
				msg:"退出登录成功"
		})
})
```

# Session认证的局限性

Session认证机制需要配合Cookie才能实现。由于Cookie不支持跨域访问，所以当涉及到前端跨域请求后端接口的时候，需要很多额外的配置才能实现。
