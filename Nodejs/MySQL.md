# 连接数据库

~~~ js
var mysql = require("mysql");
var connection = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "12581260",
  database: "test",
});
connection.connect();
connection.query("SELECT * from oUser", function (error, results, fields) {
  if (error) throw error;
  console.log(results[0]);
});

~~~

