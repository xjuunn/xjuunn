## 创建Restful web 服务

~~~ java
    private static final String template = "你好,%s";
    private final AtomicLong aLong = new AtomicLong();

    @GetMapping("/hello")
    Greeting greeting(@RequestParam(value = "name",defaultValue = "陌生人")String name){

        return new Greeting(aLong.getAndIncrement(),String.format(template,name));
    }
~~~

>   AtomicLong 生成自动增长的Long类型ID