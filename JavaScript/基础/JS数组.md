[toc]

# JS数组

### 获取页面元素：

```jsx
- id：document.getElementById()
- 标签名tagname：document.getElementsByTagName(‘标签名’);
- name属性：document.getElementsByName(‘name属性’);
- class：document.getElementsByClassName(‘class属性);
```

### 定义数组

```jsx
- var ar = [,,];
- var ar = [];
- var ar = new Arrary();
- var ar new Array(3);
- var ar = new Array;
```

### 数组赋值

```jsx
arr[0] = "test";
arr[1] = 1;

arr = ["test",0]
console.log(arr);
```

### 数组的访问

```jsx
arr[1]
```

### 数组属性

```jsx
arr.length // 数组长度
arr.constructor //返回数组的构造方法
```

### 数组方法

```js
arr.**indexOf**("c") //获得元素的索引
arr1.**lastIndexOf**("a")//获得元素最后出现的位置  ****

arr1.**concat**(arr2,arr3) //连接多个数组

arr2.**copyWithin**(3,0,1) //复制指定元素替换旧的元素
//copyWithin(复制到的指定位置[,复制的起始位置,复制的结束位置])

//检测数组的元素是否都满足要求，返回bool
arr2 = ['1','2','3','4','5','6','7']
function checkSize(ele){
		return ele<=7;
}
console.log(arr2.**every**(checkSize));//true
//array.every(function(当前元素的值[,当前元素的索引,当前数组])[,回调传值给函数])

arr2.**fill**("aa")//使用固定值来填充函数
//array.fill("填充的值"[开始填充的位置,停止填充的位置])

//返回符合要求的值
arr2 = ['1','2','3','4','5','6','7']
function checkSize(ele){
    return ele>=4;
}
console.log(arr2.**filter**(checkSize));//['4','5','6','7']
//array.filter(function(当前元素的值[当前元素的索引,当前数组])[,回调传值给函数])

//返回符合条件的第一个元素
arr2 = ['1','2','3','4','5','6','7']
function **checkSize**(ele){
    return ele>=4;
}
console.log(arr2.**find**(checkSize));//4，参数同上
console.log(arr2.**findIndex**(checkSize));// 索引

//为数组每个元素执行函数
arr1 = ["a","b","c","d","e","f","g","h","i","g"]
function logele(item,index){
    console.log(item+":"+index);
}
console.log(arr1.**forEach**(logele));

// 将给定对象转换成数组
var arr3 = Array.from(["a","1"])
var arr3 = Array.from(["a","1"],x=>x+"a")//为每个数组执行函数
Array.**from**(要转换的数组对象[每个元素执行的函数,映射函数中的this对象])

arr2.**includes**(3)//检测数组中是否包含某个对象
//第二个参数，其实检测的索引值

Array.**isArray**(arr2)//判断该对象是否是一个数组

arr2.**join**()//将数组转换成字符串
arr2.**toString**()//将数组转换成字符串

//将旧数组的每个元素执行函数，返回一个新的数组
arr2 = [1,2,3,4,5,6,7]
function addOne(ele){
    return ele+1
}
console.log(arr2.**map**(addOne));
//arr2.**map**(x=>x+1)

arr2.**pop**();//删除数组的最后一个元素，返回被删除的元素
arr2.**shift**()//删除并返回数组的第一个元素

arr2.**push**("9")//数组末尾追加元素，返回新数组的长度
arr2.**unshift**("a")//数组开头添加元素，返回新数组的长度

//将数组计算成一个值
arr2 = [1,2,3,4,5,6,7]
function jsum(total,sum){
    return total+sum;
}
console.log(arr2.**reduce**(jsum));//从左向右
console.log(arr2.**reduceRight**(jsum));//从右向左

arr2.**reverse**()//反转数组的顺序

//截取数组返回一个新的数组，不影响原始数组
arr2.**slice**(1,3)//返回索引为[1,5)的数组
arr2.**slice**(-2)//截取最后两个元素

arr2.**some**(checkSize);//检测数组中元素是否满足条件

arr2.**sort**()//将数组进行排序，将会影响原始数组

//删除或添加数组
arr2 = [1,8,3,4,5,6,7]
console.log(arr2.**splice**(1,3,0,0,0));//返回删除的元素
console.log(arr2)
Array.**splice**(从何处添加或删除元素[删除的元素个数,添加的新元素]);
```
