# Git提交和回滚

## 提交

~~~ shell
git add readme.md
# 提交所有文件
git add *
git commit -m "提交信息"
~~~

##### 查看当前状态

 ~~~ shell
 git status
 # 查看有没有被修改但未提交的文件
 ~~~

##### 查看提交历史

~~~ shell
git log
~~~

`git log`命令显示从最近到最远的提交日志。

后面的字符串是`commit id`。

## 回滚



在git中，`HEAD`表示当前版本，上一个版本就是`HEAD^`,上上个版本就是`HEAD^^`。当想要往前10个版本，可以写成`HEAD~10`。

~~~ shell
git reset --hard HEAD^
~~~

如果想要回到未来的某个版本，要知道对应的`commit id` 

~~~ shell
git reset --hard [commit id]
~~~

#### 如果丢失了`commit id`

如果回滚到某个版本，然后关掉了电脑，再次打开电脑，用`git log`命令无法获得`commit id`。

~~~ shell
git reflog
# 输出每次执行的命令,可以找到commit id
~~~

 



