# YAML

YAML全称是YAML Ain‘t Markup Language。YAML是一种直观的能够被电脑识别的数据库数据序列化格式，并且容易被人类阅读，容易和脚本语言交互，可以被支持YAML库的不同的编程语言程序导入，比如：C/C++，Python，Ruby，Java，Perl，C#，PHP等。YML文件是以数据库为核心的，比传统XML方式更加简洁。

YAML文件的扩展名可以使用.yml或.yaml

##### properties

~~~ properties
server.port=8080
server.address=127.0.0.1
~~~

##### xml

~~~ xml
<server>
	<port>8080</port>
    <address>127.0.0.1</address>
</server>
~~~

##### yml

~~~yaml
server:
	port: 8080
	address: 127.0.0.1
~~~

## YAML基本语法

* 大小写敏感
* 数据值的前面必须有空格，作为分隔符
* 使用缩进表示层级关系
* 缩进时不允许使用Tab键，只允许使用空格（各个系统tab对应的空格数目可能不同，导致层次混乱）
* 缩进的空格数目不重要，只要相同层级的元素左侧对其即可
* \# 表示注释，从这个字符一直到行尾，都会被解析器忽略

## YAML数据格式

### 对象 map 键值对的集合

~~~ yaml
person:
    name: zhangsan
person: {name: zhangsan}
~~~

### 数组 一组按次序排列的值

~~~ yaml
address:
    - beijing
    - shanghai
address: [beijing,shanghai]
~~~

### 纯量 单个的、不可再分的值

~~~ yaml
msg1: 'hello \nworld' # 单引号忽略转义字符
msg2: "hello \nworld" # 双引号识别转义字符
~~~







