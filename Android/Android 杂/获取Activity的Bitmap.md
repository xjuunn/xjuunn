```java
    /**
     * 获取当前屏幕截图，不包含状态栏
     * @param activity
     * @return
     */
    public static Bitmap screenShotWithoutStatusBar(Activity activity) {
        //通过window的源码可以看出：检索顶层窗口的装饰视图，可以作为一个窗口添加到窗口管理器
        View view = activity.getWindow().getDecorView();
        //启用或禁用绘图缓存
        view.setDrawingCacheEnabled(true);
        //创建绘图缓存
        view.buildDrawingCache();
        //拿到绘图缓存
        Bitmap bitmap = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        //状态栏高度
        int statusBarHeight = frame.top;
        int width = x;
        int height = y;
//        int width = getScreenWidth(activity);
//        int height = getScreenHeight(activity);

        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap, 0, 0, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
```