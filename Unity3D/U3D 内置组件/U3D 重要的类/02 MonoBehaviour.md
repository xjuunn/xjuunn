# 02 MonoBehaviour

## MonoBehaviour

MonoBehaviout类是一个基类，所有Unity脚本都默认派生自该类。当从Unity项目中创建一个C#脚本时，他会自动继承MonoBehaviour，并提供一些模板脚本。

### 协程

MonoBehaviour类允许启动、停止和管理协程，这是一种编写异步代码的方法，其中包括等待一定时间或某些操作完成，同时允许其他代码继续执行。

### 事件

MonoBehaviour类提供对大量事件消息的访问，允许根据项目中当前发生的情况执行代码。比如：

- Start 游戏对象开始存在时（加载场景或者实例化对象时）调用。
- Update 每帧都会被调用。
- FixedUpdate 每个物理事件步进调用。
- OnBecameVisible和OnBecameInvisible 当游戏对象的渲染器进入或离开摄像机的视图时调用。
- OnCollisionEnter和OnTriggerEnter 在发生物理碰撞或触发时调用。
- OnDestroy 在销毁游戏对象时调用。
