# 尺寸位置操作jque

### 尺寸位置操作

[Untitled](%E5%B0%BA%E5%AF%B8%E4%BD%8D%E7%BD%AE%E6%93%8D%E4%BD%9Cjque%20a5193c58a61445f6a2ec695cb8cdb0a1/Untitled%20Database%20f0f9c9c3e0c04fd0a1d2acadeb29cc3b.csv)

- 以上参数为空，则是获取相应的值，返回的是数字型
- 如果参数为数字，则是修改相应的值
- 参数可以不写单位

### 位置

位置主要有三个：offset()、position()、scrollTop()/scrollLeft()

### 1. offset()设置或获取元素偏移

- offset()方法设置或返回被选元素相对于文档的便宜坐标，跟父级没有关系。
- 该方法有两个属性left、top。offset().top用于获取距离文档顶部的距离，offset().left用于获取距离文档左侧的距离。
- 可以设置元素的偏移：offset()offset({top:10,left:30});

### 2.position()获取元素偏移

- position()方法用于返回被选元素相对于带有定位的父级偏移坐标，如果父级没有定位，则以文档为准
- 这个方法只能获取，不能设置

### scrollTop()/scrollLeft()设置或获取元素被卷去的头部和左侧

scrollTop()方法设置或返回被选元素被卷去的头部
