# Android 触摸

```java
@Overridepublic boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()){
            /**
            * 点击的开始位置
            */
        case MotionEvent.ACTION_DOWN:
            Log.i(TAG, "onTouchEvent:起始位置：(" + event.getX() + "," + event.getY());
            break;
            /**
            * 触屏实时位置
            */
        case MotionEvent.ACTION_MOVE:
            Log.i(TAG, "onTouchEvent: 实时位置：(" + event.getX() + "," + event.getY()); 
            break;     
            /**     
            * 离开屏幕的位置  
            */    
        case MotionEvent.ACTION_UP:    
            Log.i(TAG, "onTouchEvent: 结束位置：(" + event.getX() + "," + event.getY());   
            break;      
        default:     
            break;   
    }    return true; 
    /**       
    *  注意返回值 
    *  true：view继续响应Touch操作；
    *  false：view不再响应Touch操作，故此处若为false，只能显示起始位置，不能显     
    *  示实时位置和结束位置    
    */}
```

根据触摸位置改变控件坐标

```java
private double x = 0 ;private double y = 0 ;
private boolean a = false;
@Overridepublic boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()){
        case MotionEvent.ACTION_DOWN:
            if (event.getX()<textView.getX()+textView.getWidth()&&event.getX()>textView.getX()&&event.getY()<textView.getY()+textView.getHeight()&&event.getY()>textView.getY()){
                a = true; 
            }       
            break;    
        case MotionEvent.ACTION_MOVE:    
            if (a){         
                x = event.getX();  
                y = event.getY();      
                textView.setX((float) x-textView.getWidth()/2);     
                textView.setY((float) y-textView.getHeight()/2);  
            }         
            break;       
        case MotionEvent.ACTION_UP:   
            a = false;       
            break;     
        default:        
            break;   
    }   
    return true;
}
```

```java
//优化1.0
private double x = 0 ;
private double y = 0 ;
private boolean a = false;
private double dx = 0 ;
private double dy = 0 ;
@Overridepublic boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()){
        case MotionEvent.ACTION_DOWN:
            if (event.getX()<text.getX()+text.getWidth()&&event.getX()>text.getX()&&event.getY()<text.getY()+text.getHeight()&&event.getY()>text.getY()){
                x = event.getX(); 
                y = event.getY();   
                dx = x - text.getX();    
                dy = y - text.getY();    
                a = true;      
            }   
            break;    
        case MotionEvent.ACTION_MOVE:    
            t+=0.5;        
            if (a){        
                x = event.getX();      
                y = event.getY();     
                text.setX((float) (x-dx));   
                text.setY((float) (y-dy));         
            }           
            break;     
        case MotionEvent.ACTION_UP:  
            a = false;          
            break;       
        default:         
            break;   
    }   
    return true;
}
```
