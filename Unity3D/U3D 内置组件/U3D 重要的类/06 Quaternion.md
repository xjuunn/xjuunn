# 06 Quaternion

## Quaternion

Unity使用Quaternion类来储存游戏对象的三维方向，也使用它们来描述从一个方向到另一个方向的相对旋转。

处理脚本中的旋转时，应使用Quaternion类及其函数来创建和修改旋转值。

### 直接创建和操作四元数

Unity的Quaternion类具有许多函数可用于创建和操作旋转，根本无需使用欧拉角。

### 创建旋转

- Quaternion.LookRotation
- Quaternion.Angle
- Quaternion.AngleAxis
- Quaternion.FromToRotation

### 操作旋转

- Quaternion.Slerp
- Quaternion.Inverse
- Quaternion.RotateTowards

Transform类还提供了一些方法可用于处理Quaternion选抓

- Transform.Rotate & Transform.RotateAround

### 使用欧拉角

```C#
// 正确使用欧拉角的旋转脚本。
// 将欧拉角存储在一个类变量中，并仅使用
// 该变量作为欧拉角进行应用，但从不依赖于读回欧拉值
float x;
void Update (){  
    x += Time.deltaTime * 10;  
    transform.rotation = Quaternion.Euler(x,0,0);
}
```
