# java Stream流

### 控制台读取多字符输入

```java
public class Maina {
    public static void main(String[] args) throws IOException {      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        System.out.print("按q键退出");      
        char c ;   
        do {      
            System.out.print("正在运行。。。");     
            c= (char)br.read();
        }while (c!='q');
        //读取到q后退出循环  
    }}
```

### 控制台读取字符串

```java
public class Maina { 
    public static void main(String[] args) throws IOException {    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
        String str;   
        System.out.print("回车 行");   
        System.out.print("回车 'end' 退出");
        do {         
            str = br.readLine();      
            System.out.print(str);
        }while(!str.equals("end"));  
    }}
```

---

> BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));System.out.print((char)reader.read());
> 

> BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));System.out.print(reader.readLine());
> 

```java
import java.io.*;public class Main { 
    public static void main(String[] arg){  
        String filename = "write-filename.txt";  
        try {           
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write("This is the first String1\n");  
            out.close();     
            out = new BufferedWriter(new FileWriter(filename, true));    
            out.write("This is the second String2\n");  
            out.close();    
            BufferedReader in = new BufferedReader(new FileReader(filename));         
            String str;        
            // 输出文件内容         
            while ((str = in.readLine()) != null) {      
                System.out.println(str);       
            }            
            in.close(); 
        } catch (IOException e) {  
            System.out.println("exception occoured" + e);   
        }    
    }}
```

简化

```java
String filename = "write-filename.txt";
try { 
    BufferedWriter out = new BufferedWriter(new FileWriter(filename,true));  
    out.write("This is the first String1q\n"); 
    out.close(); 
    out = new BufferedWriter(new FileWriter(filename, true));//z
    out.write("This is the second Stringw2\n"); 
    out.close();
} catch (IOException e) {}
```
