# JS正则表达式

## 正则表达式

- 正则表达式是由一个字符序列形成的搜索模式
- 当在文本中搜索数据时，可以用搜素模式来描述要查询的内容
- 正则表达式可以是一个简单的字符，或者一个更复杂的模式
- 正则表达式可以用于所有文本搜索和文本替换的操作

### 语法

> /正则表达式主题/修饰符（可选）
> 

```
var parr = /abcde/i
```

/abcde/i 是一个正则表达式，abcde是一个正则表达式的主体，用于检索，i是一个修饰符

### search()

```
var str = "Visit Runoob!";var n = str.search(/Runoob/i);// > 6
```

### replace()

```
var str = document.getElementById("demo").innerHTML;var txt = str.replace(/microsoft/i,"Runoob");// > Visit Runoob!
```

### 正则表达式修饰符

- i 执行对大小写不敏感的匹配
- g 执行全局匹配
- m 执行多行匹配

### 正则表达式模式

- 表达式
    - [abc]查找方括号之间的任何字符
    - [0-9]查找任何从0至9的数字
    - (x|y)查找任何以|分隔的选项
- 元字符
    - 
    - 查找空白字符
    - 
    - 查找以十六进制数xxxx规定的Unicode字符
- 量词
    - n+ 匹配任何包含至少一个n的字符串
    - n* 匹配任何包含零个或者多个n的字符串
    - n? 匹配任何包含零个或者一个n的字符串

### RegExp对象

### test()

test()方法是一个正则表达式的方法

test()方法用于检测一个字符串是否匹配 某个模式，如果字符串中包含有匹配的字符，则返回true，否则返回false。

搜索字符串中的“e”

```
var patt = /e/;patt.test("the");> true
```

可以将以上代码合并为一行

> /e/.test(“the”)
> 

### exec()

exec()方法是一个正则表达式的方法

exec()用来检测字符串中的正则表达式的匹配

函数返回一个数组，其中存放匹配的结果。如果未找到，则返回值为null

搜索字母“e”

```
/e/.exec("the");> e
```
