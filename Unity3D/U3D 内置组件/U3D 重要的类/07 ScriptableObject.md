# 07 ScriptableObject

## ScriptableObject

ScriptableObject是一个可独立于类实例来保存大量数据的数据容器。ScriptableObject的一个主要用例是通过避免重复值来减少项目内存使用量。

每次实例化预制件时，都会产生单独的数据副本。这种情况下可以不使用该方法并且不储存重复数据，而是使用ScritableObject来储存数据，然后通过所有预制件的引用访问数据。这意味着内存中只有一个数据副本。

就像MonoBehaviour一样，ScriptableObject派生自基本Uniyt对象，但与MonoBehaviour不同，不能将ScriptableObject附加到游戏对象。应该将它们保存为项目中的资源。

如果使用Editor，可以在编辑和运行时将数据保存到ScriptableObject，因为ScriptableObject使用Editor命名空间和Editor脚本。

### 使用SctiptableObject

- 在Editor会话期间保存和储存数据
- 将数据保存为项目中的资源，以便在运行时使用

要使用ScriptableObject，必须在应用程序的Assets文件夹中创建一个脚本，并使其继承自ScriptableObject类。

```C#
using UnityEngine;
[CreateAssetMenu(fileName = "Data", menuName = "ScriptableObjects/SpawnManagerScriptableObject", order = 1)]
public class SpawnManagerScriptableObject : ScriptableObject{    
    public string prefabName;   
    public int numberOfPrefabsToCreate;   
    public Vector3[] spawnPoints;
}
```

在Assets文件夹中创建的脚本，可通过导航到Assets>Create>ScriptableObject>SpawnManagerScriptableObjext来创建ScriptableObject的实例。

要使用这些值，必须创建一个引用SctiptableObject的新脚本

```C#
using UnityEngine;public class Spawner : MonoBehaviour{ 
    // 要实例化的游戏对象。   
    public GameObject entityToSpawn; 
    //上面定义的 ScriptableObject 的一个实例。 
    public SpawnManagerScriptableObject spawnManagerValues;    
    //这将附加到创建的实体的名称，并在创建每个实体时递增。   
    int instanceNumber = 1;  
    void Start() 
    {   
        SpawnEntities();   
    }    void SpawnEntities()  
    {    
        int currentSpawnPointIndex = 0;   
        for (int i = 0;i < spawnManagerValues.numberOfPrefabsToCreate; i++)    
        {   
            //在当前生成点处创建预制件的实例。  
            GameObject currentEntity = Instantiate(entityToSpawn, spawnManagerValues.spawnPoints[currentSpawnPointIndex], Quaternion.identity);  
            //将实例化实体的名称设置为 ScriptableObject 中定义的字符串，然后为其附加一个唯一编号。        
            currentEntity.name = spawnManagerValues.prefabName + instanceNumber;    
            // 移动到下一个生成点索引。如果超出范围，则回到起始点。  
            currentSpawnPointIndex = (currentSpawnPointIndex + 1) % spawnManagerValues.spawnPoints.Length;     
            instanceNumber++;
        } 
    }}
```

将上述脚本附加到场景中的游戏对象。然后，在 Inspector 中，将 **Spawn Manager Values** 字段设置为新设置的 `SpawnManagerScriptableObject`。

将 **Entity To Spawn** 字段设置为 Assets 文件夹中的任何预制件，然后在 Editor 中单击 **Play**。在 `Spawner` 中引用的预制件会使用在 `SpawnManagerScriptableObject` 实例中设置的值来实例化。

如果在 Inspector 中使用 ScriptableObject 引用，可以双击引用字段来打开 ScriptableObject 的 Inspector。还可以创建自定义编辑器来定义该类型的 Inspector 外观，从而帮助管理它所代表的数据。
