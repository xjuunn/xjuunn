# node代码

### 输出目录中所有的目录

```jsx
const fs = require("fs");
fs.readdir('./',function(err,files){
    //声明一个数组存储目录下的所有文件夹
    var floder = [];
    //从数组的第一个元素开始遍历数组
    (function iterator(i){
        //遍历数组files结束
        if(i==files.length){
            console.log(floder);
            return;
        }
        //遍历查看目录下所有东西
        fs.stat('./'+files[i],function(err,stats){
            //如果是文件夹，就放入存放文件夹的数组中
            if(stats.isDirectory()){
                floder.push(files[i]);
            }
            iterator(i+1);
        })
    })(0)})
```

### 输出目录中的所有文件

```jsx
const fs = require('fs');
const path = require('path');
fs.readdir("C:\\Users\\oDlim\\OneDrive\\Markdown笔记\\PHP",function (err,files){
    console.log(files);
})
```

### 递归获取文件目录下所有文件（包括子目录）

```jsx
var fs = require('fs');
var path = require('path');
var exec = require('child_process').exec;
function readFileList(dir, filesList = []) {
    const files = fs.readdirSync(dir);
    console.log(files);
    files.forEach((item, index) => {
        var fullPath = path.join(dir, item);
        const stat = fs.statSync(fullPath);
        if (stat.isDirectory()) {
            readFileList(path.join(dir, item), filesList);  //递归读取文件
        } else {
            filesList.push(fullPath.split("markdown")[1]);
        }
    });
    return filesList;
}
var filesList = [];
const root = path.join(__dirname,"../../","assets","markdown")
readFileList(root,filesList)
;console.log(filesList);
```

### 转换成js

~~~ js
const fs = require("fs");
const path = require('path');
const readDir = (entry,mfname) => {
    const dirInfo = fs.readdirSync(entry);
    var dircontent = new DirTree();
    dircontent.files = [];
    dircontent.dirs = [];
    dircontent.name = mfname;
    for (let i = 0; i < dirInfo.length; i++) {
        const location = path.join(entry,dirInfo[i]);
        const info = fs.statSync(location);
        if(info.isDirectory()){
            dircontent.dirs.push( readDir(location,dirInfo[i]));
        }else{
            dircontent.files.push(dirInfo[i]);
        }
    }
    return dircontent;
}
function DirTree(name,files,dirs){
    this.name = name;
    this.files = files;
    this.dirs = dirs;
}
var docsdir = readDir(path.join(__dirname,"../../public/docs/"));
var json = JSON.stringify(docsdir);
fs.writeFile(__dirname+"/dirdata.js","var dirdata="+json+";\nexport {dirdata};",function(err){
    if (err!=null) console.log(err);
})
console.log('执行完毕');
~~~



### Json字符串转换

```jsx
JSON.parse()//将json字符串转换为js数据
JSON.stringify()//将对象，数组转换成json字符串
```

