## iterator

遍历字符串

~~~ js
const str = "123123 456456 789789"
const iterator = str[Symbol.iterator]();
console.log(iterator.next());//1
console.log(iterator.next());//2
console.log(iterator.next());//3
~~~

## at

获取下标对应的字符

~~~ js
const str = "123"
console.log(str.at(0))//1
//负值表示从末尾开始
console.log(str.at(-1))//3
~~~

## charAt

获取下标对应的字符

~~~ js
const str = "123"
console.log(str.charAt(0))//1
//不允许使用负数
~~~

## charCodeAt

获取对应索引的Unicode值

~~~ js
const str = "123"
console.log(str.charCodeAt(0))//49
~~~

## codePointAt

获取对应索引的Unicode值

~~~ js
const str = "123"
console.log(str.codePointAt(2))
~~~

## concat

拼接字符串

~~~ js
const str = "123"
const str1 = "456"
console.log(str1.concat(' ',str))//456 123
console.log(str.concat(',',str1))//123，456
console.log(str.concat('1','2','3',str,str,str1))//123123123123456
~~~

## endsWith

判断是否为结尾字符串

~~~ js
const str = "123,456 789"
console.log(str.endsWith('6 789'))//true
console.log(str.endsWith('78'))//fasle
console.log(str.endsWith('123'))//false
console.log(str.endsWith('123',3))//true  指定结尾位置
~~~

## fromCharCode

从UTF-16转换为字符

~~~ js
console.log(String.fromCharCode(97,98,99))//abc
~~~

## fromCodePoint

从UTF-16转换为字符

~~~ js
console.log(String.fromCodePoint(97,98,99))//abc
~~~

## includes

搜索字符串

~~~ js
const str = "123,456 789"
console.log(str.includes('123'))//true
console.log(str.includes('123',3))//false  开始搜索的位置
~~~

## indexOf

搜索字符串第一次出现的位置

~~~ js
const str = "123,456 789"
console.log(str.indexOf('123'))//0
console.log(str.indexOf('789'))//8
~~~

## lastIndexOf

字符串最后一次出现的位置

~~~ js
const str = "123 456 789"
console.log(str.indexOf(' '))//3
console.log(str.lastIndexOf(' '))//7
~~~



[String.prototype.localeCompare() - JavaScript | MDN (mozilla.org)](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/String/localeCompare)

















