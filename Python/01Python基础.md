[toc]

# Python基础

## 数据类型

*   数字 Number
    *   整数 int
    *   浮点数 float
    *   复数 complex
    *   布尔 bool
*   字符串
*   列表
*   元组
*   集合
*   字典

>   注释
>
>   单行注释  # test
>
>   多行注释 “““

## 变量

~~~ python
money = 50
print("我的钱：",money)
~~~

python的变量没有类型，变量存储的值有类型，所以变量可以存储任何类型的值。

### 类型转换

*   int(x) 将x转换为一个整数
*   float(x) 将x转换为一个浮点数
*   str(x) 将x转换为一个字符串

 ### 字符串扩展

*   单引号定义
*   双引号定义
*   三引号定义 多行

### 字符串展位

~~~ python
name = "test";
message = "name = %s" % name   # 单个占位
old = 18
message = "name = %s , old = %s" % (name,old) # 多个占位
~~~

#### 字符串快速格式化

~~~ python
name = "test"
old = 18
print(f"name:{name},old:{old}")
~~~

## 数据输入 input

~~~ python
name = input()
~~~

## 逻辑控制

### if

~~~ python
age = 19
if age >=18:
	print("成年")
    print("成年")
else:
    print("未成年")
~~~

~~~ python
num = 9
if num >=0:
    print(num)
elif num<=0:
    print(num)
elif num > 100:
    print(num)
else:
    print(num)
~~~

### while

~~~ python
i = 0;
while i<100:
    print(i)
    i += 1;
~~~

### for

~~~ python
name = "test"
for x in name:
    print(x)
~~~

>   [!tip]
>
>   语法中，待处理数据集严格来说，称之为：序列类型
>
>   序列类型是指,其内容可以一个个取出的类型
>
>   *   字符串
>   *   列表
>   *   元组
>   *   等

### range

生成数字序列

#### 语法1 

range(num)

获取一个从0开始的，到num结束的数字序列(不含num本身)

如range(5)取得的数据是[0,1,2,3,4]

#### 语法2

range(num1,num2)

获得一个从num1开始的，到num2结束的数字序列，不含num2本身

#### 语法3

range(num1,num2,step)

获取一个从num1开始，到num2结束的数字序列，不含num2本身

数字之间的不长，以step为准，默认为1

## 函数

~~~ python
def test_fun(data):
    print(data)
test_fun("test")
~~~

>   [!tip]
>
>   def 函数名(传入参数):
>       函数体
>       return 返回值

~~~ python
def add(x,y):
    return x + y
~~~

如果不写返回值，则返回 `None`

### 函数文档

~~~ python
"""
add 函数可以接收两个参数，进行2数相加的功能
:param x: 一个数字
:param y: 另一个数字
:return: 两个数之和
"""
def add(x,y):
    return x + y
~~~

### 修改函数外部的变量

>   [!caution]
>
>   函数内部修改外部的变量，只能在函数内部使用，函数外部的变量没有改变，如果确实要改变函数外部的变量，可以用`global`关键字

~~~ python
num = 100
def test(){
    global num
    num = 200
}
~~~



