# CS 1.委托

### 基本使用

```C#
public delegate void MyDel(string str);
//定义委托
public void doTest(string str)//定义委托的方法
{   
    MessageBox.Show("aaa"); 
    button1.Text = str;
}
MyDel mydel = new MyDel(doTest);
//创建委托的对象
mydel("aaaaa");
//调用委托的方法
```

### 委托的多播

```C#
static void Main(string[] args){  
    // 创建委托实例 
    NumberChanger nc;  
    NumberChanger nc1 = new NumberChanger(AddNum);    
    NumberChanger nc2 = new NumberChanger(MultNum);  
    nc = nc1; 
    nc += nc2;
    // 调用多播   
    nc(5);  
    Console.WriteLine("Value of Num: {0}", getNum()); 
    Console.ReadKey();
}
```
