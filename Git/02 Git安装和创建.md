# Git 安装和创建

## Linux 安装Git

~~~ shell
# 检查系统有没有安装Git
$ git
~~~

### Debian或者Ubuntu 

~~~ shell
sudo apt-get install git
~~~

### 其他Linux版本

从Git官网下载源码

~~~ shell
./config
make
sudo make install
~~~

## Windows 安装Git

从Git官网下载安装程序。

### 登录

~~~ shell
git config --global user.name "name"
git config --global user.email "email"
~~~

# 创建一个版本库

~~~ shell
git init
~~~

版本库可以跟踪文件的改动，比如txt，网页，代码，但是没法跟踪文件的变化，图片和微软Word无法跟踪改动。

>   :o: 不能使用Windows自带的**记事本**修改任何文件，因为记事本会在文件开头添加一个十六进制字符，会让代码发生错误。