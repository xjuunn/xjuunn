# 集合

*   动态数组 ArrayList
*   哈希表 Hashtable
*   排序列表 SortedList
*   堆栈 Stack
*   队列 Queue
*   点阵列 BitArray

## 堆栈 Stack

~~~ C#
Stack st = new Stack();
st.Push(4);
st.Push("test2");
st.Push(2.3);
st.Push("test4");
Console.WriteLine(st.ToString());
Console.WriteLine(st.Peek());
Console.WriteLine(st.Pop());
Console.WriteLine(st.Pop());
Console.WriteLine(st.Pop());
Console.WriteLine(st.Pop());
~~~

*   Const 元素个数
*   Clear() 移除所有元素
*   Contains()是否包含某元素
*   Peek() 返回顶部元素
*   Pop() 移除并返回顶部元素
*   Push() 在顶部添加一个对象
*   ToArray() 复制Stack到一个新的数组中

## 队列 Queue

~~~ C#
Queue queue = new Queue();
queue.Enqueue("test1");
queue.Enqueue(2);
queue.Enqueue(2.3);
queue.Enqueue('A');
queue.Enqueue("test5");
queue.Enqueue("test6");
queue.Enqueue("test7");
Console.WriteLine(queue.Dequeue());
Console.WriteLine(queue.Peek());
Console.WriteLine(queue.Peek());
Console.WriteLine(queue.Dequeue());
Console.WriteLine(queue.Dequeue());
Console.WriteLine(queue.Dequeue());
~~~

*   Const 元素个数
*   Clear() 移除所有元素
*   Dequeue() 移除并返回开头的对象
*   Enqueue() 末尾添加一个对象
*   ToArray() 复制Queue到一个新的数组中
*   TrimToSize() 设置容量为Queue中的元素的实际个数