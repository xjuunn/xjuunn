# android ViewPager2

# 布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".VP21">
    <androidx.viewpager2.widget.ViewPager2
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/viewpager21"/>

</LinearLayout>
```

# Acitvity

```java
List<String> data = new ArrayList<>();
        data.add("文字2");
        data.add("文字3");
        data.add("文字4");
        data.add("文字5");
        data.add("文字6");
        data.add("文字7");
        vp2 = findViewById(R.id.viewpager21);
        vp2.setAdapter(new ViewPager2Adapter(data,this));
```

Adapter

```java
public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewPagerHolder> {
    private List<String> data = new ArrayList<>();
    private Context context;

    public ViewPager2Adapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerHolder(LayoutInflater.from(context).inflate(R.layout.vp21item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size()  ;
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.vp21text);
        }
    }
}
```
