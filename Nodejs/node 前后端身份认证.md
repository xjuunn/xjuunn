# node 前后端身份认证

**身份认证是指通过一定的手段，完成对用户身份的确认。**

[web开发模式（node）](node%20%E5%89%8D%E5%90%8E%E7%AB%AF%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%20090b7da0665a44a29a6ef9e84c443660/web%E5%BC%80%E5%8F%91%E6%A8%A1%E5%BC%8F%EF%BC%88node%EF%BC%89%2080387a6b8b02417d800e63ff1e8621e6.md) 

# 不同模式下的身份认证

- **服务端渲染**推荐使用 [Session认证机制](node%20%E5%89%8D%E5%90%8E%E7%AB%AF%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%20090b7da0665a44a29a6ef9e84c443660/Session%E8%AE%A4%E8%AF%81%E6%9C%BA%E5%88%B6%20ec12606ee2d448b68c4def013268e9d2.md)
- **前后端分离**推荐使用 [JWT认证机制](node%20%E5%89%8D%E5%90%8E%E7%AB%AF%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%20090b7da0665a44a29a6ef9e84c443660/JWT%E8%AE%A4%E8%AF%81%E6%9C%BA%E5%88%B6%20fcedcf5928524ce2ad630ce172a75316.md)

当前端请求后端的接口不存在跨域问题的时候，推荐使用Session身份认证机制。

当前后端需要跨域请求，推荐使用JWT认证机制。

[express中的Sesson身份认证](node%20%E5%89%8D%E5%90%8E%E7%AB%AF%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%20090b7da0665a44a29a6ef9e84c443660/express%E4%B8%AD%E7%9A%84Sesson%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%2048ce8e245f0c4a60ae727b31238cfe49.md) 

[express中使用JWT](node%20%E5%89%8D%E5%90%8E%E7%AB%AF%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%/express%E4%B8%AD%E4%BD%BF%E7%94%A8JWT%.md) 

# 全局错误处理中间件，捕获解析JWT失败后产生的错误

错误会导致崩溃

```jsx
app.user((err,req,ers,next)=>{
		if(err.name === "UnauthorizedError"){
				return res.send({
						status:401,
						message:"无效的token"
				})
		}
		res.send({
				status:500,
				message:"未知的错误"
		})
})
```
