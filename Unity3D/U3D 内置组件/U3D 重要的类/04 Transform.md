# 04 Transform

## Transform

Transform类提供多种方式类通过脚本处理游戏对象的位置、旋转和缩放，以及父与子游戏对象的层级关系。

## Transform 查找组件，改变位置、角度、大小

对象的位置，旋转和缩放

```C#
foreach(Transform child in this.transform){
    print(child.name);
    //child为每个子物体的变换组件
}
```

> 变量
> 
> 
> position 相对于的世界的坐标
> 
> localPosition 相对于父物体的坐标
> 
> localScale 相对于父物体的缩放
> 
> lossyScle 物体与模型缩放比例（自身缩放比例*夫物体的所发昂比例）,只读
> 

> 方法
> 
> - 移动
> 
> Translate 相对于自身移动（相对坐标）
> 
> ```
> this.transform.Translate(0,0,0,Space.Self);//相对于自身移动，默认值this.transform.Translate(0,0,0,Space.World);//相对于世界移动
> ```
> 
> - 旋转
> 
> Rotate
> 
> ```
> this.transform.Rotate(0,10,0,Space.Self);//沿自身坐标系y轴旋转10度this.transform.Rotate(0,10,0,Space.World);//沿世界坐标系y轴旋转10度
> ```
> 
> - 围绕旋转
> 
> RotateAround
> 
> ```
> this.transform.RotateAround(Vector3.zero,Vector3.forward,1);
> ```
> 
> - 获取/设置组件
> 
> 根物体
> 
> ```
> Transform roottf = this.transform.root;
> ```
> 
> 父物体
> 
> ```
> Transform parenttf = this.transform.parent;
> ```
> 
> 设置父物体为
> 
> ```
> this.transform.SetParent(tf);//把tf作为this的父物体this.transform.SetParent(tf,false);//当前物体的位置视为localPosition坐标
> ```
> 
> 根据名字查找子物体
> 
> ```
> Transform childTF = this.transform.Find("子物体名称");Transform childTF = this.transform.Find("子物体名称/孙物体的名称");//支持路径格式（不建议）
> ```
> 
> 根据索引查找子物体
> 
> ```
> int count = this.transform.childCount;//获取子物体数量Transform childTF = this.transform.GetChild(索引);
> ```
> 
> 解除父子关系
> 
> ```
> transform.DetachChildren();//解除子物体SetParent = null;//解除父物体
> ```
