[toc]

# PY 基础语法1

### 注释

```python
# 单行注释用井号表示
print("Hello,Python!")
```

### 行与缩进

python最具特色的就是使用缩进来表示代码块，不需要使用大括号｛｝

缩进的空格数是可变的，但是同一个代码块的语句不许包含相同的缩进空格数

```python
if True:
    print ("True")
if False:
    print ("False")
```

缩进不一致会导致运行错误

### 多行语句

python通常是一行写完一条语句，但如果语句很长，可以使用反斜杠\来实现多行语句

```python
total = item_one + \
item_two + \
item_three
```

在[],{},()中的语句，不需要使用反斜杠\

```python
total = ['ttem_one','item_two','item_three',
         'item_four','item_five']
```

### 数字(Number)类型

python中数字有四种类型：整数，布尔型，浮点数和复数。

- int（整数），如1，只有一种整数类型int，表示为长整数，没有python2中的long
- bool（布尔），如True
- float（浮点数），如1.23、3E-2
- complex（复数），如1+2j、1.1+2.2j

### 字符型（String）

- python中单引号‘和双引号“使用完全相同
- 使用三引号‘’‘或者“”“可以指定一个多行字符串。
- 转义符\。
- 反斜杠可以用来转义，使用r可以让反斜杠不发生转义。如**r“this is a line with ”** 则 *** 会显示，并不是换行。
- 按字面意义级联字符串，如“this” “is” “string” 会被自动转换为this is string。
- 字符串可以用+运算符连接在一起，用运算符重复。
- python中的字符串有两种索引方式，从左往右以0开始，从右往左以1开始。
- python中的字符串不能改变。
- python没有单独字符类型，一个字符就是长度为1的字符串。
- 字符串的截取的语法格式：变量[头下标:尾下标:步长]
  
    ```python
    word = '字符串'
    sentence = '这是一个句子'
    paragraph = """这是一个段落，可以由多行组成"""
    ```
    
    ```python
    #!/usr/bin/python3
    str='123456789'print(str)                 
    # 输出字符串
    print(str[0:-1])           
    # 输出第一个到倒数第二个的所有字符
    print(str[0])              
    # 输出字符串第一个字符
    print(str[2:5])            
    # 输出从第三个开始到第五个的字符
    print(str[2:])            
    # 输出从第三个开始后的所有字符
    print(str[1:5:2])         
    # 输出从第二个开始到第五个且每隔一个的字符（步长为2）
    print(str * 2)            
    # 输出字符串两次
    print(str + '你好')         
    # 连接字符串
    print('------------------------------')
    print('hello\nrunoob')      
    # 使用反斜杠(\)+n转义特殊字符
    print(r'hello\nrunoob')     
    # 在字符串前面添加一个 r，表示原始字符串，不会发生转义
    ```
    
    ###### 空行
    
    函数之间或者类的方法之间用空行分隔，表示一段新的代码的开始。类和函数入口之间也用一行空行分隔，以突出函数入口的开始。
    
    空行与代码缩进不同，空行并不是python语法的一部分。书写时不插入空行，python解释器运行也不会出错。但是空行的作用在于分隔两端不同功能或含义的代码，便于日后代码的维护或重构。（空行也是程序代码的一部分）
    
    ###### 等待用户输入
    
    执行下面的程序在按回车键后就会等待用户输入：
    
    ```python
    input ("\n\n按下enter键后退出。")
    ```
    
    以上代码中，\n\n在结果输出前会输出两个新的空行。一旦用户按下enter键时，程序将退出。
    
    ###### 同一行显示多条语句
    
    python可以在同一行中使用多条语句，语句之间使用分号;分割
    
    ```python
    import sys; x = 'rra'; sys.stdout.write(x+'\n')
    ```
    
    ###### print输出
    
    print默认输出是换行的，如果要实现不换行需要在变量末尾加上end=“”：
    
    ```python
    #!/usr/bin/python3x="a"y="b"# 换行输出print( x )print( y )print('---------')# 不换行输出print( x, end=" " )print( y, end=" " )print()
    ```
    
    ###### import 与 from…import
    
    在python用import或者form…import来导入相应的模块
    
    将整个模块(somemodule)导入，格式为：import somemodule
    
    从某个模块中导入某个函数，格式为：form somemodule import somefunction
    
    从某个模块中导入多个函数，格式为：from somemodule import firstfunc, secondfunc, thirdfunc
    
    将某个模块中的全部函数导入,格式为：from somemodule import *
    
    导入sys模块
    
    ```python
    import sysprint('================Python import mode==========================')print ('命令行参数为:')for i in sys.argv:    print (i)print ('\n python 路径为',sys.path)
    ```
    
    导入sys模块的argv.path成员
    
    ```python
    from sys 
    import argv,path  
    #  导入特定的成员
    print('================python from import===================================')
    print('path:',path) 
    # 因为已经导入path成员，所以此处引用时不需要加sys.path
    ```
