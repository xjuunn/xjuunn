# Notification 通知

~~~ java
private NotificationManager manager;
private Notification notification;

		btnTest = findViewById(R.id.btn_test);
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("test1","测试通知",NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        notification = new NotificationCompat.Builder(this,"test1")
                .setContentTitle("测试通知")
                        .setContentText("通知内容")
                                .setSmallIcon(R.drawable.testicon)
                                        .build();
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.notify(1,notification);

            }
        });
~~~



## 通知重要程度设置，NotificationManager类中

*   IMPORTANCE_NONE 关闭通知
*   IMPORTANCE_MIN 开启通知，不会弹出，没有提示音，状态栏中无显示
*   IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
*   IMPORTANCE_DEFAULT 开启通知，不会弹出，发出提示音，状态栏中显示
*   IMPORTANCE_HIGH 开启通知，会弹出，发出提示音，状态栏中显示



## NotificationCompat.Builder常用方法

1.  setContentTitle(string string) 设置标题
2.  setContentText(string string) 设置通知内容
3.  setSmallIon(int icon) 设置小图标

以上是必须设置的

4.  setLargeIcon(Bitmap icon) 设置大图标
5.  setColor(int argb) 设置小图标的颜色
6.  setContentIntent(PendingIntent intent) 设置点击通知后跳转的跳转意图
7.  setAutoCancel(boolean boolean) 设置点击通知后自动清除通知
8.  setWhen(long when) 设置通知被创建的时间



#### 取消通知

manager.cancel(1);









