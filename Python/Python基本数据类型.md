[toc]

# PY 基本数据类型

### Python3 基本数据类型

Python 中的变量不需要声明。每个变量在使用前都必须赋值，变量赋值以后才会被创建。

在Python中，变量就是变量，它没有类型，我们所说的“类型”是变量所指的内存中对象的类型。

等号(=)用来给变量赋值。

等号(=)运算符左边是一个变量名，等号右边是储存在变量中的值。

```python
counter = 100       
# 整型变量
miles   = 1000.0  
# 浮点型变量
name    = "runoob"    
# 字符串
print (counter)
print (miles)
print (name)
```

### 多个变量赋值

Python 允许同时为多个变量赋值。

```python
a = b = c = 1
```

以上实例，创建一个整型对象，值为1，从后向前复制，三个变量被赋予了相同的数值。

也可以为多个对象指定多个变量

```python
a,b,c = 1,2,"aaa"
```

以上实例，两个整型对象1和2的分配给变量a和变量b，字符串对象分配给变量c。

---

### 标准数据类型

Python3 中有六个标准的数据类型：

- Number（数字）
- String （字符串）
- List （列表）
- Tuple （元组）
- Set （集合）
- Dictionary （字典）

Python3 的六个数据类型中：

- 不可变数据：Number、String、Tuple
- 可变数据：List、Dictionary、Set

---

### Number （数字）

Python3 支持int、 float、bool、complex（复数）。

在Python3 里，只有一种整数类型，表示为长整数，没有python2中的long。

像大多数语言一样，数值类型的赋值和计算都是很直观的。

内置的type()函数可以用来查询变量所指的对象类型。

Python3 中，bool是int的子类，True和False可以和数字相加，True\=\=1，False\=\=0会返回True，但可以通过is来判断类型。

当指定一个值时，Number对象就会创建

```python
var1 = 1var2 = 10
```

也可以使用del语句删除一些对象的引用

del语句的句法是

```python
del var1[,var2[,var3[...,varN]]]
```

可以通过使用del语句删除单个或多个对象

```python
del vardel var_a,var_b
```

### 数值运算

```python
>>> 5 + 4  # 加法9
>>> 4.3 - 2 # 减法2.3
>>> 3 * 7  # 乘法21
>>> 2 / 4  # 除法，得到一个浮点数0.5
>>> 2 // 4 # 除法，得到一个整数0
>>> 17 % 3 # 取余2
>>> 2 ** 5 # 乘方32
```

- python可以同时为多个变量赋值，如a,b = 1,2
- 一个变量可以通过复制指向不同类型的对象。
- 数值的除法包含两个运算符:/返回一个浮点数，//返回一个整数。
- 在混合计算时，python会把整型转换成为浮点数。
  
    ###### 数值类型实例
    
    [Untitled](PY%20%E5%9F%BA%E6%9C%AC%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B%2058fda3041ae44dff8f5009ca0a38747e/Untitled%20Database%20783d509630bf4bde991c351724f5b05b.csv)
    
    Python还支持复数，复数由实数部分和虚数部分构成，可以用a + bj,或者complex(a,b)表示， 复数的实部a和虚部b都是浮点型
    
    ---
    
    ###### String 字符串

python中的字符串用单引号或者双引号括起来，同时使用反斜杠转义特殊字符

---

### List（列表）

list是python中使用最频繁的数据类型。

列表可以完成大多数集合的数据结构实现。列表中元素可以不相同，它支持数字，字符串甚至可以包含列表(所谓嵌套)。

列表是写在方括号[]之间、用逗号隔开的元素列表。

和字符串一样，列表同样可以被索引和截取，列表被截取后返回一个包含所需元素的新列表

列表截取的语法格式如下

```python
变量[头下标:尾下标]
```

- List写在方括号之间，元素用逗号隔开。
- 和字符串一样，list可以被索引和切片。
- List可以使用+操作符进行拼接。
- List中的元素是可以改变的。

---

### Tuple(元组)

元祖与列表类似，不同之处在于元组的元素不能修改。元组写在小括号()里，元素之间用逗号隔开。元组中的元素类型也可以不相同：

```python
tuple = ( 'abcd', 786 , 2.23, 'test', 70.2  )
tinytuple = (123, 'test')
print (tuple)		    # 输出完整元组
print (tuple[0])		# 输出元组的第一个元素
print (tuple[1:3])        # 输出从第二个元素开始到第三个元素
print (tuple[2:])         # 输出从第三个元素开始的所有元素
print (tinytuple * 2)     # 输出两次元组
print (tuple + tinytuple) # 连接元组
```

构造包含0个或1个元素的元组比较特殊，所以有一些额外的语法规则

```python
tup1 = () # 空元组
tup2 = (20,) # 一个元素，需要在元素后面添加逗号
```

string、list和tuple都属于sequence(序列)。

- 与字符串一样，元组的元素不能修改。
- 元素也可以被索引和切片
- 注意构造包含0或1个元组的特殊语法规则
- 元组也可以使用+操作进行拼接

---

### Set（集合）

集合（set）是由一个或数个形态各异的大小整体组成的，构成集合的事物或对象称作元素或成员。

基本功能是进行成员关系测试和删除重复元素。

可以使用大括号{}或者set()函数创建集合，创建一个空集合必须用set()而不是{},因为{}是用来创建一个空字典。

创建格式

```python
parame = {value01,value02,....}
```

```python
sites = {'Google', 'Taobao', 'Runoob', 'Facebook', 'Zhihu', 'Baidu'}
print(sites)   # 输出集合，重复的元素被自动去掉
# 成员测试
if 'Runoob' in sites :
    print('Runoob 在集合中')
else :
    print('Runoob 不在集合中')
# set可以进行集合运算
a = set('abracadabra')
b = set('alacazam')
print(a)
print(a - b)     # a 和 b 的差集
print(a | b)     # a 和 b 的并集
print(a & b)     # a 和 b 的交集
print(a ^ b)     # a 和 b 中不同时存在的元素
```

---

### Dictionary （字典）

字典是python中另一个非常有用的内置数据类型

列表是有序的对象集合，字典是无序的对象集合。两者之间的区别在于：字典当中的元素是通过键来存取的，而不是用过偏移存取。

自电视一种映射类型，字典用{}表示，他是一个无序的**键（key）：值（value）**的集合。

键必须使用不可变类型。

在同一个字典中，键必须是唯一的。

```python
dict = {}
dict['one'] = "1 - 菜鸟教程"
dict[2]     = "2 - 菜鸟工具"
tinydict = {'name': 'runoob','code':1, 'site': 'www.runoob.com'}
print (dict['one'])       # 输出键为 'one' 的值
print (dict[2])           # 输出键为 2 的值
print (tinydict)          # 输出完整的字典
print (tinydict.keys())   # 输出所有键
print (tinydict.values()) # 输出所有值
```

构造函数dict()可以直接从键值对序列中构建字典如下：

```python
>>> dict([('Runoob', 1), ('Google', 2), ('Taobao', 3)]){'Runoob': 1, 'Google': 2, 'Taobao': 3}
>>> {x: x**2 for x in (2, 4, 6)}{2: 4, 4: 16, 6: 36}
>>> dict(Runoob=1, Google=2, Taobao=3){'Runoob': 1, 'Google': 2, 'Taobao': 3}
```

{x: x**2 for x in (2,4,6)}该代码使用的是字典的推导式 ：[Python 推导式](https://www.runoob.com/python3/python-comprehensions.html)

另外，字典类型也有一些内置的函数，例如clear(),keys(),valuses()等。

- 字典是一种映射类型，他的元素是键值对。
- 字典的关键字必须为不可变类型，且不能重复。
- 创建空字典使用。
  
    { }
