# CSS3 2D转换

CSS3 转换可以对元素进行移动，缩放，旋转，拉长或者拉伸

# translate()移动

从当前位置移动到目标位置

```css
div
{
transform: translate(50px,100px);
-ms-transform: translate(50px,100px); /* IE 9 */
-webkit-transform: translate(50px,100px); /* Safari and Chrome */
}
```

# rotate()旋转

```css
div
{
transform: rotate(30deg);
-ms-transform: rotate(30deg); /* IE 9 */
-webkit-transform: rotate(30deg); /* Safari and Chrome */
}
```

# **scale()缩放**

```css
div
{
-ms-transform:scale(2,3); /* IE 9 */
-webkit-transform: scale(2,3); /* Safari */
transform: scale(2,3); /* 标准语法 */
}
```

# **skew()倾斜**

```css
div
{
transform: skew(30deg,20deg);
-ms-transform: skew(30deg,20deg); /* IE 9 */
-webkit-transform: skew(30deg,20deg); /* Safari and Chrome */
}
```

# **matrix()**

matrix 方法有六个参数，包含旋转，缩放，移动（平移）和倾斜功能。

```css
div
{
transform:matrix(0.866,0.5,-0.5,0.866,0,0);
-ms-transform:matrix(0.866,0.5,-0.5,0.866,0,0); /* IE 9 */
-webkit-transform:matrix(0.866,0.5,-0.5,0.866,0,0); /* Safari and Chrome */
}
```

# 2D转换方法

[2D转换方法](CSS3%202D%E8%BD%AC%E6%8D%A2%20c8f4729201c64fff8003737a01779ef1/2D%E8%BD%AC%E6%8D%A2%E6%96%B9%E6%B3%95%203134f94e90e146818f9fdacc788f0d2a.csv)
