[toc]

# Rust 基础

## 变量

Rust变量默认是不可变的，可以用mut关键字让变量变得可变

``` rust
fn main(){
    let x = 15; // 不可变
    let mut y = 20; // 可变
}
```

>   如果创建了一个变量，但是没有在任何地方使用，Rust会发出一个警告，因为这可能是一个bug。如果希望告诉Rust不要警告这个未使用的变量，可以使用下划线作为变量名的开头。
>
>   ~~~ rust
>   fn main(){
>       let x = 5; // 警告
>       let _y = 5; // 不警告
>   }
>   ~~~

### 变量解构

从一个相对复杂的变量中，匹配出该变量的一部分内容

~~~ rust
fn main(){
    let (a,mut b):(bool,bool) = (true,false);
    println!("a={?},b={?}",a,b);
    
    b = true;
    assert_eq!(a,b);
}
~~~

### 解构赋值

可以在赋值语句左式中使用元组，切片和结构体模式了。

~~~ rust
struct Struct{
    e:i32
}
fn main(){
    let (a,b,c,d,e);
    (a,b) = (1,2);
    // _ 代表匹配一个值，但是不关心具体的值是什么
    [c,..,d,_] = [1,2,3,4,5];
    Struct {e,..} = Struct(e:5);
    assert_eq!([1,2,1,4,5],[a,b,c,d,e]);
}
~~~

>   <kbd>+=</kbd>的赋值语句还不支持解构赋值

 ### 常量

~~~ rust
cosnt MAX_POINTS:u32 = 100_000;
~~~

### 变量遮蔽

Rust允许声明相同的变量名，后面声明的变量会遮蔽掉前面的声明。

~~~ rust
fn main(){
    let x = 5;
    // 在main函数的坐于与内对之前的x进行遮蔽
    let x = x+1;
    {
        let x = x+2;
        println!("{}",x);
    }
    println("{}",x);
}
~~~

输出  12   6

这和mut变量的使用是不同的，使用let生成了一个完全不同的新变量，两个变量只是恰好拥有同样的名称，涉及一次内存对象的再分配。

## 基本类型

*   数值类型：有符号整数(i8,i16,i32,i64,isize)、无符号整数(u8,u16,u32,u64,usize)、浮点数(f32,f64)、以及有理数、复数
*   字符串：字符串字面量和字符串切片&str
*   布尔类型：true和false
*   字符类型：表示单个Unicode字符，存储为四个字节
*   单元类型：即(),其唯一的值也是()

### 类型推导与标注

Rust是一门静态类型的语言，编译器必须在编译期知道所有变量的类型，Rust会根据变量的值和上下文中使用方式，自动推导出变量的类型，但在某些情况下，需要手动介于一个类型标注。

~~~ rust
lst guess = "42".parse().expect("Not a number");
~~~

这段代码的目的是将字符串“42”进行解析，而编译器无法推导出想要什么类型，因此编译器会报错

因此要提供给编译器更多的信息，例如给变量一个显示的类型标注：`let guess:i32 = ...` 或者 `“42.parse::<i32>()”`。

### 数值类型

#### 整数类型

整数是没有小数部分的数字。i32表示有符号的32位整数，Rust默认使用i32

>   isize和usize类型取决于程序运行的计算机CPU类型：若CPU是32位的，则这两个类型是32位的，若CPU是64位的，那么它们则是64位的

#### 浮点类型

浮点类型是带有小数点的数字，默认是f64

#### NaN

对于数学上未定义的结果，例如对负数取平方根，会产生一个特殊的结果：Rust的浮点数类型使用NaN(not a number)来处理这些情况。

所有跟NaN交互的操作都会返回一个NaN，而且NaN不能用来比较，会使代码崩溃。

出于防御性编程的考虑，可以使用is_nan()等方法，可以判断一个数是不是NaN

~~~ rust
fn main(){
    let x = (-42.0_f32).sqrt();
    if x.is_nan() {
        println!("未定义的数字行为");
    }
}
~~~

#### 序列（Range）

Rust提供了一个非常简洁的方式，用来生成连续的数值，例如1..5，生成从1到4的连续数字，不包含5；1..=5，生成1到5的连续数字，包含5

~~~ rust
for i in 1..=5 {
    println!("{}",i);
}
~~~

>   序列只允许用于数字类型，原因是他们连续

### 单元类型

单元类型就是()，唯一的值也是()。

main函数的返回值就是单元类型()，可以用()作为map的值，表示不关注具体的值，只关注key，可以作为一个值用来占位，但是不占用任何内存。

### 表达式

表达式进行求值，然后返回一个值，例如`6+5`，在求值后返回值11，因此他就是一个表达式。

>   表达式不能包含分号
>
>   如果表达式不返回任何值，则隐式返回（）

~~~ rust
fn main() {
    assert_eq!(ret_unit_type(), ())
}

fn ret_unit_type() {
    let x = 1;
    // if 语句块也是一个表达式，因此可以用于赋值，也可以直接返回
    // 类似三元运算符，在Rust里我们可以这样写
    let y = if x % 2 == 1 {
        "odd"
    } else {
        "even"
    };
    // 或者写成一行
    let z = if x % 2 == 1 { "odd" } else { "even" };
}
~~~

### 函数

~~~ rust
fn add(i: i32, j: i32) -> i32 {
   i + j
 }
~~~

*   函数名和变量名使用蛇形命名法，例如fn add_two() -> {}
*   函数的位置可以随便放，Rust不关心在哪里定义了函数，只要定义了即可。
*   每个函数参数都需要标注类型。

#### 返回值

函数的最后一行表达式作为返回值，但是也可以用return关键字提前返回

## 所有权

程序释放内存的方式

*   垃圾回收机制(GC):在程序运行时不断寻找不再使用的内存（java，GO）
*   手动管理内存的分配和释放:在程序中，通过调用的方式来申请和释放内存（C++）
*   通过所有权来管理内存:编译器在编译时会根据一系列规则进行检查（Rust）

所有权原则

1.   Rust中每一个值都被一个变量所拥有，该变量被称为值得所有者
2.   一个值同时只能被一个变量所拥有，或者说一个值只能拥有一个所有者
3.   当所有者(变量)离开作用域范围时，这个值将被丢第

~~~ rust
let s1 = String::from("hello");
let s2 = s1;
~~~

当s1被赋予给s2后，rust认为s1不在有效，因此把所有权从s1转移给了s2，s1马上失效。

### 函数传值与返回

~~~ rust
fn main(){
    let s = String::from("hello"); // s进入作用域
    takes_overship(s); // s 的值移动到函数里。后面s将不再有效
    let x = 5；// x 进入作用域
    makes_copy(x); // 但是i32是copy的，所以后面可以继续使用
} // 这里，x先移出作用域，然后是s，但是s的值已经被移走，所以不会有特殊操作
fn takes_overship(some_string:String){ // some_string
    println!("{}",some_string);
} // 这里some_string 移出作用域并调用drop方法，占用的内存被释放
fn makes_copy(some_integer:i32) { // some_integer进入作用域
    println!("{}",some_integer);
} // some_integer移出作用域，不会有特殊操作
~~~

返回值也有所有权

~~~ rust
fn main() {
    let s1 = gives_ownership(); // gives_ovnership 将返回值移给s1
    let s2 = String::from("hello"); // s2进入作用域
    let s3 = takes_and_gives_back(s2); // s2被移动到takes_and_gives_back 中，将返回值移给s3.
}  // s3移出作用域并被丢弃。s2也移出作用域，但已被移走，所以什么也不会发生。s2移出作用域被丢弃。
fn gives_ownership() -> String { // gives_ownership 将放回置移动给调用它的函数
    let some_string = String::from("hello"); // some_string 进入作用域
    some_string // 返回some_string并移出给调用的函数
}
// takes_and_gives_back 将传入字符串并返回改值
fn takes_and_gives_back(a_string:String) -> String { // a_string 进入作用域
    a_string // 返回a_string并移出给调用函数
}
~~~

所有权避免了内存的不安全性，但是，总把一个值传来传去来使用，让语言表达变得啰嗦。可以使用`引用与借用来解决`

## 引用与借用

获取某个变量的引用，称之为**借用**

### 引用与解引用

常规引用是一个指针类型，指向了对象存储的内存地址

~~~ rust
fn main() {
    let x = 5;
    let y = &x;
    assert_eq(5,x);
    assert_eq(5,*y);
}
~~~

变量x存放了一个i32值5.y是x的一个引用。使用\*y解出来的值(解引用)。一旦解引用了，就可以访问y所指向的整型值并可以与5做比较。

### 不可变引用

~~~ rust
fn main() {
    let s1 = String::from("hello");
    let len = calculate_length(&s1);
    println!("{} {}",s1,len);
}
fn calculate_length(s:&String) -> usize {
    s.len();
}
~~~

1.   无需先通过函数参数传入所有权，然后在通过函数返回传出所有权，代码更加简洁
2.   calculate_length 的参数 s 类型从String 变成 &String

>   [!caution]
>
>   但是无法通过这个引用，来<del>修改借用的变量</del>，因为这是一个不可变引用。
>
>   ~~~ rust
>   fn main() {
>       let s = String::from("hello");
>       change(&s);
>   }
>   fn change(some_string:&String) {
>       some_string.push_str(",world");
>   }
>   ~~~

### 可变引用

~~~ rust
fn main() {
	let mut s = String::from("hello");
    change(&mut s);
}
fn change(some_string:&mut String) {
    some_string.push_str(",world");
}
~~~

首先声明s是可变的类型，其次创建可变引用`&mut s`和接受可变引用参数`some_string:&mut String`的函数

==可变引用只能存在一个==，同一个作用域，特定的数据只能有一个可变引用

>   [!caution] 
>
>   ~~~ rust
>   let mut s = String::from("hello");
>   let r1 = &mut s;
>   let r2 = &mut s;
>   println!("{} {}",r1,r2);
>   ~~~

编译器这样做的原因是为了避免数据竞争

*   两个或更多的指针同时访问同一个数据
*   至少有一个指针被用来写入数据
*   没有同步数据访问的机制

手动限制变量的作用域，来避免数据竞争

~~~ rust
let mut s = String::from("hello");
{
    let r1 = &mut s;
} // 这里r1离开了作用域，所以完全可以创建一个新的引用
let r2 = &mut s;
~~~

==可变引用与不可变引用不能同时存在==

~~~ rust
let mut s = String::from("hello");
let r1 = &s;
let r2 = &s;
let r3 = &mut s; // 报错，无法借用可变，因为它已经被借用不可变
~~~

为了防止数据污染

>   [!tip]
>
>   在新的编译器中，引用作用域的结束未知冲花括号变成最后一次使用的位置。这种编译器优化行为，称为**NLL**(**Non-Lexical Lifetimes**)。因此，在不可变引用最后一次使用后，可以创建可变引用。

### 悬垂引用

悬垂引用也叫做悬垂指针，意思是指针指向某个值后，这个值被释放了，但是指针仍然存在，其指向的内存可能不存在任何值或者已经被其他变量使用。

~~~ rust
fn main() {
    let reference_to_noting = dangle();
}
fn dangle() -> &String {
    let s = String::from("hello");
    &s;
}
~~~

报错：该函数返回了一个借用的值，但是已经找不到它所借用值的来源

解决：

~~~ rust
fn no_dangle() -> String {
    let s = String::from("hello");
    s   // String的所有权被转移给外面的调用者
}
~~~

>   [!tip]
>
>   *   同一时刻，只能拥有要么一个可变引用，要么任意多个不可变引用
>   *   引用必须总是有效

## 复合类型

#### 切片 (slice)

对于字符串而言，切片剧场对String类型的引用

~~~ rust
let s = String::from("hello world");
let hello = &s[0..5];
let world = &s[6..11];
~~~

**[开始索引..终止索引]**，其中开始索引是切片的第一个元素的索引未知，终止索引是最后一个元素后面的索引位置，也就是**左闭右开区间**。

从索引0开始

~~~ rust
let s = String::from("hello");
let slice = &s[0..2];
let slice = &s[..2]; // 这两个是等效的
~~~

包含到String的最后一个字符

~~~ rust
let s = String::from("hello");
let len = s.len();
let slice = &s[4..len];
let slice = &s[4..]; // 这两个是等效的
~~~

截取完整的String切片

~~~ rust
let s = String::from("hello");
let len = s.len();
let slice = &s[0..len];
let slice = &s[..]; // 这两个是等效的
~~~

>   [!caution]
>
>   在字符串中使用中文时，切片的索引必须落在字符之间的边界位置，也就是UTF8字符的边界，例如中文在UTF8占用三个字节
>
>   ~~~ rust
>   let s = "中国人";
>   let a = &s[0..2];
>   println!("{}",a);
>   ~~~
>
>   <span style="color:red">报错!</span> 因为只取s字符串的前两个字节，但是汉字占用三个字节，因此没有落在边界处，此时程序直接崩溃退出。

==字符串切片的类型是**&str**。==

>   [!warning]
>
>   当已经有了一个可变借用时，就无法在拥有不可变借用！
>
>   ~~~ rust
>   fn main(){
>       let mut s = String::from("hello world");
>       let word = first_word(&s);
>       s.clear();  // error
>       println!("{}",word);
>   }
>   ~~~
>
>   <span style="color:red">报错!</span> 因为clear需要一个可变借用，而之后的println!又需要一个不可变借用，因此编译无法通过。

#### 其他切片

切片是对集合的部分引用，其他集合类型也有切片

~~~ rust
let a = [1,2,3,4,5];
let slice = &a[1..3];
assert_eq!(slice,&[2,3]);
~~~

==字符串字面量是切片==

### String 字符串

Rust中的字符是Unicode类型，因此每个字符占据4个内存空间，但是字符串是不一样，字符串是UTF8编码，也就是说，字符串中的字符所占的字节数是变化的(1\-4)，这样有利于大幅降低所占的内存空间。

#### String和&str的转换

**&str->String**

*   String::from(“hello,world”);
*   “hello,world”.to_string();

**String->&str**

~~~ rust
fn main(){
    let s = String::from("hello,world");
    say_hello(&s);
    say_hello(&s[..]);
    say_hello();
}
fn say_hello(s:&str) {
    println!("{}",s);
}
~~~

#### 字符串索引

==无法在Rust中使用索引获取字符串的某个字符或字串==

字符串的底层数据存储是一个[u8]字节数组,对于英文来说，一个字母是一个字节，但是对于中文和一些其他语言的字符，一个字符占用三个字节，这种情况下，访问一个字节没有意义。

### 字符串操作

#### 追加 push

使用`push`方法追加字符char，也可以使用`push_str`方法追加字符串字面量。
这两种方法都是在原有的字符串上追加，不会返回新 的字符串，所以字符串变量必须由`mut`关键字修饰。

~~~ rust
let mut s = String::from("hello");
s.push_str("world");
s.push('!');
~~~

#### 插入 insert

*   insert 插入单个字符
*   insert_str 插入字符串字面量

需要两个参数

1.   插入位置的索引
2.   插入的字符(串)

~~~ rust
let mut s = String::from("hello rust!");
s.insert(5,',');
s.insert_str(6,"love");
~~~

#### 替换

##### replace

适用于String和&str类型，接收两个参数

1.   被替换的字符串
2.   新的字符串

该方法返回一个新的字符串，而不是操作原来的字符串

~~~ rust
fn main(){
    let string_replace = String::from("I like rust!!!")；
    let new_string_replace = string_replace.replace("rust","RUST");
}
~~~

##### replacen

前两个参数和replace一样，还接收第三个参数：替换的个数

##### replace_range

这个方法只适用于String类型，接收两个参数

1.   替换的字符串的范围
2.   新的字符串

该方法直接操作原来的字符串，不会返回新的字符串，需要mut关键字修饰。

~~~ rust
let mut string_replace_range = String::from("I like rust");
string_replace_range.replace_range(7..8,"R");
~~~

#### 删除 Delete

##### pop 删除并返回字符串的最后一个字符

**操作原来的字符串**。但是存在返回值，返回值是一个Option类型，如果字符串为空，则返回None

##### remove 删除并返回字符串中指定位置的字符

**操作原来的字符**。

##### truncate 删除字符串中从指定位置开始到结尾的全部字符

**直接操作原来的字符**。

##### clear 清空字符串

**操作原来的字符**。调用后，相当于truncate方法参数为0的时候

#### 连接 concatenate

##### 使用+或者+=连接字符串

使用+或者+=连接字符串，要求右边的参数必须为字符串的切片引用类型。当调用+的操作符时，相当于调用了std::string标准库中的`add`方法。第二个参数是一个引用类型。在使用+时，必须传递切片引用类型，不弄直接传递String类型，+是返回一个新的字符串。所以变量声明可以不需要使用mut关键字修饰。

~~~ rust
let string_append = String::from("hello ");
let string_rust = String::from("rust");
let result = string_append + &string_rust; // string_rust 的所有权被转移走了，后面不能再使用string_append
let mut result = result + "!"; // result + ! 中的result是不可变的
~~~

##### 使用format！ 连接字符串

适用于String和&str，用法和print!用法类似

~~~ rust
let s1 = "hello";
let s2 = String::from("rust");
let s = format!("{} {}",s1,s2);
~~~

### 字符串转义

可以通过转义的方式`\`输出ASCII和Unicode字符

~~~ rust
fn main() {
    // 通过 \ + 字符的十六进制表示，转义输出一个字符
    let byte_escape = "I'm writing \x52\x75\x73\x74!";
    println!("What are you doing\x3F (\\x3F means ?) {}", byte_escape);

    // \u 可以输出一个 unicode 字符
    let unicode_codepoint = "\u{211D}";
    let character_name = "\"DOUBLE-STRUCK CAPITAL R\"";

    println!(
        "Unicode character {} (U+211D) is called {}",
        unicode_codepoint, character_name
    );

    // 换行了也会保持之前的字符串格式
    // 使用\忽略换行符
    let long_string = "String literals
                        can span multiple lines.
                        The linebreak and indentation here ->\
                        <- can be escaped too!";
    println!("{}", long_string);
}
~~~

~~~ rust
fn main() {
    println!("{}", "hello \\x52\\x75\\x73\\x74");
    let raw_str = r"Escapes don't work here: \x3F \u{211D}";
    println!("{}", raw_str);

    // 如果字符串包含双引号，可以在开头和结尾加 #
    let quotes = r#"And then I said: "There is no escape!""#;
    println!("{}", quotes);

    // 如果还是有歧义，可以继续增加，没有限制
    let longer_delimiter = r###"A string with "# in it. And even "##!"###;
    println!("{}", longer_delimiter);
}
~~~

### 操作UTF8字符串

遍历Unicode字符

~~~ rust
for c in "中国人".chars() {
    println!("{}",c);
}
~~~

遍历字符串底层字节数组

~~~ rust
for b in "中国人".bytes() {
    println!("{}",b);
}
~~~

获取字串，可以使用 **utf8_slice** 库

### 元组

元组是由多种类型组合到一起形成的，元组的长度固定，元组中元素的顺序也是固定的。

~~~ rust
let tup:(i32,f64,u8) = (500,6.4,1);
~~~

#### 使用模式匹配解构元组

~~~ rust
let tup = (500,6.4,1);
let (x,y,z) = tup;
~~~

#### 使用`.`来访问元组

~~~ rust
let x:(i32,f64,u8) = (500,6.4,1);
let five_hundred = x.0;
let six_point_four = x.1;
let one = x.2;
~~~

>   [!tip]
>
>   可以使用元组再函数中返回多个值
>
>   ~~~ rust
>   fn calculate_length(s:String) -> (String,usize) {
>       let length = s.len();
>       (s,length)
>   }
>   ~~~
>
>   calculate_length函数接收第一个参数的所有权，然后计算长度，接着把字符串所有权和字符串长度返回给调用。

但是元组有一个巨大的缺陷：**不具备任何清晰的含义**

可以使用`元组结构体`来解决这个问题。

### 结构体

结构体和元组有些相像：都是由多种类型组合而成。但是与元组不同的是，结构体可以为内部的每一个字段起一个富有含义的名称。

*   通过关键字 `struct` 定义
*   一个清晰明确的结构体名称
*   几个有名字的结构体字段

~~~ rust
struct User {
    active:bool,
    username:String,
    emial:String,
    sign_in_count:u64
}
~~~

#### 创建结构体实例

~~~ rust
let user1 = User {
    emial:String::from("xjuunn@gmail.com"),
    username:String::from("Junhsiun"),
    active:true,
    sign_in_count:1
}
~~~

1.   初始化时，每个字段都需要初始化
2.   初始化时，字段顺序不需要和结构体定义时一致

#### 访问结构体字段

~~~ rust
let mut  user1 = User {
    ... // 省略了
}
user.email = String::from("xjuunn@163.com");
~~~

想要修改结构体实例的字段，必须将结构体声明为可变的。Rust不支持将结构体某个字段标记为可变。

#### 简化结构体创建

~~~ rust
fn build_user(email:String,username:String) -> User {
    User {
        email,   //  和js中一样
        username,
        active:true,
        sign_in_count:1,
    }
}
~~~

#### 结构体更新语法

根据已有的结构体实例，创建新的结构体实例

例如：根据已有的user1实例创建user2：

~~~ rust
let user2 = User {
    email:String::from("xjuunn@qq.com"),
    ..user1
}
~~~

只对email进行赋值，剩下的通过结构体更新语法..user1即可完成

`..`语法表明，凡是没有显式声明的字段，全部从user1中自动获取。需要注意的是，..user1必须在结构体尾部使用。

>   [!warning]
>
>   结构体更新语法和`=`非常相像，因此在上面的代码中，user1的部分字段的所有权被转移到了user2中：username字段发生了转移，作为结果，user1无法在被使用。
>
>   值得注意的是：username所有权转移给了user2，导致user1无法在被使用，但是不代表user1内部的其他字段不能被继续使用。

==把结构体中具有所有权的字段转移出去，将无法再访问该字段，但是可以访问其他字段==

#### 元组结构体

结构体必须要有名称，但是结构体的字段可以没有名称，这种结构体长得很像元组，因此被称为元组结构体。

~~~ rust
struct Color(i32,i32,i32);
struct Point(i32,i32,i32);
let black = Color(0,0,0);
let origin = Point(0,0,0);
~~~

元组结构体希望有一个整体的名称，但是又不关心字段名称。

#### 单元结构体

没有任何字段和属性的结构体

如果定义一个类型，但是不关心该类型的内容，只关心他的行为，就可以使用单元结构体。

~~~ rust
struct AlwaysEqual;
let subject = AlwaysEqual;
// 不关心AlwaysEqual的字段数据，只关心它的行为，因此将它声明为单元结构体，然后再实现他的某个特征
impl SomeTrait for AlwaysEqual {
    
}
~~~

#### 结构体数据的所有权

如果要在结构体中使用借用的数据，需要引入生命周期的概念，简而言之，生命周期能够确保结构体的作用范围要比借用的数据的作用范围小。详见生命周期。

#### 使用#[derive(Debug)]来打印结构体的信息

~~~ rust
#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

fn main() {
    let rect1 = Rectangle {
        width: 30,
        height: 50,
    };

    println!("rect1 is {:?}", rect1);
}
~~~

>   $ cargo run
>   rect1 is Rectangle { width: 30, height: 50 }

或者使用{:#?}可以纵向输出

>   rect1 is Rectangle {
>       width: 30,
>       height: 50,
>   }

也可以使用dbg!输出

~~~ rust
#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

fn main() {
    let scale = 2;
    let rect1 = Rectangle {
        width: dbg!(30 * scale),
        height: 50,
    };

    dbg!(&rect1);
}
~~~

>   $ cargo run
>   [src/main.rs:10] 30 * scale = 60
>   [src/main.rs:14] &rect1 = Rectangle {
>       width: 60,
>       height: 50,
>   }

### 枚举

通过列举可能的成员来定义一个枚举类型

~~~ rust
enum PokerSuit {
    Clubs,
    Spades,
    Diamonds,
    Hearts,
}
~~~

#### 枚举值

~~~ rust
let heart = PokerSuit::Hearts;
let diamond = PokerSuit::DIamonds;
~~~

使用

~~~ rust
fn main() {
    let heart = PokerSuit::Hearts;
    let diamond = PokerSuit::Diamonds;
    print_suit(heart);
    print_suit(diamond);
}
fn print_suit(card:PokerSuit) {
	// 需要在定义enum PokerSuit的上面添加 #[derive(Debug)] 否则报card没有实现Debug
    println!("{:?}",card)
}
~~~

#### 带有类型的枚举值

~~~ rust
enum PokerCard {
    Clubs(u8),
    Spades(u8),
    Diamonds(char),
    Hearts(char),
}
fn main() {
    let c1 = PokerCard::Spades(5);
    let c2 = PokerCard::Diamonds('A')
}
~~~

==任何类型的数据都可以放入枚举成员人中==

~~~ rust
enum Message {
    Quit,
    Move {x,i32,y:i32},
    Write(String),
    ChangeColor(i32,i32,i32),
}
fn main() {
    let m1 = Message::Quit;
    let m2 = Message:Move[x:1,y:1];
    let m3 = Message::ChangeColor(255,255,0);
}
~~~

*   Quit 没有任何关联数据
*   Move 包含一个匿名结构体
*   Write 包含一个字符串
*   ChangeColor 包含三个i32

### 数组

Rust中，最常用的数组有两种

1.   array 数组 固定长度 速度快
2.   Vector 动态数组 动态增长 性能低

array数组：多个类型相同的元素一次排列

*   长度固定
*   元素必须有相同的类型
*   依次线性排列

#### 创建数组

~~~ rust
fn main() {
    let a = [1,2,3,4,5];
}
~~~

>   [!tip]
>
>   *   array 是存储在栈上的
>   *   Vector 是存储在堆上的

#### 为数组声明类型

~~~ rust
let a: [i32;5] = [1,2,3,4,5];
~~~

*   i32 是元素类型
*   5 是数组长度

还可以使用语法初始化一个某个重复出现n次的数组

~~~ rust
let a = [3;5]; // 包含五个元素，值都是3
~~~

>   [!caution]
>
>   如果要在数组中初始化创建多个复杂类型
>
>   ~~~ rust
>   let array = [String::from("rust");8];
>   ~~~
>
>   <span style="color:red">报错: </span>let array = [3;5] 底层是通过copy出来的，但是只有基本类型才能copy，复杂类型没有深拷贝，只能一个个创建
>
>   比较优雅的解决方法
>
>   ~~~ rust
>   let array:[String;8] = std::array::from_fn(|_i| String::from("rust"));
>   ~~~

#### 访问数组元素

~~~ rust
fn main() {
    let a = [5,4,3,2,1];
    let first = a[0];
    let second = a[1];
}
~~~

#### 数组切片

~~~ rust
let a:[i32;5] = [1,2,3,4,5];
let slice:&[i32] = &a[1..3]
~~~

上面的数组切片slice的类型是&[i32]，与之对比，数组的类型是[i32;5]

*   且前的长度可以与数组不同，并不是固定的，而是取决于使用时指定的起始位置和结束为主
*   创建切片的代价非常小，因为切片只是针对底层数组的一个引用
*   切片类型[T]拥有不固定的大小，而切片引用类型&[T]则具有固定的大小，因为Rust很多时候都需要固定大小的数据类型，因此&[T]更有用，&str字符串切片也是同理

