# fs文件系统模块

## fs文件系统模块

fs模块是node官方提供的、用来操作文件的模块。它提供了一系列的方法和属性，用来满足用户对文件操作的需求

- fs.readFile()方法，用来读取指定文件中的内容
- fs.writeFile()方法，用来指定的文件中写入内容

如果要在JavaScript代码中使用fs模块来操作文件，则需要使用如下方式先导入它

> const fs = require(‘fs’);
> 

### 读取指定文件的内容

### fs.readFile()

> fs.readFile(path[,options],callback)
> 
- 参数1：必选参数，字符串，表示文件的路径
- 参数2：可选参数，表示以什么编码格式来读取文件
- 参数3：必选参数，文件读取完成后，通过回调函数拿到读取的结果

```js
const fs = require('fs');
fs.readFile("PHP基础.md",'utf8',function(err,datastr){
    console.log(err);
    console.log(datastr);
})
```

判断读取是否成功

```js
const fs = require('fs');
fs.readFile("PHP基础.md",'utf8',function(err,datastr){
    if (err){
        return  console.log(err);
    }
    console.log('datastr')
})
```

### 向指定文件中写入内容

> fs.writeFile(file,data[,option],callback)
> 
- 参数1：必选参数，需要指定一个文件路径的字符串，表示文件的存放路径
- 参数2：必选参数，表示要写入的内容。
- 参数3：可选参数，表示以什么格式写入文件内容，默认值是utf8
- 参数4：必选参数，文件写入完成后的回调函数

```js
const fs = require('fs');
fs.writeFile('./files/3.txt',"abcde",function(err){
    console.log(err);
})
```

### 路径拼接问题

解决方法 __dirname：当前js文件所处的绝对目录
