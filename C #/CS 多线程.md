# CS 多线程

## 多线程

### 获取当前所有已开启线程

```C#
Process[] peocesses = Process.GetProcesser();
DataTable dt = new DataTable();
dt.Columns.Add(new DataColumn("进程ID"));
dt.Columns.Add(new DataColumn("进程名称"));
for(int i =0;i<processes.Length;i++){  
    DataRow dr = dt.NewRow();  
    dr.ItemArray = new string[]{processes[i].Id.ToString,processes[i].ProcessName};
    dt.Rows.Add(dr);}dgv1.DataSource =dt;
```

### 打开进程

```C#
Process[] processes = new Process();
process.StartInfo.FileName = @"explorer.exe";
process.StartInfo.Arguments = @"D:\";
process.Start();
```

### 关闭进程

```C#
 string pName = dgv1.CurentRow.Cells[1].Value
```

### 跨线程操作UI

- Invoke(Delegate method)【同步】
- BeginInvoke(Delegate method)【异步】

> Action委托，无返回值，可以有参数，参数的个数为0-16
> 
> 
> Action<int,string> action;
> 
> Func委托,有返回值，参数个数为0-16个，最有一个是返回值类型
> 
> Func func1;表示返回值是int
> 
> Func<int,string,int>;表示参数是int和string，返回值是int
> 

### Lambda表达式

Lambda表达式本质上是一个匿名的方法，是一个方法极端简化的写法，不能独立存在，需要依附于委托

>  多线程互斥
> 

多个线程同时访问一个变量，会导致变量值发生不可控的变化，从而引起信息处理错误。因此多线程程序必须对关键性变量才需__排他性措施__，即多个子线程访问同一个变量时，任一时刻都只能有一个线程能访问到该资源（其他线程等待该线程访问完毕后再访问）

可以使用lock关键字实现多线程的互斥，lock将标记出一块区域，该区域中的代码语句被一个线程访问期间，其他线程无法访问该代码语句。

> lock(this){ //this是一个两个线程都能访问到的对象*
> 





