[toc]

# AOP面向切面

AOP: Aspect Oriented Programming(面向切面编程、面向方面编程)，其实就是面向特定方法编程

实现：动态代理是面向切面编程最主流的实现。而SpringAOP是Spring框架的高级技术，旨在管理bean对象的过程中，主要通过底层的动态代理机制，对特定的方法进行编程。

## AOP入门

导入aop的依赖

~~~ xml
<dependency>
	<groupId>org.springfram</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
~~~

针对于特定方法根据业务需要进行编程

~~~ java
@Component
@Aspect
@Slf4j
public class TimeAspect {
    @Around("execution(* com.itheima.service.*.*(..))") // 表达式
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed(); // 调用原始方法运行
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature()+"执行时间",end - begin);
        return object;
    }
}
~~~

*   JoinPoint:连接点，可以被AOP控制的方法，封装了执行时的相关 信息
*   Advice:通知，指哪些重复的逻辑，也就是共性功能(最终体现为一个方法)
*   PointCut:切入点，匹配连接点的条件，通知仅会在切入点方法执行时被应用

## 通知类型

1.   @Around:环绕通知，此注解标注的通知方法在目标方法前、后都被执行
2.   @Before:前置通知，此注解标注的通知方法在目标方法前被执行
3.   @After :后置通知，此注解标注的通知方法在目标方法后被执行，无论是否有异常都会执行
4.   @AfterReturning：返回后通知，此注解标注的通知方法在目标方法后被执行，有异常不会执行
5.   @AfterThrowing :异常后通知，此注解标注的通知方法发生异常后执行

~~~ java
@Component
@Aspect
public class MyAspect1 {
    @Before("excution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public void beforre() {
        // ...
    }
}
~~~

>   [!tip]
>
>   不同切面中，默认按照切面类的类名字母排序
>
>   *   目标方法前的通知方法：字母排名靠前的先执行
>   *   目标方法后的通知方法：字母排名靠后的先执行

## 切入点表达式

描述切入点方法的一种表达式

1.   excution(…):根据方法签名来匹配
2.   @annotation(…):根据注解匹配

### execution

根据方法的返回值、类名、方法名、方法参数信息了匹配

>   execution(访问修饰符?返回值 包名.类名.?方法名(方法参数) throws 异常?)
>
>   ?代表可以省略的部分