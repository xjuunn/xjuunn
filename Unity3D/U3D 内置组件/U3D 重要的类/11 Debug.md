# 11 Debug

## Debug

Debug类用于可视化编辑器中的信息，这些信息可以帮助了解或者调查项目运行时发生的情况。

### 记录错误、警告、和消息

```C#
Debug.Log("This is a log message.");//消息
Debug.LogWarning("This is a warning message!");//警告
Debug.LogError("This is an error message!");//错误
```

写入控制台的所有内容，也会写入到日志文件。

如果在控制台中启用了Error Pause，通过Debug类写入控制台的任何错误信息都会导致Unity的运行模式暂停。

可以选择为这些日志方法提供第二个参数，指定消息与特定游戏对象相关联。

```C#
using UnityEngine;
public class DebugExample : MonoBehaviour{  
    void Start()
    {      
        Debug.LogWarning("I come in peace!", this.gameObject);   
    }}
```

这样做的好处是，当在控制台中点击消息时，与该消息关联的游戏对象会在层级视图中突出显示。

Debug类还提供了两种在Scene视图和Game视图中绘制线条的方法，DrawLine和DrawRay

```C#
using UnityEngine;public class DebugLineExample : MonoBehaviour{
    // Start is called before the first frame update   
    void Start() 
    {
        float height = transform.position.y;  
        Debug.DrawLine(transform.position, transform.position - Vector3.up * height, Color.magenta, 4);  
    }}
```
