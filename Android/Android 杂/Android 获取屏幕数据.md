# Android 获取屏幕数据

```java
/** * @ 获取当前手机屏幕的尺寸(单位:像素) */
public static float getPingMuSize(Context mContext) {
    int densityDpi = mContext.getResources().getDisplayMetrics().densityDpi;
    float scaledDensity = mContext.getResources().getDisplayMetrics().scaledDensity;
    float density = mContext.getResources().getDisplayMetrics().density;
    float xdpi = mContext.getResources().getDisplayMetrics().xdpi;
    float ydpi = mContext.getResources().getDisplayMetrics().ydpi;
    int width = mContext.getResources().getDisplayMetrics().widthPixels;
    int height = mContext.getResources().getDisplayMetrics().heightPixels;    // 这样可以计算屏幕的物理尺寸
    float width2 = (width / xdpi)*(width / xdpi);
    float height2 = (height / ydpi)*(width / xdpi);
    return (float) Math.sqrt(width2+height2);
}
```

```java
// 通过Activity类中的getWindowManager()方法获取窗口管理，再调用getDefaultDisplay()方法获取获取Display对象
Display display = getActivity().getWindowManager().getDefaultDisplay();
// 方法一(推荐使用)使用Point来保存屏幕宽、高两个数据
Point outSize = new Point();
// 通过Display对象获取屏幕宽、高数据并保存到Point对象中
display.getSize(outSize);
// 从Point对象中获取宽、高
int x = outSize.x;
int y = outSize.y;
// 通过吐司显示屏幕宽、高数据522
Toast.makeText(context, "手机像素为：X:" + x + "  Y:" + y, Toast.LENGTH_LONG).show();
```

```java
        // 通过WindowManager获取
DisplayMetrics dm = new DisplayMetrics();
getWindowManager().getDefaultDisplay().getMetrics(dm);
System.out.println("width-display :" + dm.widthPixels);
System.out.println("heigth-display :" + dm.heightPixels);        // 通过Resources获取
DisplayMetrics dm2 = getResources().getDisplayMetrics();
System.out.println("width-display :" + dm2.widthPixels);
System.out.println("heigth-display :" + dm2.heightPixels);        // 获取屏幕的默认分辨率
Display display = getWindowManager().getDefaultDisplay();
System.out.println("width-display :" + display.getWidth());
System.out.println("heigth-display :" + display.getHeight());
```

```java
    private static double mInch = 0;
/**     
* 获取屏幕尺寸
* @param context
* @return
*/
public static double getScreenInch(Activity context) {
    if (mInch != 0.0d) {
        return mInch;
    }
    try {
        int realWidth = 0, realHeight = 0;
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            Point size = new Point();
            display.getRealSize(size);
            realWidth = size.x;
            realHeight = size.y;
        } else if (android.os.Build.VERSION.SDK_INT < 17
                   && android.os.Build.VERSION.SDK_INT >= 14) {
            Method mGetRawH = Display.class.getMethod("getRawHeight");
            Method mGetRawW = Display.class.getMethod("getRawWidth");
            realWidth = (Integer) mGetRawW.invoke(display);
            realHeight = (Integer) mGetRawH.invoke(display);
        } else {
            realWidth = metrics.widthPixels;
            realHeight = metrics.heightPixels;
        }
        mInch =formatDouble(Math.sqrt((realWidth/metrics.xdpi) * (realWidth /metrics.xdpi) + (realHeight/metrics.ydpi) * (realHeight / metrics.ydpi)),1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return mInch;
}
/**     
* Double类型保留指定位数的小数，返回double类型（四舍五入）
* newScale 为指定的位数
*/
private static double formatDouble(double d,int newScale) {
    BigDecimal bd = new BigDecimal(d);
    return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
}
```
