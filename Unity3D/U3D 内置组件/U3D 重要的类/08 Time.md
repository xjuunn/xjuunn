# 08 Time

## Time

变量

> time 游戏开始到现在所用的时间（秒）
> 
> 
> deltaTime 完成最后一帧的时间 乘以deltaTime抵消性能带来的差异
> 
> > 在FixedUpdate中可以不写deltaTime，FixedUpdate0.02秒执行一次，与渲染无关
> > 
> 
> timeScale 时间缩放 可以达到减慢运动效果或游戏暂停
> 
> > timeScale不会影响update，但是会影响deltaTime
> > 
> > 
> > 要想某些物体暂停，某些物体不受影响，可以在update中使用Time.unscaledDeltaTime。不受缩放影响的每帧间隔。
> >
