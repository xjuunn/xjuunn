# 2 drag拖放

拖放是一种常见的特性，即抓取对象以后拖到另一个位置。

在HTML5中，拖放是标准的一部分，任何元素都能够拖放。

## 设置元素为可拖放

```html
<img draggable="true"></img>
```

## drag事件

| 事件 |  产生事件的元素 | 描述 |
| --- | --- | --- |
| dragstart |  被拖放的元素 |  开始拖放操作 |
|  drag |  被拖放的元素 |  拖放过程中 |
| dragenter | 拖放过程中鼠标经过的元素 |  被拖放的元素开始进入本元素的范围内 |
| dragover |  拖放过程中鼠标经过的元素 |  被拖放的元素正在本元素的范围内移动 |
| dragleave |  拖放过程中鼠标经过的元素 |  被拖放的元素离开本元素的范围 |
| drop | 拖放的目标元素 |  有其他元素被拖放到了本元素中 |
| dragend |  拖放的对象元素 |  拖放过程结束 |

## 编程

1. 设置元素可以被拖放： draggable=”true”;
2. ondragstart里setData
3. ondragover里阻止默认事件
4. ondrop里，阻止默认事件，并getData，append到元素里；

在ondragover中一定要执行preventDefaule()，否则ondrop事件不会被触发。

drag系列事件不能和mousemove共存

```html
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<style type="text/css">
			#div1 {
				width: 350px;
				height: 70px;
				padding: 10px;
				border: 1px solid #aaaaaa;
			}
			.box1 {
				display: inline-block;
				margin: 10px;
				width: 336px;
				height: 69px;
				background-color: greenyellow;
			}
		</style>
		<script>
			function allowDrop(ev) {
				ev.preventDefault();
				console.log("鼠标经过" + ev)
			}
			function drag(ev) {
				ev.dataTransfer.setData("Text", ev.target.id);
				console.log("鼠标拖动" + ev + " id" + ev.target.id)
			}
			function drop(ev) {
				ev.preventDefault();
				var data = ev.dataTransfer.getData("Text");
				ev.target.appendChild(document.getElementById(data));
				console.log("鼠标抬起" + ev)
			}
		</script>
	</head>
	<body>
		<div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)"></div><br>
		<div class="box1" draggable="true" ondragstart="drag(event)" id="dr1"></div>
	</body>
</html>
```
