# CS GDI+

## GDI+

核心类

- Graphics — 绘图，创建：this.CreateGraphics()、

辅助类

- Point — 点
- Size — 尺寸，宽高
- Rectangle — 矩形，宽高，坐标
- Color
- Font

工具类

- Pen — 笔，绘制线条
- Brush — 画刷，绘制色块，填充颜色

图像类

- Bitmap — 用像素点表示的图片

```C#
Rectangle rectangle = new Rectangle(10,10,100,50);
//坐标10，10，宽100，高50
Pen pen = new Pen(Color.Blue);
e.GraPhics.DrawRectangle(pen,Rectangle);//Paint事件
Brush brush = new SolidBrush(Color.Red);//红色画刷
e.Graphics.FillRectangle(brush,rectangle);//填充
// TextureBrush --- 纹理画刷
string path = "图片url";
Bitmap bitmap = new Bitmap(path);
TextureBrush texture = new TextureBrush(bitmap);//创建纹理画刷
e.Graphice.fillEllipse(texture,);
// LinearGradientBrush --- 线性画刷
Point p1 = new Point(0,10);
Point p2 = new Point(10,10);
LinearGradientBrush linearGradientBrush = new LinearGradientBrush(p1,p2,Color.Red,Color.Orange);
e.Graphics.FillEllipse(linearGradinetBrush,110,90,100,100);
```

> graphics.SmoothingMOde = SmoothingMode.HighQuality; //高质量
