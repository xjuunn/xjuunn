[toc]

# 方法 Method

Rust方法往往跟结构体、枚举、特征一起使用

## 定义方法

Rust 使用 `impl` 来定义方法

~~~ rust
struct Circle {
    x:f64,
    y:f64,
    radius:f64,
}
impl Circle {
    // new 是 Circle 的关联函数，因为它的第一个参数不是self，且new并不是一个关键字
    // 这种方法往往用于初始化当前结构体的实例
    fn new(x:f64,y:f64,radius:f64) -> Circle {
        Circle {
            x:x,
            y:y,
            radius,radius,
        }
    }
    fn area(&self) -> f64 {
        std::f64::consts::PI * (self.radius * self.radius)
    }
}
~~~

~~~ rust
#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }
}

fn main() {
    let rect1 = Rectangle { width: 30, height: 50 };

    println!(
        "The area of the rectangle is {} square pixels.",
        rect1.area()
    );
}
~~~

>   [!tip]
>
>   `&self`是`self: &Self`的简写

## self、&self 和 &mut self

*   `self`表示所有权转移到方法中，使用的比较少
*   `&self`表示对实例的不可变引用
*   `&mut self`表示可变借用

## 带有多个参数的方法

~~~ rust
impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }
    fn can_hold(&self,other:&Rectangle) -> bool {
        self.width > other.width && self.height > other.height
    }
}
fn main() {
    let rect1 = Rectangle {width:30,height:50};
    let rect2 = Rectangle {width:10,height:40};
    let rect3 = Rectangle {width:60,height:45};
    println!("{}",rect1.can_hold(&rect2));
    println!("{}",rect1.can_hold(&rect3));
}
~~~

## 关联函数

定义在`impl`中且没有`self`的函数称之为**关联函数**：因为它没有self，不能用`.`的形式调用，因此它是一个函数而不是方法，它又在`impl`中，与结构体紧密关联，因此称为关联函数。

~~~ rust
impl Rectangle {
    fn new(w:u32,h:u32) -> Rectangle {
        Rectangle { width:w, height:h }
    }
}
~~~

>   [!tip]
>
>   Rust有一个约定俗成的规则，使用`new`来作为构造器的名称，出于设计考虑，Rust特地没有用new作为关键字。

因为是函数，所有不能用`.`的方法调用，需用用`::`来调用，例如`let sq = Rectangle::new(3,3)`。这个方法位于结构体的命名空间中:`::`语法用于关联函数和模块创建的命名空间。

## 多个impl定义

Rust允许为一个结构体定义多个`impl`块，目的是提供更多的灵活性和代码组织性，当方法多了后，可以把相关的方法组织到同一个`impl`块，各自完成一块儿目标

~~~ rust
impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }
}
impl Rectangle {
    fn can_hold(&self,other:&Rectangle) -> bool {
        self.width > other.width && self.height > other.height
    }
}
~~~

## 为枚举实现方法

枚举不仅可以同一化类型，还可以像结构体一样，为枚举实现方法

~~~ rust
#![allow(unused)]
enum Message {
    Quit,
    Move {x:i32,y:i32},
    Write(String),
    ChangeColor(i32,i32,i32),
}
impl Message {
    fn call(&self) {
        // 定义方法
    }
}
fn main() {
	let m = Message::Write(String::from("hello"));
    m.call();
}
~~~

除了结构体和枚举，还可以为特征实现方法

