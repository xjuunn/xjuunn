# Toolbar

### Toolbar

1. 创建Toolbar的xml文件
2. 要显示Toolbar的布局include Toolbar文件
3. 创建Base类文件，继承AppCompatActivity
4. 要显示Toolbar的java文件继承Base类
5. ~~~ java
    public class BaseActi extends AppCompatActivity {
        public void showToolbar(String title,Boolean bool){ 
            Toolbar toolbara = findViewById(R.id.toolbar);//这个是include的id 
            TextView textView = findViewById(R.id.toolbar_title); 
            textView.setText(title);
            toolbara.setTitle("");//关闭自带的title 
            setSupportActionBar(toolbara);
            if (getSupportActionBar()!=null&&bool){//显示返回钮
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } 
        } 
        
        @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            //返回钮的点击事件 
            if (item.getItemId()== android.R.id.home){
                onBackPressed(); 
            } 
            return true; 
        } 
    }
    ~~~
6. 在Activity的java文件
   
    ```java
    showToolbar("title",true);//true表示显示返回钮
    ```
    

---

### 创建带menu的toolbar

1. 创建menu文件
   
    ```xml
    <item    android:id="@+id/history"    android:icon="@drawable/hos"    app:showAsAction="always"    android:title="历史"/>
    ```
    
2. ~~~ java
    @Override 
    public boolean onCreateOptionsMenu(Menu menu) { 
        getMenuInflater().inflate(R.menu.toolbarmenu,menu); 
        return true; 
    } 
    
    @Override 
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { 
        if (item.getItemId()==R.id.history){ 
            Intent intent = new Intent(TingnaActivity.this,TingnaHisActivity.class);
            startActivity(intent);
        } 
        return true; 
    }
    ~~~

3. 
