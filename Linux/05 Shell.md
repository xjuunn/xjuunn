[toc]

# Shell

Shell是linux系统的命令行界面，它是用户与操作系统内核之间的接口。

## 运行Shell脚本

~~~ shell
chmod +x test01.sh # 给脚本添加运行权限
./test01.sh # 运行脚本
~~~

## 变量

### 变量类型

1. 局部变量：只能在当前Shell进程中有效
2. 环境变量：可被子进程继承的变量
3. 特殊变量：Shell预定义的特殊用途的变量

~~~ shell
a=1 # 定义一个变量
echo $a # 使用变量
~~~

变量命名规则

1. 只能包含字母、数字、和下划线
2. 不能数字开头
3. 通常使用大写字母表示环境变量，小写字母表示局部变量
4. 避免使用Shell关键字

#### 常用的环境变量

* PATH：可执行文件搜索路径
* HOME：当前用户主目录
* USER：当前用户名
* SHELL：当前Shell路径
* PWD：当前工作目录
* UID：当前用户的ID

#### 特殊变量

* $0：当前脚本名称
* \$1 - \$9：脚本参数
* $#：参数个数
* $*：所有参数，作为一个整体
* $@：所有参数，作为独立字符串
* $?：上一个命令的退出状态
* $$：当前Shell的进程ID
* $!：最后一个后台进程的PID

### 变量输出

~~~ shell
hello=world # 设置变量
echo $hello # 直接输出
> world
echo "hello = ${hello}" # 使用双引号 + ${} 输出变量
> hello = world
echo 'hello = ${hello}' # 单引号原样输出
> hello = ${hello}
~~~

### 命令替换

~~~shell
echo "当前时间：`date`" # 使用反引号 ``，可将执行结果输出
> 当前时间：Mon Jun 16 18:20:50 CST 2025
~~~

### 变量值输入

~~~ shell
read # 让用户输入
read -p "输入用户名" # 用户输入前提示用户
~~~

## 表达式

#### 算术表达式

用户执行数学运算

~~~ shell
# 使用$(( )) 语法
echo $((3 + 5))
echo $((10 - 2))
# 使用 let 命令
let "result = 5 + 3"
echo $result
# 使用 expr 命令
expr 5 + 3
~~~

#### 条件表达式

测试条件是否成立

~~~ shell
test 5 = 5
echo $? # 0 ，$?输出上一句是否执行成功，0表示成功
test 5 = 6
echo $? # 1 ，1表示失败
[ 9 = 9 ] # 使用[]来进行测试
echo $? # 0
# 使用[[ ]]，进行测试，支持模式匹配和逻辑运算
[[ 5 -gt 3 && 3 -lt 10 ]]
~~~

> [!caution]
>
> 等号和运算符前后必须要有空格
>
> 使用[ ]进行判断时，[ ]内的开头和结尾也要有空格

### 数值比较运算符

| 运算符 | 规则说明                                |
| ------ | --------------------------------------- |
| -gt    | 左边是否大于右边 > (greater than)       |
| -lt    | 左边是否小于右边 < (less than)          |
| -eq    | 是否相等 == (equal)                     |
| -ne    | 是否不等 != (not equal)                 |
| -ge    | 是否大于等于 >= (greater than or equal) |
| -le    | 是否小于等于 <= (less than or equal )   |

## 流程控制

### 分支结构

####  if

~~~ shell
if [ 条件 ]; then

elif [ 条件 ]; then

else 

fi
~~~

eg

~~~ shell
read -p "请输入年龄：" age
if [ $age -lt 18 ]; then
	echo "未成年"
elif [ $age -ge 18 ] && [ $age -lt 60 ]; then
	echo "成年人"
else 
	echo "老年人"
fi
~~~

#### case

~~~ shell
case 变量 in
	模式1)
		# 匹配模式1时执行命令
		;;
	模式2)
		# 匹配模式2时执行命令
		;;
	*)
		#匹配模式3时执行命令
		;;
esac
~~~

eg

~~~ shell
read -p "输入水果名称" fruit
case $fruit in
	apple)
		echo "苹果"
		;;
	banana|orange)
		echo "香蕉或橘子"
		;;
	*)
		echo "未知水果"
		;;
~~~

#### select 菜单选择

~~~ shell
select 变量 in 列表; do
	# 选择处理
	break;
done
~~~

eg

~~~ shell
#!/bin/bash
PS3="请选择您喜欢的水果: "
select fruit in apple banana orange quit; do
    case $fruit in
        quit)
            break
            ;;
        *)
            echo "您选择了: $fruit"
            ;;
    esac
done
~~~

### 循环控制

#### for

~~~ shell
for 变量 in 列表; do
	# 循环体
done
~~~

#### while

~~~ shell
while [ 条件 ]; do
	# 循环体
done
~~~

#### until

~~~ shell
until [ 条件 ]; do
	# 循环体
done
~~~

#### 循环控制命令

* break：跳出当前循环
* continue：跳过当前循环，进入下次循环

