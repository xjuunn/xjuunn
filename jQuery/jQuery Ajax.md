# jQuery Ajax

# Jquery load() 方法

> $(selector).load(URL,data,callback);
> 

必需的 *URL* 参数规定您希望加载的 URL。

可选的 *data* 参数规定与请求一同发送的查询字符串键/值对集合。

可选的 *callback* 参数是 load() 方法完成后所执行的函数名称。

可选的 callback 参数规定当 load() 方法完成后所要允许的回调函数。回调函数可以设置不同的参数：

- *responseTxt* - 包含调用成功时的结果内容
- *statusTXT* - 包含调用的状态
- *xhr* - 包含 XMLHttpRequest 对象

```jsx
$("button").click(function(){
  $("#div1").load("demo_test.txt",function(responseTxt,statusTxt,xhr){
    if(statusTxt=="success")
      alert("外部内容加载成功!");
    if(statusTxt=="error")
      alert("Error: "+xhr.status+": "+xhr.statusText);
  });
});
```

# jQuery $.get()方法

$.get() 方法通过 HTTP GET 请求从服务器上请求数据。

### **语法：**

```jsx
$.get(URL,callback);
或
$.get( URL [, data ] [, callback ] [, dataType ] )
```

- **URL**：发送请求的 URL字符串。
- **data**：可选的，发送给服务器的字符串或 key/value 键值对。
- **callback**：可选的，请求成功后执行的回调函数。
- **dataType**：可选的，从服务器返回的数据类型。默认：智能猜测（可以是xml, json, script, 或 html）。

# jQuery $.post() 方法

$.post() 方法通过 HTTP POST 请求向服务器提交数据。

**语法:**

```
$.post(URL,callback);
或
$.post( URL [, data ] [, callback ] [, dataType ] )
$.get(URL,data,function(data,status,xhr),dataType)
```

- **URL**：发送请求的 URL字符串。
- **data**：可选的，发送给服务器的字符串或 key/value 键值对。
- **callback**：可选的，请求成功后执行的回调函数。
- **dataType**：可选的，从服务器返回的数据类型。默认：智能猜测（可以是xml, json, script, 或 html）。

[jQuery AJAX 方法](jQuery%20Ajax%20a9e38c8c4a094d90922782fd10604e56/jQuery%20AJAX%20%E6%96%B9%E6%B3%95%20b8037e5c2ba449c7beca46045594bb37.csv)
