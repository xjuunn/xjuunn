# Animation View动画

## 动画

通过动画视图可以直接创建和修改动画片段

显示动画视图：window — Animation

### 创建动画片段

- 为物体添加Animation组件
- 在动画视图中创建片段

变量

> isPlaying 是否有任意动画在播放
> 

函数

> Paly 播放
> 
> 
> CrossFade 淡入淡出的播放
> 
> PlayQueued 在前一个动画播放完成之后直接播放下一个动画
> 

> animation[“动画名”].speed = 1;播放速度。负数为倒放
> 
> 
> animation[“动画名”].length;动画总长度
> 
> animation[“动画名”].time;当前动画的播放时间
> 

开关门案例

```C#
if(doorState){   
    if(anim.isPlaying==false){    
        anim[animName].time = anim[animName].length;  
    }    
    anim[animName].speed = -1;
}else{  
    anim[animName].speed = 1;
}
anim.Play(animName);
doorState = !doorState;
```
