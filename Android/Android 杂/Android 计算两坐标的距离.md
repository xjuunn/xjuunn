# Android 计算两坐标的距离

```java
double distance(double lat1, double lon1, double lat2, double lon2) { 
    double theta = lon1 - lon2; 
    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))    
        + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))     
        * Math.cos(deg2rad(theta));   
    dist = Math.acos(dist);    
    dist = rad2deg(dist);   
    double miles = dist * 60 * 1.1515;
    return miles;
}//将角度转换为弧度
static double deg2rad(double degree) {  
    return degree / 180 * Math.PI;}
//将弧度转换为角度
static double rad2deg(double radian) { 
    return radian * 180 / Math.PI;
}
```
