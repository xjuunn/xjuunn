# 	RecyclerView

# layout文件

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".RecycleActivity">
    <androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/recycle1"/>
</LinearLayout>
```

# item layout

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="200px">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="文字"
        android:textSize="100px"
        android:id="@+id/recycle_item_text"
        android:gravity="center"/>

</LinearLayout>
```

# Activity

```java
recyclerView = findViewById(R.id.recycle1);
        List<String> data = new ArrayList<>();
        data.add("文字1");
        data.add("文字2");
        data.add("文字3");
        data.add("文字4");
        data.add("文字5");
        data.add("文字6");
        data.add("文字7");
        recyclerView.setAdapter(new MyAdapater(data,this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
```

# Adapter

```java
public class MyAdapater extends RecyclerView.Adapter<MyAdapater.MyViewHolder> {
    private List<String> data = new ArrayList<>();
    private Context context;

    public MyAdapater(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recycle1_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycle_item_text);
        }
    }
}
```

# 点击事件

```java
@Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
        int i = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test", "onClick: "+data.get(i));
            }
        });
    }
```
