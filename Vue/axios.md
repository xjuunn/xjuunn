# axios

## <a href="https://www.axios-http.cn">axios</a>

功能强大的网络请求库

需要导入

> <script src="https://cdn.bootcdn.net/ajax/libs/axios/1.0.0-alpha.1/axios.js"></script>

#### axios 实现GET/POST请求案例

```js
<input type="button" value="Get请求" class="get"/>
<input type="button" value="Post请求" class="postbtn"/>
<script>
    document.querySelector(".get").onclick= function(){
        axios.get("https://autumnfish.cn/api/joke/list?num=5")
            .then(function(response){
                console.log(response);
            }),function(err){
            console.log(err);
        }
    }
    document.querySelector(".postbtn").onclick=function(){
        console.log("a")
        axios.post("https://autumnfish.cn/api/user/reg",
            {username:"testname"})
            .then(function(response){
                console.log(response);
            })
    }

</script>
```

```js
<input type="button" value="Get请求" class="get"/>
<input type="button" value="Post请求" class="postbtn"/>
<script>
    document.querySelector(".get").onclick= function(){
        axios.get("https://autumnfish.cn/api/joke/list?num=5")
            .then(function(response){
                console.log(response);
            }),function(err){
            console.log(err);
        }
    }
    document.querySelector(".postbtn").onclick=function(){
        console.log("a")
        axios.post("https://autumnfish.cn/api/user/reg",
            {username:"testname"})
            .then(function(response){
                console.log(response);
            })
    }

</script>
```
