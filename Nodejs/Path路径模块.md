# Path路径模块

## Path路径模块

path模块是Node.js官方提供的、用来处理路径的模块。它提供了一系列的方法和属性，用来满足用户对路径的处理需求。

- paht.join()，将多个路径片段拼接成一个完整的路径字符串
- path.basename()，用来从路径字符串中，将文件名解析出来

> 导入
> 
> 
> const path = require(‘path’);
> 

### path.join 拼接路径片段

```js
const path = require("path");
const pathStr = path.join("/a","/b/c/d","../","e");
console.log(pathStr);// \a\b\c\e
```

凡是设计道路径拼接的操作，都要使用path.join()方法进行处理，不要直接使用+进行字符串拼接

### path.basename() 获取路径中的文件名

> path.basename(path[,ext])
> 
- path 必选参数，表示一个路径的字符串
- ext 可选参数，表示文件扩展名
- 返回：表示路径中的最后一部分

```js
const path = require("path");
const fpath = "/a/b/c/d/index.html";
var fullName = path.basename(fpath);
console.log(fullName);
//index.html
const fpath = "/a/b/c/d/index.html";
var fullName = path.basename(fpath,".html");
console.log(fullName);//index
```

### path.extname()获取文件扩展名

> path.extname(path)
> 
- path 必选参数，表示路径的字符串
- 返回：返回得到的扩展名字符串

```js
const path = require("path");
const fpath = "/a/b/c/d/index.html";
var fext = path.extname(fpath);
console.log(fext);//.html
```
