# MeshCollider网格碰撞体

## 网格碰撞体

网格碰撞体采用网络资源并基于该网络后见其碰撞体。在进行碰撞检测时，Mesh Conllider比使用更发杂网络的基元更准确。标记为Convex的Mesh。

### 属性

[Untitled](MeshCollider%E7%BD%91%E6%A0%BC%E7%A2%B0%E6%92%9E%E4%BD%93%20a596f62959174eefbfaddb7623b03db9/Untitled%20Database%2001e8adcdabdd4a55a1c6a06898b8d7c0.csv)

Mesh Collider 从附加到游戏对象的[网格](https://docs.unity3d.com/cn/current/Manual/class-Mesh.html)构建其碰撞表示，并读取附加的[变换组件](https://docs.unity3d.com/cn/current/Manual/class-Transform.html)的属性以正确设置其位置和缩放。这样做的好处是可以使碰撞体的形状与游戏对象可见网格的形状完全相同，从而产生更精确和真实的碰撞。但是，伴随这种精度的不足之处是，与涉及原始碰撞体（例如球体、盒体和胶囊体）的碰撞相比，处理开销会更高，因此最好谨慎使用 Mesh Collider。

碰撞网格中的面为单面。这意味着，游戏对象可从一个方向穿过这些面，但从另一个方向会与这些面碰撞。

如需了解网格碰撞体 (Mesh Collider) 使用的基础算法和数据结构的详细信息，请参阅 [PhysX 文档](https://docs.nvidia.com/gameworks/content/gameworkslibrary/physx/guide/Manual/Geometry.html)。

### 网格烹制

网格烹制将常规网格更改为可以在物理引擎中使用的网格。烹制会构建用于物理查询的空间搜索结构，以及用于接触生成的支持结构。Unity 在碰撞检测中使用网格之前烹制所有这些网格。这可在导入时 (**Import Settings > Model > Generate Colliders**) 或运行时进行。

如果要在运行时生成网格（例如，对于程序化表面），设置 **Cooking Options** 以更快地生成结果并禁用清理过程的其他数据清理步骤将非常有用。缺点是需要生成无退化三角形和非同位顶点，但烹制的运行速度更快。

如果禁用 **Enable Mesh Cleaning** 或 **Weld Colocated Vertices__，则需要确保未使用那些算法在其他情况下可能会过滤的数据。如果禁用了** Weld Colocated Vertices__，请确保没有任何同位顶点，如果启用了 **Enable Mesh Cleaning**，确保没有面积接近零的小三角形，没有狭长的三角形，也没有面积接近于无限大的大三角形。

**注意**：将 **Cooking Options** 设置为除默认设置之外的任何其他值时，意味着 Mesh Collider 必须使用一个isReadable 值为 `true` 的网格。

### 限制

使用 Mesh Collider 时有一些限制：

具有 Rigidbody 组件的游戏对象仅支持启用了 **Convex** 选项的网格碰撞体 (Mesh Collider)：物理引擎只能模拟凸面网格碰撞体。

要使 Mesh Collider 正常工作，网格必须在以下情况下设置为 read/write enabled：

- Mesh Collider 的变换组件具有负缩放（例如 (–1, 1, 1)）并且网格为凸面。
- Mesh Collider 的变换组件是倾斜或截断的（例如，当旋转的变换组件具有缩放的父变换组件时）。
- Mesh Collider 的 **Cooking Options** 标志设置为默认值以外的任何值。

不应修改用于碰撞体的网格几何体，因为每次更改网格时，物理引擎都必须重建内部网格碰撞加速结构。这会导致大量的性能开销。对于需要在运行时发生碰撞和进行更改的网格，通常最好使用诸如胶囊体、球体和盒体之类的原始碰撞体来模拟网格形状。
