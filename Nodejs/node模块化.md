# node模块化

## 模块化

- 内置模块
- 自定义模块
- 第三方模块

### 模块作用域

在自定义模块中定义的变量，方法等成员，只能在当前模块中被访问，这种模块级别的访问限制，叫做**模块作用域**

### 向外共享模块作用域中的成员

### module对象

在每一个.js自定义模块中，都有一个module对象，它里面存储了和当前模块有关的信息。

### module.exports

在自定义模块中，可以使用module.exports对象，将模块内的成员共享出去，供外界使用

外界用require()方法导入自定义模块时，得到的就是module.exports所指向的对象。

### 向外共享成员

> //自定义模块
>
> module.exports.username = 'zhang';module.exports.sayhello = function(){      console.log('Hello!');}
>
>
> ```js
> //外界访问模块成员
> const m = require('./自定义模块');console.log(m);
> ```
>

使用require()方法导入模块时，导入的结果，永远以module.exports指向的对象为准。

```js
module.exports = {
    nickname:'小黑',
    sayHi(){
        console.log('Hi');
    }
}
```

### exports对象

由于module.exports单词写起来比较复杂，为了简化向外共享成员的代码，Node提供了exports对象。默认情况下，exports和module.exports指向同一个对象。最终共享的结果，还是以module.exports指向的对象为准。
