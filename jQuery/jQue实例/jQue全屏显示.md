# jQue全屏显示

## 全屏显示

```js
$(function(){  
    /* 获取 documentElement (<html>) 以全屏显示页面 */ 
    var elem = document.documentElement;  
    /* 全屏查看 */ 
    function openFullscreen() {  
        if (elem.requestFullscreen) {  
            elem.requestFullscreen();  
        } else if (elem.mozRequestFullScreen) {
            /* Firefox */  
            elem.mozRequestFullScreen();  
        } else if (elem.webkitRequestFullscreen) { 
            /* Chrome, Safari 和 Opera */   
            elem.webkitRequestFullscreen();   
        } else if (elem.msRequestFullscreen) {
            /* IE/Edge */     
            elem.msRequestFullscreen();
        }
    } 
    /* 关闭全屏 */ 
    function closeFullscreen() {  
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.mozCancelFullScreen) { 
            /* Firefox */  
            document.mozCancelFullScreen();   
        } else if (document.webkitExitFullscreen) { 
            /* Chrome, Safari 和 Opera */  
            document.webkitExitFullscreen();
        } else if (document.msExitFullscreen) {
            /* IE/Edge */      
            document.msExitFullscreen(); 
        }   
    }  
    $("#fulls").click(function(){   
        openFullscreen(); 
    });
})
```
