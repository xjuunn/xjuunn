# SpringBoot

## 基础代码

~~~ java
@SpringBootApplication
public class SpringTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}
}
~~~

~~~ java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
~~~



## SpringBoot 配置

* SpringBoot提供了两种配置文件类型：properteis和yml/yaml
* 默认配置文件名称：application
* 在同一级目录下优先级：properties>yml>yaml

## 读取配置文件的内容

* @Value
* Environment
* @ConfigurationProperties

### @Value

~~~ yaml
name: nameaaa
~~~

~~~ java
@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @RequestMapping("/hello")
    public String hello(){
        return "hello"+name;
    }
}
~~~

对象格式

~~~ yaml
person:
    age: 12
    name: 'zhangsan
~~~

~~~ java
@RestController
public class HelloController {

    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private int age;

    @RequestMapping("/hello")
    public String hello(){
        return "姓名："+name+"    年龄："+age;
    }
}
~~~

### Environment

~~~ java
@Autowired
private Environment env;
@RequestMapping("/hello")
public String hello(){
    return ""+env.getProperty("person.name");
}
~~~

### @ConfigurationProperties

~~~ java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
~~~

~~~ yaml
person:
    age: 12
    name: 'zhangsan'
~~~

~~~ java
@Autowired
private Person person;
@RequestMapping("/hello")
public String hello(){
    return person.toString();
}
~~~

## profile

开发SpringBoot应用时，通常同一套程序会被安装到不同环境，比如：开发、测试、生产等。其中数据库地址、服务器端口等等配置都不同。profile功能就是来进行动态配置切换的。

profile配置方式

* 多profile文件方式
* yml多文档方式

profile激活方式

* 配置文件
* 虚拟机参数
* 命令行参数

#### 激活其他配置文件

##### 多文件方式

> application-dev.properties

添加配置

~~~ yaml
spring.profiles.active=dev
~~~

##### 单文件方式

~~~ xaml
---
server:
    port: 8080
spring:
    profiles: dev
---
server:
    port: 8081
sprgin:
    profiles: test
---
server:
    port: 8082
spring:
    profiles: pro
---
spring:
    profiles:
        active: pro # 激活
~~~

### profile激活方式

* 配置文件：spring.profiles.active=dev
* 虚拟机参数：在VM.options指定：-Dspring.profiles.active=dev
* 命令行参数：java -jar xxx.jar —spring.profiles.active=dev

## 内部配置加载顺序

Springboot程序启动时，会从以下位置加载配置文件：

1. file:./config/:当前项目的config目录下
2. file:./当前项目的根目录
3. classpath:/config/:classpath的config目录下
4. classpath/:classpath的根目录

> 加载顺序为上文排列顺序，高优先级配置的属性会生效

 
