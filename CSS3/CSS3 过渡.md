# CSS3 过渡

CSS3中，我们为了添加某种效果可以从一种样式转变到另一个的时候，无需使用Flash动画或JavaScript。

- 指定要添加效果的CSS属性
- 指定效果的持续时间。

```css
div
{
    transition: width 2s;
    -webkit-transition: width 2s; /* Safari */
}
```

📡多项改变用”,”分隔。

# **CSS3 transition-timing-function 时间曲线**

```css
transition-timing-function: linear;
-webkit-transition-timing-function: linear; /* Safari and Chrome */
```

[时间曲线参数](CSS3%20%E8%BF%87%E6%B8%A1%20e6c344fb89534dc09d1bd9de933b9255/%E6%97%B6%E9%97%B4%E6%9B%B2%E7%BA%BF%E5%8F%82%E6%95%B0%20ba8e2e7ad83c46fa969f00024cc5e97c.csv)

# **CSS3 transition-delay 开始时间**

```css
transition-delay: 2s;
-webkit-transition-delay: 2s; /* Safari */
```

> transition-delay: *time；*
>
