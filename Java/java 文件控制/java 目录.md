# java 目录

### 创建目录

> File file = new File("testtt01");boolean f =file.mkdir();
> 

- **mkdir( )**方法创建一个文件夹，成功则返回true，失败则返回false。失败表明File对象指定的路径已经存在，或者由于整个路径还不存在，该文件夹不能被创建。
- **mkdirs()**方法创建一个文件夹和它的所有父文件夹。

### 删除目录或文件

```java
File file = new File("testtt01");       
boolean a = file.delete();
```
