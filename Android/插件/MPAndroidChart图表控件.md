# MPAndroidChart图表控件 v3.1.0

[MPAndroidChart 文档 ](https://weeklycoding.com/mpandroidchart-documentation/)

### 简单使用 (:chart_with_downwards_trend: 折线图)

~~~ xml
<com.github.mikephil.charting.charts.LineChart
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:id="@+id/testchart3"/>
~~~

~~~ java
int[] data = {5,4,8,2,5,4,3,7,5,6,2,9};
List<Entry> entries = new ArrayList<>();
for (int i = 0; i < data.length; i++) {
    entries.add(new Entry(i,data[i]));
}
LineDataSet ds = new LineDataSet(entries,"test3");
LineData ld = new LineData(ds);
lineChart.setData(ld);
~~~

## 图表种类

*   LineChart 折线图
*   BarChart 柱状图
*   PieChart 饼图 
*   ScatterChart 散点图
*   CandleStickChart K线图
*   RadarChart 雷达图
*   BubbleChart 气泡图

## 常用属性和方法

### 常规设置和样式

*   invalidate 刷新重绘
*   notifyDataSetChanged 数据更改，重新计算

样式

*   setBackgroundColor 背景色（可以在布局文件中设置）

*   setDescripttion 设置显示在图表右下角的说明文本

*   setDescriptionColor 设置说明文本的颜色

*   setDescriptionPosition 设置说明文本的位置

*   setDescriptionTypeface 绘制文本的用途

*   setDesctiptionTextSize 设置说明文本的大小 [6f--16f]

*   setNoDataText 设置图表为空时应显示的文本

*   setDrawGridBackground 启用后，图表背景矩形

*   setGridBackgroundColor 设置网格背景的绘制颜色

*   setDrawBorders 绘制图表边框

*   setBorderColor 边框颜色

*   setBorderWidth 边框宽度

    



### 交互

*   setTouchEnabled 触摸交互
*   setDragEnable 拖动
*   setScaleEnabled 缩放
    *   setScaleXEnabled x轴上的缩放
    *   setScaleYEnabled y轴上的缩放
    *   setPinchZoom 收缩缩放
    *   setDoubleTapToZoomEnable 双击缩放



*   legend 图例
*   Description 描述
*   XAxis x轴  chart.getAxisLeft Y轴
*   



