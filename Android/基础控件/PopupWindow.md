# PopupWindow

*   setContentView 设置显示的View
*   showAsDropDown 相对某个控件的位置，无偏移
*   showAsDropDown(view,int,int)设置相对某个控件的位置，有偏移
*   setFocusable 设置是否获取焦点
*   setBackgroundDrawable 设置背景
*   dismiss 关闭弹窗
*   setAnimationStyle 设置加载动画
*   setTouchable 设置触摸使能
*   setOutsideTouchable 设置外面的触摸使能

~~~ java
View popupView = getLayoutInflater().inflate(R.layout.popup_view,null);
PopupWindow popupWindow = new PopupWindow(poputView,ViewGroup.LayoutParams.WRAP_CONTENT,View.Group.LayoutParams.WRAP_CONTENT);
popupWindow.showAsDropDown(view); //显示在view下方
~~~

