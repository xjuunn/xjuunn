# node 数据库（MySQL）

1. 安装操作MySQL数据库的第三方模块（MySQL）
2. 通过mysql模块连接到MySQL数据库
3. 通过mysql模块执行SQL语句

> npm install mysql
> 

# 连接

配置mysql模块

```jsx
// 导入mysql模块
const mysql = require("mysql");
// 建立连接
const db = mysql.createPool({
	host:"127.0.0.1",//数据库IP
	user:"登录账号",
	password:"密码",
	database:"数据库名"
})
```

测试mysql模块能否正常工作

调用db.query()函数，指定要执行的SQL语句，通过回调函数拿到执行的结果：

```jsx
db.query("select 1",(err,results)=>{
		if(err) return console.log(err.message);
		console.log(results);
}
```

# 查询

```jsx
db.query("select * from users",(err,results)=>{
			if(err) return console.log(err.message);
			console.log(results);
})
```

# 插入

```jsx
const user = {username:"name“,passward:"123456"};
const sqlStr = "insert into users (username,password) values (?,?)";
db.query(sqlStr,[user.username,user.password],(err,results)=>{
		if(err) return console.log(err.message);
		if(results.affectedRows===1){console.log(插入数据成功)};
})
```

### 简化

如果数据对象的每个属性和数据表的字段一一对应，则可以使用一下方式快速插入数据

```jsx
const user = {username:"username",password:"123456"};
const sqlStr = "insert into users set ?";
db.query(sqlStr,user,(err,results)=>{
		if(err) teture console.log(err.message);
		if(results.affectedRows===1) {console.log(插入数据成功)};
})
```

# 更新

```jsx
const user = {id:7,username:"aaaa",password:"000000"};
const sqlStr = "update users set username=?,password=? where id=?";
db.query(sqlStr,[user.username,user.password,user.id],(err,results)=>{
		if(err) return console.log(err.message);
		if(results.affectedRows===1) {console.log("更新数据成功")};
})
```

### 简化

```jsx
const user = {id:7,username:"aaaaaa",password:"123456"};
const sqlStr = "update user set ? where id=? ";
db.query(sqlStr,[user,user.id],(err,results)=>{
		if(err) return console.log(err.message);
		if(results.affectedRows===1) {console.log("数据更新成功")};
})
```

# 删除

```jsx
const = sqlStr = "delete from users where id=?";
db.query(sqlStr,7,(err,results)=>{
		if(err) return console.log(err.message);
		if(resulets.affectedRows===1) {console.log("数据删除成功")};
})
```

推荐使用标记删除，这样做更安全
