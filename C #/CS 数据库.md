# CS 数据库

## 数据库

核心类：Connection、Command、DataReader、DataAdapter

重要类：DataSet、DataTable

关键控件：DataGridView

断开式：Command

非断开式：DataAdapter

### 连接数据库

```C#
//"server=  ; user id=  ;password=   ;dataase= "   s
//"Data Source= 计算机名;Integrated Security=true;Initial Catalog= 数据库名" Windows身份验证SqlConnection connection = new SqlConnection("用户名和密码信息");
connection.Open();
connection.Close();
```

- A 在app.config中增加connectionStrings配置
- B 在.cs文件中引入命名空间 using System.Configuration;
- C 在项目上添加对System.Configuration.dll 的引用
- D 使用ConfigurationManager.ConnectionStrings[“connStr”].ConnectionString获取数据库连接字符串

### DataTable

```C#
DataTable dt = new DataTable();
dt.Columns.Add(new DataColumn("学号"));
dt.Columns.Add(new DataColumn("姓名"));
dt.Columns.Add(new DataColumn("性别"));
for(int i = 0;, i<10; i++){
    DaataRow dr = dt.NewRow();
    dr.ItemArray = new string[]{$"{i},$张三{i}，“男"};   
    dt.Rows.
}
```

### 使用DataAdapter对象执行数据库命令

从数据源检索数据填充到Dataset中

### DataSet数据集类

相当于在内存中模拟出的数据库，是多个DataTable的集合

假设Dataset对象为ds，则访问数据集中国的数据表的方法为ds.Table[i]

```C#
SqlConnection conn = null;
SqlDataAdapter adapter = null;
DataSet ds = null;//全局
conn = new Sqlconnction('');
//查询
string sql = "sql语句";
adapter =new SqlDataAdapter(sql,conn);
ds = new DataSet();
adapter.Fill(ds,"studentinfo");
//把查询结果保存到ds对象中
dgv1.DataSource = ds.Tables["studentinfo"];//["0"]
//增
string num = tbxNum.Text;
string name = tbxName.Text;
int isex = rbmale.checked?1:0;
SqlCommandBuilder builder = new SqlCommandBuilder(adapter);
DataTable dt = ds.Tables[0];//内存数据库（dataset）中的表
DataRow dr = dt.NewRow();dr.ItemArray = new string[]{
    num,name,isex.ToString()
};
dt.Rows.Add(dr);
adapter.Update(ds,"studentinfo");
//改
Sql CommandBuilder builder = new SqlCommandBuilder(dataper);
DataTable dt = ds.Tables[0];
int rowIdx = dgv1.CurrentRow.Index;
dt.Rows[rowIdx][0] = num;adapter.Update(ds,"studentinfo");
//删
DataTable dt = dev1.C
```

- 如果数据表中没有主键，调用Update会报错

SqlDataAdapter

查

```C#
string sql = "select * from stus";
adapter = new SqlDataAdapter(sql, conn);
ds = new DataSet();
adapter.Fill(ds, "stuss");
dgv.DataSource = ds.Tables["stuss"];
```
