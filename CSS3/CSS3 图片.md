# CSS3 图片

# 圆角图片

```css
border-radius:8px;
border-fadius:50%;
```

# 缩略图

```css
img {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
}

<img src="paris.jpg" alt="Paris">
```

### 边框动态

```css
a {
    display: inline-block;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
    transition: 0.3s;
}

a:hover {
    box-shadow: 0 0 2px 1px rgba
    (0, 140, 186, 0.5);
}

<a href="paris.jpg">
  <img src="paris.jpg" alt="Paris">
</a>
```

# 响应式图片

```css
img {
    max-width: 100%;
    height: auto;
}
```

# 滤镜

## 黑白效果

```css
img {
    -webkit-filter: grayscale(100%); /* Chrome, Safari, Opera */
    filter: grayscale(100%);
}
```

[CSS3 filter(滤镜) 属性](https://www.runoob.com/cssref/css3-pr-filter.html)

# 模态

窗口显示

```css
// 获取模态窗口
var modal = document.getElementById('myModal');

// 获取图片模态框，alt 属性作为图片弹出中文本描述
var img = document.getElementById('myImg');
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    modalImg.alt = this.alt;
    captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}
```
