# 09 UnityEvent

## UnityEvent

借助 UnityEvent 可让用户驱动的回调从编辑时间一直持续到运行时，无需进行额外的编程和脚本配置。

UnityEvent 对许多方面都很有用：

- 内容驱动的回调
- 解耦系统
- 持久回调
- 预配置的调用事件

`UnityEvent` 可添加到任何 `MonoBehaviour`，并从标准 .net 委托之类的代码中执行。当 `UnityEvent` 添加到 `MonoBehaviour` 时，它会出现在 Inspector 中，并可添加持久回调。

`UnityEvent` 与标准委托有类似的限制。也就是说，它们会保留对目标元素的引用，而这会阻止对目标进行垃圾收集。如果将 UnityEngine.Object 作为目标，而本机表示消失，则不会调用回调。

### 使用 UnityEvent

### 通用 UnityEvent

默认情况下，`Monobehaviour` 中的 `UnityEvent` 动态绑定到 void 函数。但不一定非得如此，因为 UnityEvent 的动态调用支持绑定到最多包含 4 个参数的函数。为此，您需要定义一个支持多个参数的自定义 `UnityEvent` 类。此定义十分简单：

```C#
[Serializable]

public class StringEvent : UnityEvent <string> {}
```

通过将此类的实例添加到您的类而不是基本 `UnityEvent`，即可使回调动态绑定到字符串函数。

然后，可通过调用以 `string` 为参数的 `Invoke()` 函数来对其进行调用。

UnityEvent 可在其通用定义中定义最多 4 个参数。
