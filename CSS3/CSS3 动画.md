# CSS3 动画

```css
@keyframes myfirst
{
    from {background: red;}
    to {background: yellow;}
}
 
@-webkit-keyframes myfirst /* Safari 与 Chrome */
{
    from {background: red;}
    to {background: yellow;}
}

div
{
    animation: myfirst 5s;
    -webkit-animation: myfirst 5s; /* Safari 与 Chrome */
}
```

可以用“from”和“to”或者百分比来规定变化发生的时间。

0%是动画开始，100%是动画结束

```css
@keyframes myfirst
{
    0%   {background: red;}
    25%  {background: yellow;}
    50%  {background: blue;}
    100% {background: green;}
}
 
@-webkit-keyframes myfirst /* Safari 与 Chrome */
{
    0%   {background: red;}
    25%  {background: yellow;}
    50%  {background: blue;}
    100% {background: green;}
}
```

[CSS3的动画属性](CSS3%20%E5%8A%A8%E7%94%BB%2072a0b8310c634fc391a26d66cfb2406c/CSS3%E7%9A%84%E5%8A%A8%E7%94%BB%E5%B1%9E%E6%80%A7%20ea0e7dad76c942d6a7599922973ddc7b.csv)
