# Gizmos & Handles

## Gizmos & Handles

Gizmos & Handles类用于在Scene视图和Game视图绘制线条和形状以及交互式手柄和控件。这两个类共同提供了一种方法来扩展这些视图中显示的内容，并构建交互式工具。

### Gizmos

Gizmos类允许创建线条、球体、立方体、图标、纹理和网格绘制到Scene视图中，在开放项目时用作辅助手段或工具。

例如：在游戏对象周围绘制10个单位的黄色线框立方体

```C#
using UnityEngine;public class GizmosExample : MonoBehaviour{   
    void OnDrawGizmosSelected()   
    {        
        // 在变换位置绘制一个黄色立方体  
        Gizmos.color = Color.yellow;       
        Gizmos.DrawWireCube(transform.position, new Vector3(10, 10, 10));
    }}
```

### Handles

Handles类似于Gizmos，但在交互性和操作方面提供了更多功能。Uniyt本身提供的用于在Scene视图中操作项目的3D控件时Gizmos和Handles的组合。内置的Handle GUI有很多，如通过变化组件定位、缩放和旋转对象等熟悉的工具。不过，可以自行定义HandleGUI。，以与自定义组件编辑器接合使用。类此GUI对于编辑以程序方式生成场景内容，“不可见”项和相关对象的组（如路径点和位置标记）非常实用。

```C#
using UnityEditor;
using UnityEngine;
using System.Collections;//项目中应已包含了此类
public class WireArcExample : MonoBehaviour{   
    public float shieldArea;                 
}
// 使用附加到圆盘的 ScaleValueHandle 创建一个 180 度的线弧，
// 允许您修改 WireArcExample 中的 "shieldArea" 的值
[CustomEditor(typeof(WireArcExample))]
public class DrawWireArc : Editor{  
    void OnSceneGUI()   
    {    
        Handles.color = Color.red;  
        WireArcExample myObj = (WireArcExample)target;      
        Handles.DrawWireArc(myObj.transform.position, myObj.transform.up, -myObj.transform.right, 180, myObj.shieldArea);     
        myObj.shieldArea = (float)Handles.ScaleValueHandle(myObj.shieldArea, myObj.transform.position + myObj.transform.forward * myObj.shieldArea, myObj.transform.rotation, 1, Handles.ConeHandleCap, 1);  
    }}
```
