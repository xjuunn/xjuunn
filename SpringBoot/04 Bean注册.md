[toc]

# Bean 注册

| 注解        | 说明                 | 位置                                        |
| ----------- | -------------------- | ------------------------------------------- |
| @Component  | 声明bean的基础注解   | 不属于一下三类时，用此注解                  |
| @Controller | @Component的衍生注解 | 标注再控制类上                              |
| @Service    | @Component的衍生注解 | 标注再业务类上                              |
| @Repository | @Component的衍生注解 | 标注再数据访问类上(由于mybatis整合，用的少) |

## 第三方jar包的Bean注册

*   @Bean
*   @Import

### @Bean

~~~ java
@Bean  // 带有@Bean注解的方法，会自动把返回值注入进IOC容器
public Country country(){
    return new Country();
}
// 获取注入的对象
public static void main (String[] args){
    ApplicationContext context = SpringApplication.run(SpringbootRegisterApplication.class,args);
    Country country = context.getBean(Country.class);
    system.out.println(country);
}
~~~

#### 应用

使用配置类注册

~~~ java
public class CommonConfig{
    @Bean
    public Country country(){
        return new Country();
    }
    // 对象名默认为：方法名
    // @Bean("name") //自动逸对象名
    @Bean
    public Province province(){ //如果依赖其他的类，直接参数传入
        return new Province();
    }
}
~~~



~~~ java
public static void main(String[] args){
	ApplicationContext context = SpringApplication.run(SpringbootRegisterApplication.class,args);
    //通过类名获取
    Country country = context.getBean(Country.class);
    //通过对象名获取
    Province province = context.getBean("province");
}
~~~

### @Import

1.   导入 配置类
2.   导入 ImportSelector 接口实现类

