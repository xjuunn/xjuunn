# 01 GameObject

## GameObject

Unity的GameObject类用于表示任何可以存在于场景中的游戏事物。

在脚本编写过程中，GameObject类提供了允许在代码中使用方法的集合，包括查找、建立连接和在GameObject之间发送消息，以及添加或移除附加到GameObject的组件和设置与其在场景中的状态相关的值。

### 场景属性状态

GameObject的活动状态可以由GameObject名称左侧的复选框表示。可以使用GameObject.SetActive控制此状态

还可以使用GameObject.activeSelf读取当前的活动状态，使用GameObject.activeInHierarchy读取GameObject是否在场景中实际处于有活动状态。这两者中，后者是必要的，因为GameObject是否实际处于活动状态取决于其自身的活动状态，以及其所有的父项的活动状态。如果其所有的父项都不处于活动状态，则尽管它自己设置为活动状态，它也不会处于活动状态。

### 静态状态 （Static）

Unity的一些系统（如全局光照，遮挡，批处理，导航和反射探针）依赖于GameObject的静态状态。可以使用GameObjectUtilty.SetStaticEditOrFlags控制哪些Unity系统将GameObject视为静态。

### 标签和层（Tag and Layer）

标签是提供在场景中标记和识别gameObject类型的方式，而层提供一种类似但不同的方式在某些内置操作（例如渲染或物理碰撞）中包括或排除GameObjct组。

可以使用gameObject.tag和GameObject.layer属性通过脚本修改标签和层的值。还可以使用CompareTag方法高效地检查GameObject的标签，该方法包括验证标签是否存在，不会导致任何内存分配。

### 添加和移除组件

可以在运行时添加或移除组件，这对于以程序化方式创建GameObject或修改GameObject行为方式可能非常有用。还可以通过脚本启用或禁用脚本组件和某些类型的内置组件，而不销毁他们。

在运行时添加组件的最佳方法是使用AddComponent。若要移除组件，必须对组件本身使用Object.Destroy方法。

### 访问组件

最简单的情况是GameObject上的脚本需要访问附加到同一个GameObject的另一个组件（附加到GameObject的其他脚本本身也是组件）。为此，第一步是获取对要使用的组件实例的引用。通过GetComponent方法来完成。通常要将组件对象分配给变量

获取对同一个GameObject上的Rigidbody组件的引用

```C#
void Start (){ 
    Rigidbody rb = GetComponent<Rigidbody>();
}
```

获得对组件实例的引用后，可以像在Inspector中一样设置其属性的值

```C#
=void Start (){  
    Rigidbody rb = GetComponent<Rigidbody>();  // 更改对象的刚体质量。
    rb.mass = 10f;
}
```

还可以对组件引用调用方法

```C#
void Start (){  
    Rigidbody rb = GetComponent<Rigidbody>();   
    // 向刚体添加作用力。 
    rb.AddForce(Vector3.up * 10f);
}
```



可以将多个自定义脚本附加到同一个GameObject。如果需要从一个脚本访问另一个脚本，可以像往常一样使用二GetComponent，只需使用脚本的类名来指定所需的组件类型。

### 访问其他GameObject上的组件

脚本通常会跟踪其他GameObject或者其他GameObject上的组件。例如，再烹饪游戏中，厨师可能需要知道炉子的位置。

### 在Inspector中使用变量链接到GameObject

查找相关游戏对象的最直接方法是向脚本添加公共的游戏对象变量。

```C#
public class Chef : MonoBehaviour{ 
    public GameObject stove;   
    // 其他变量和函数...
}
```

此变量在Inspector中会显示为GameObject字段

可以将对象从场景或Hierarchy面板拖到变量上，进行分配。

### 查找子对象

```C#
using UnityEngine;
public class WaypointManager : MonoBehaviour { 
    public Transform[] waypoints;   
    void Start()   
    {  
        waypoints = new Transform[transform.childCount]; 
        int i = 0;  
        foreach (Transform t in transform)    
        {     
            waypoints[i++] = t;  
        }    
    }}
```

使用Transform.Find方法按名称查找特定的子对象：transform.Find(“Frying Pan”);

当gameObject具有可以在游戏运行过程中添加和移除子GameObject时，这种功能可能会很有用。可以在游戏运行过程中数去和放下的工具或器皿就是这方面的一个例子。

### 发送和广播消息

通过BroadcastMessage可以发送对命名方法的调用，而无需具体说明实现方法的位置。可以使用它对特定GameObject或任何子项上的每个MonoBehaviour调用命名方法。可以选择强制要求必须至少有一个接收方（否则会报错）。

SendMessage更具体一些，只对gameObject本身（而不对其子项）发送对命名方法的调用。

SendMessageUpwards是类似的方法，但是对GameObject及其所有父项发送对命名方法的调用。

### 按名称或标签查找GameObject

只要有某种信息可以识别游戏对象，就可以在场景层级视图中的任何位置找到该游戏对象。可以使用GameObject.Find函数按名称检索各个对象

```C#
GameObject player;void Start(){  
    player = GameObject.Find("MainHeroCharacter");
}
```

还可以使用 GameObject.FindWithTag和 GameObject.FindGameObjectsWithTag方法按标签查找对象或者对象集合。

例如，在一个烹饪游戏中有一个厨师的角色，厨房中有许多炉子（每个都标记为“Stove”）

```C#
GameObject chef;GameObject[] stoves;void Start(){   
    chef = GameObject.FindWithTag("Chef");  
    stoves = GameObject.FindGameObjectsWithTag("Stove");
}
```

### 创建和销毁GameObject

可以在运行期间创建和销毁GameObject。在Unity中，可以使用Instantiate方法创建GameObject。该方法可以生成现有对象的副本。

还有一个Destroy方法，该方法将在帧更新完成后或短时间延迟后销毁对象

```C#
void OnCollisionEnter(Collision otherObj) {    
    if (otherObj.gameObject.tag == "Garbage can") {   
        Destroy(gameObject, 0.5f);  
    }}
```

Destroy函数可以在不影响游戏对象本身的情况下销毁个别组件。

### 原始对象

GameObject类为UnityGameObject菜单中的可用选项提供了基于脚本的替代方案，可用于创建原始对象。

若要创建Unity内置原始对象的实例，使用GameObject.CreatePrimitive，他会实例化所指定的类型的原始对象。

可用的原始对象类型有

1. Sphere
2. Capsule
3. Cylinder
4. CUbe
5. Plane
6. Quad

添加光源

```C#
//创建物体
GameObject lightGO = new GameObject();//添加组件
Light light = lightGo.AddComponent<Light>();
light.color = Color.red;//设置组件的属性
light.type = LightType.Point;
```

> 属性
> 
> 
> SetActive 激活/停用此游戏对象
> 
> activeSelf 该游戏对象的激活状态
> 
> activeInHierarchy 实际激活状态（父物体未激活，就算他自己激活也不会被激活）
> 

> 方法
> 
> 
> GameObject.Find(“游戏对象名称”);//在场景中根据名称查找物体（慎用）
> 
> GameObject[] allObject = GameObject.FindGameObjectsWithTag(“标签”)；//获取所有使用该标签的物体
> 
> Gameobject playerGo = GameObject.FindWithTag(“Player”); //获取使用该标签的物体（单个）
> 
> Destroy 删除一个游戏对象，组件或资源
> 
> DontDestroyOnLoad(transform.gameObject); 加载新场景时，目标对象不会自动销毁
> 
> FindObjectOfType 返回Type类型第一个激活加载的对象
> 
> FindobjectsOfType 返回Type类型的所有激活的加载的物体列表
> 

### 实例

查找血量最少的敌人，并把它变成红色

先找HP最低的敌人

```
public Enemy FindEnemyByMinHP(Enemy[] allEnemy){    //假设第一个就是血量最低的敌人    Enemy min = all Enemy[0];    //依次与后面比较    for(int i = 0;i<allEnemy.Length;i++;){        if(min.HP>allEnemy[i].HP)            min = allEnemy[i];    }    return min;}
```

调用，变色

```C#
private void OnGUI(){    
    if(GUILayout.Button("查找血量最低的敌人")){     
        //查找场景中所有Enemy所有类型的引用 
        Enemy[] allEnemy = Object.FindObjectsOfType<Enemy>();
        //获取血量最低的引用  
        Enemy min = FindEnemyByMinHP(allEnemy);  
        //根据Enemy类型引用获取其他组件类型的引用       
        min.GetComPonent<MeshRenderer>().material.color = Color. 
    }}
```

*   名字：this.gameObject.name
*   是否激活：this.gameObject.activeSelf
*   标签：this.gameObject.tag
*   层级：this.gameObjec.layer
*   transform：this.gameObject.transform.position

##### 创建自带的几何体

GameObject obj = GameObject.CreatePrimitive(PrimitiveType.Cube);

obj.name = “”;

obj.GetComponent\<Rigidbody\>();

##### 查找对象

###### 方法1  对象名

GameObject obj = GameObject.Find(“tank”);

效率低，没找到就会返回null

###### 方法2  标签

GameObject obj = GameObject.FindWithTag(“player”);

GameObject obj2 = GameObject.FindGameObjectWithTag(“player”);

>   两个方法效果一样，只是方法名不同

得到单个对象，有两种方法

1.  定义public成员变量，从外部面板拖，进行关联
2.  通过以上API查找

##### 查找多个对象

只能通过tag查找

~~~ C#
GameObject[] objs = GameObject.FindGameObjectsWithTag("Player");
print("找到tag为Player对象的个数："+obj.Length)；
~~~

>   无论查找单个或者多个对象，只能找到激活对象，无法找到失活
>
>   查找单个对象时，如果场景中存在多个满足条件的对象，我们无法确定找到的是谁

##### 实例化对象

根据一个GameObject对象，创建出一个和它一模一样的对象

GameObject obj4 = GameObject.Instantiate(myObj);

继承MonoBehavior可以 简写

Instantiate(myObj);

##### 删除对象

GameObject.Destroy(myObj2);下一帧删除

gameObject.Destroy(obj3,5);5秒后删除

立即删除

GameObject.DestroyImmediate(myObj;)

过场景不移除

GameObject.DontDestroyOnLoad(this.gameObject);

随机

Random.Range(min,max);

----

## 添加脚本

Lesson1 les1 = obj.AddComponent(typeof(Lesson1)) as Lesson1;

#### 重复执行

InvokeRepeating(“调用的方法名”,几秒后执行,间隔几秒执行);







