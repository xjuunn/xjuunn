# MyBatis

连接数据库

~~~ properties
spring:
  datasource:
    url: jdbc:mysql:///testdatabase?serverTimezone=UTC
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
~~~

## Select

Application添加注解

> @MapperScan("com.example.batistesta.mapper")

添加mapper接口

~~~ java
@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> find();
}
~~~

Controller

~~~ java
@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user")
    public List getUser(){
        List<User> list = userMapper.find();
        return list;
    }
}
~~~

> User类必须和表对应