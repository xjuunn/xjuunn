# java File流

### FileInputStream 输入流

```java
public class Maina { 
    public static void main(String[] args) throws IOException {     
        InputStream in = new FileInputStream("C:\\Users\\oDlim\\Desktop\\java流\\aaa.txt");   
        int size = in.available();  
        for (int i = 0; i < size; i++) {           
            System.out.print((char)in.read()+" ");  
        }  
    }}
```

> 可以使用字符串类型的文件名来创建一个输入流对象来读取文件：
> 
> 
> > InputStream f = new FileInputStream(“C:/java/hello”);
> > 
> 
> 也可以使用一个文件对象来创建一个输入流对象来读取文件。我们首先得使用 File() 方法来创建一个文件对象：
> 
> > File f = new File(“C:/java/hello”); InputStream in = new FileInputStream(f);
> > 

### FileOutputStream 输出流

> OutputStream os = new FileOutputStream("C:\\Users\\oDlim\\Desktop\\javaio\\11.txt");byte[] by = {50,51,53,51,52,53,55,55,55,51};os.write(by);
