# CSS3 渐变

两个或多个指定的颜色之间显示平稳的过渡。

- 线性渐变（Linear Gradients）-向下/向上/向左/向右/对角方向
- 径向渐变（Radial Gradients）-由中心定义

[渐变色工具 - 菜鸟工具](https://c.runoob.com/more/gradients/#LemonLime)

# 线性渐变

![实例](https://www.runoob.com/wp-content/uploads/2014/07/gradient_linear.png)

实例

### 语法

> `background-image: linear-gradient(direction, color-stop1, color-stop2, ...);`
> 

### 线性渐变，从上到下（默认）

```css
#grad {
    background-image: linear-gradient(#e66465, #9198e5);
}
```

### 从左到右

```css
#grad {
  height: 200px;
  background-image: linear-gradient(to right, red , yellow);
}
```

### 对角

```css
#grad {
  height: 200px;
  background-image: linear-gradient(to bottom right, red, yellow);
}
```

## 角度

> `background-image: linear-gradient(angle, color-stop1, color-stop2);`
> 

## 使用多个颜色节点

```css
#grad {
  background-image: linear-gradient(red, yellow, green);
}
#grad {
  /* 标准的语法 */
  background-image: linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet);
}
```

## 透明度

```css
#grad {
  background-image: linear-gradient(to right, rgba(255,0,0,0), rgba(255,0,0,1));
}
```

## 重复的线性渐变

```css
#grad {
  /* 标准的语法 */
  background-image: repeating-linear-gradient(red, yellow 10%, green 20%);
}
```

# CSS3 径向渐变

### 语法

> `background-image: radial-gradient(shape size at position, start-color, ..., last-color);`
> 

### 颜色节点均匀分布

```css
#grad {
  background-image: radial-gradient(red, yellow, green);
}
```

### 颜色节点不均匀分布

```css
#grad {
  background-image: radial-gradient(red 5%, yellow 15%, green 60%);
}
```

## 设置形状

shape 参数定义了形状。它可以是值 circle 或 ellipse。其中，circle 表示圆形，ellipse 表示椭圆形。默认值是 ellipse。

```css
#grad {
  background-image: radial-gradient(circle, red, yellow, green);
}
```

## 不同尺寸大小关键字的使用

size 参数定义了渐变的大小。它可以是以下四个值：

- **closest-side**
- **farthest-side**
- **closest-corner**
- **farthest-corner**

## 重复的径向渐变

repeating-radial-gradient() 函数用于重复径向渐变：

```css
#grad {
  background-image: repeating-radial-gradient(red, yellow 10%, green 15%);
}
```
