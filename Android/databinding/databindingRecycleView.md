# databindingRecycleView

1. 设置build.gradle
   
    > buildFeatures {viewBinding truedataBinding true   }
    > 
    
2. 在Activity中设置布局文件的binding
   
    ~~~ java
    public class NewsActivity2 extends AppCompatActivity { 
        ActivityNews2Binding binding; 
        @Override 
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_news2);
            binding = ActivityNews2Binding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.textaa.setText("aaaaa");//设置布局中的文本 
        }
    ~~~
    
    
    
3. 创建RecycleView的Adapter

```java
public class RecycleViewAdapter<T> extends RecyclerView.Adapter<BindingViewHolder>
```

1. 创建BindingViewHoldre

```java
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder
```

1. 实现ViewHolder，修改参数

```java
private T mbinding;public BindingViewHolder(T mbinding) { 
    super(mbinding.getRoot()); 
    this.mbinding = mbinding;
}
public T getMbinding(){
    return mbinding;
}
```

1. Adapter

```java
private Context context;
private int res;
private List<T> list;
private LayoutInflater linearLayout;
public RecycleViewAdapter(Context context, int res, List<T> list) {
    this.context = context;
    this.res = res;
    this.list = list;
    linearLayout= LayoutInflater.from(context);
}
```

1. BindingViewHolder,return

```java
return new BindingViewHolder(DataBindingUtil.inflate(layoutInflater,resoures,parent,false));
```

1. onBindViewHolder，getItemCount返回list.size()

```java
ViewDataBinding mbinding = holder.getMbinding();
mbinding.setVariable(BR._all,list.get(position));//暂时使用all，稍后会改
mbinding.executePendingBindings();
```

1. 创建itemLayout

    ~~~ xml
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android">
        <data> 
            <import type="com.example.databinding.NewsBean.RowsDTO"/>
            <variable name="model" type="RowsDTO" />
        </data>
        <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"> 
            <TextView android:layout_width="match_parent"
                      android:layout_height="wrap_content" 
                      android:text="@{model.title}"
                      android:textSize="20sp" 
                      android:gravity="center"
                      android:id="@+id/textview"/> 
        </LinearLayout>
    </layout>
    ~~~

    

4. RecycleViewHodler使用

```java
RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(MainActivity.this,R.layout.news_recycle_item_layout,newsBean.getRows());
```

---
