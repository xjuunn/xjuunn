[toc]

# Controller





## 获取前端传来的参数

~~~ java
@RequestMapping("/hello")
public String hello(String name){
    return ""+name;
}
~~~

> url：[127.0.0.1:8080/hello?name=test](http://127.0.0.1:8080/hello?name=test)

参数名称不相同

~~~ java
@RequestMapping("/hello")
public String hello(@RequestParam(value = "name",required = false) String nickname){
    return ""+nickname;
}
~~~

post请求接收参数

~~~ java
@PostMapping("/Post")
public static String doPost(String name){
    return "name:"+name;
}
~~~

> [127.0.0.1:8080/hello?name=aab](http://127.0.0.1:8080/hello?name=aab)
>
> 使用x-www-form-urlencoded传参

使用json传参

~~~ java
@PostMapping("/Post3")
public static Person doPost3(@RequestBody Person person){
    return person;
}
~~~

传对象

~~~ java
@PostMapping("/Post2")
public static Person doPost2(Person person){
    return person;
}
~~~

> ~~~ json
> {
>     "name": "testa",
>     "phone": "123123"
> }
> ~~~

通配符

> /\*\* 响应多级任意字符  
>
> /\* 响应一级任意字符

## 获得路径上的参数

~~~ java
@GetMapping("/user/{id1}")
public String getUser(@PathVariable("id1") int id){
    return id+"";
}
~~~

