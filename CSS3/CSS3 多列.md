# CSS3 多列

- `column-count` 列数
- `column-gap` 列间隙
- `column-rule-style`
- `column-rule-width`
- `column-rule-color`
- `column-rule` 样式
- `column-span` 跨列 1/all
- `column-width` 列宽

# 创建多列

```css
div {
    -webkit-column-count: 3; /* Chrome, Safari, Opera */
    -moz-column-count: 3; /* Firefox */
    column-count: 3;
}
```

# 列间隙

```css
div {
    -webkit-column-gap: 40px; /* Chrome, Safari, Opera */
    -moz-column-gap: 40px; /* Firefox */
    column-gap: 40px;
}
```

# 列边框

```css
div {
		/*边框样式*/
    column-rule-style: solid;
		/*边框厚度*/
		column-rule-width: 1px;
		/*边框颜色*/
		column-rule-color: lightblue;
		/*合并*/
		column-rule: 1px solid lightblue;
}
```

# 元素跨列

```css
h2 {
    -webkit-column-span: all; /* Chrome, Safari, Opera */
    column-span: all;
}
```

# 列宽

```css
div {
    -webkit-column-width: 100px; /* Chrome, Safari, Opera */
    column-width: 100px;
}
```













