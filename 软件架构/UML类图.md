# UML类图

![30分钟学会UML类图](https://picx.zhimg.com/v2-941a070601f399d992125ef31261637e_720w.jpg?source=172ae18b)

## 具体类

具体类用**矩形框**表示，矩形有三层

* 第一层：类名
* 第二层：成员变量
* 第三层：方法

成员变量和方法的访问修饰符用符号表示

* ‘+’表示public
* ‘-’表示private
* ‘#’表示protected
* 不写表示default

![img](https://pic4.zhimg.com/80/v2-71b22158f5b09dffa57a123d72ec4653_720w.webp)

## *抽象类*

抽象类的类名和方法名用***斜体***表示

![img](https://pic2.zhimg.com/80/v2-5c69cd9ff703377f7bbf37cee8199451_720w.webp)

## 接口  \<\<interface\>\>

接口第一层用 \<\<interface\>\>表示，下面是接口名

![img](https://pic2.zhimg.com/80/v2-e39bdff5514c38e7797848372ac51365_720w.webp)

## 包

![img](https://pic3.zhimg.com/80/v2-b421c9c15219feba7dd9cf7681070682_720w.webp)



## 类图的表示关系

![img](https://pic4.zhimg.com/80/v2-e6a48521352fff8270e753ea4a79d9fb_720w.webp)

### 实现关系 implements

接口和实现类之间的关系

![img](https://pic1.zhimg.com/80/v2-616c153ec74d496a811ac50c83c3653c_720w.webp)

### 泛化关系 extends

对象与对象的继承关系

![img](https://pic4.zhimg.com/80/v2-04064db11797cf36229d67407fea1b83_720w.webp)

### 关联关系 

对象和对象之间的连接，它使一个对象知道另一个对象的属性和方法。
表现形式为一个对象含有另一个对象的引用

> 关联关系有：单向关联和双向关联

![img](https://pic4.zhimg.com/80/v2-3f331f3dc075abb4215413014688638f_720w.webp)

### 依赖关系

依赖关系是一种弱关联关系。

> 如果A用到对象B，但是和B的关系不是很明显的时候，可以把这种关系看作是依赖关系。

![img](https://pic1.zhimg.com/80/v2-431e044bbf26778a20dd788968e22aac_720w.webp)

> 依赖关系在java种具体体现为：B为A的**构造器**或方法种的**局部变量**、**方法**或**构造器的参数**，**方法的返回值**，或A调用B的**静态方法**

### 聚合关系

体现的是整体与部分的关系

此时整体与部分是可以分离的，他们可以具有各自的生命周期，部分可以属于多个整体对象，也可以为多个整体对象共享

![img](https://pic4.zhimg.com/80/v2-82cb160ed1566d1d04ea320575b54d5f_720w.webp)

### 组合关系

体现整体与部分间的关系

此时整体与部分不可分，整体也不能给其他整体共享，作为整体的对象负责部分的对象的生命周期。

> 这种关系比聚合更强，也成为了强聚合

![img](https://pic1.zhimg.com/80/v2-e651244eeca9c0881cbdd2add429e850_720w.webp)

















