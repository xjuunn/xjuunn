[toc]

# MyBatis

## 1. 创建项目

勾选

1.   Spring Web
2.   MyBatis Framework
3.   MySQL Driver

## 2. 连接数据库

### 在Database中配置连接数据库

#### application.yml配置

~~~ yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/carbon
    username: root
    password: 123456
~~~

## 3. 使用

#### pojo

自动生成pojo类: 

右键数据表->Tools->Scripted Extensions->Generate POJOs.groovy

保存到对应目录

#### Mapper

~~~ java

@Mapper
public interface UserMapper {
    @Select("select * from atestuser where id = #{id}")
    public Atestuser findUserById(long id);
}
~~~

#### Service

~~~ java
public interface UserService {
   public Atestuser findUserById(long id);
}
~~~

#### ServiceImpl

~~~ java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Atestuser findUserById(long id) {
        return userMapper.findUserById(id);
    }
}
~~~

#### Controller

~~~ java
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/findUserById")
    public Atestuser findUserById(Integer id){
        return userService.findUserById(id);
    }
}
~~~

>   [!tip]
>
>   插入数据时获取数据的ID
>
>   ~~~ java
>   @Options(userGeneratedKeys = true, keyProperty = "id")
>   @Insert("insert into ...")
>   public void insert(Emp emp);
>   ~~~

>   [!tip]
>
>   如果在查询时，数据实体类属性名和数据表查询返回字段名不一致，不能自动封装
>
>   1.   解决方法1: 给select语句的字段起别名，别名和字段名保持一致
>
>   2.   解决方法2: 手动映射 
>
>        ~~~ java
>        @select("select ...")
>        @Results({
>            @Result(column = "dept_id",property=
>        deptId),
>        })
>        public Emp getById(Integer id);
>        ~~~
>
>   3.   解决方法3: 启用自动驼峰命名映射
>
>        ~~~
>        mybatis.configuration.map-underscore-to-camel-case=true
>        ~~~

>   [!tip]
>
>   如果sql语句中需要查询字符串，但是字符串中无法使用`#{}`的格式，可以使用使用`concat()`函数拼接字符串
>
>   ~~~ sql
>   select * from emp where name like concat('%',#{name},'%')
>   ~~~

## 使用XML映射文件查询

src/main/resources/org/junhsiun/Mapper/UserMapper.xml  ==同包同名==

~~~ xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.junhsiun.Mapper.UserMapper">
    <select id="getUserList" resultType="org.junhsiun.pojo.User">
        select * from user where id = 20
    </select>
</mapper>
~~~

## 动态SQL

### if

~~~ xml
<mapper namespace="com.junhsiun.UserMapper">
	select * from user
    <where>
    	<if test="name != null">
        	name like concat('%',#{name},'%')
        </if>
        <if test="gender != null">
        	and gender = #{gender}
        </if>
        <if test="begin != null and end != null">
    		and entrydate between #{begin} and #{end}    
        </if>
    </where>
    order by update_time desc
</mapper>
~~~

>   [!tip]
>
>   使用where标签，会自动的删除多余的`and`和`where`
>
>   set标签会自动删除多余的`,`逗号

### foreach

~~~ xml
<delete id="deleteByIds">
	delete from emp where id in
    <foreach collection="ids" item="id" separator="," open="(" close=")">
    	#{id}
    </foreach>
</delete>
~~~

*   collection 遍历的集合
*   item 遍历出来的元素
*   separator 分隔符
*   open 遍历开始前拼接的sql片段
*   close 遍历结束狗拼接的sql片段

### sql和include

~~~ xml
<mapper namespace="com.junhsiun.mapper.UserMapper">
	<sql id="commonSelect">
    	select id,username,password,name,gender,job
    </sql>
    <select id="list" resulType="com.junhsiun.pojo.User">
    	<include refid="commonSelect"/>
        <where>
        	....
        </where>
    </select>
</mapper>
~~~

