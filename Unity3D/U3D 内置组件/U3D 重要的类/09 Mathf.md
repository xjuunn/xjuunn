# 09 Mathf

# 重要的类 - Mathf

Unity 的 Mathf 类提供了一组常见的数学函数，包括三角函数、对数函数以及游戏和应用开发中常用的其他函数。

此页面概述了 Mathf 类及其使用该类编写脚本时的常见用法。

## 三角函数

Unity 的所有三角函数都使用**弧度**。

- `Sin`
- `Cos`
- `Tan`
- `Asin`
- `Acos`
- `Atan`
- `Atan2`

提供了 `PI` 常量，您可以乘以静态值 `Rad2Deg` 或者 `Deg2Rad` 在弧度和度之间转换。

## 幂和平方根

Unity 提供了您通常会用到的幂和平方根函数： - `Pow` -`Sqrt` - `Exp`

还有一些有用的 2 的幂次相关函数。这在处理常见的二进制数据大小时很有用，这些数据大小通常被限制或优化为二的幂值（例如纹理尺寸）：

- `ClosestPowerOfTwo`
- `NextPowerOfTwo`
- `IsPowerOfTwo`

## 插值

Unity 的插值函数允许您计算位于两个给定点之间某处的值。其中的函数行为各不相同，适用于不同的情况。有关更多信息，请参阅各自的示例：

- `Lerp`
- `LerpAngle`
- `LerpUnclamped`
- `InverseLerp`
- `MoveTowards`
- `MoveTowardsAngle`
- `SmoothDamp`
- `SmoothDampAngle`
- `SmoothStep`

注意，Vector 类和 `Quaternion` 类都有自己的插值函数（如 Quaternion.Lerp），允许您在多个维度上插入位置、方向和旋转。

## 限制和重复值

这些简单的辅助函数在游戏或应用中通常很有用，当您需要将值限制在某个范围内或在某个范围内重复时，可以为您节省时间。

- `Max`和 `Min`
- `Repeat` 和 `PingPong`
- `Clamp` 和 `Clamp01`
- `Ceil`和 `Floor`

## 对数

`Log`函数用于计算指定数字的对数，可以是自然对数，也可以是指定底数的对数。此外，`Log10` 函数返回指定数值的以 10 为底的对数。
