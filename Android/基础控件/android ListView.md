# android ListView

# 布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ListVIewActivity1">

    <ListView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/listViewtest"  />
</LinearLayout>
```

# activity

```java
listView = findViewById(R.id.listViewtest);
listView.setAdapter(new MyListViewHolder(data,this));
```

# Adapter

```java
public class MyListViewHolder extends BaseAdapter {
    private List<String> data = new ArrayList<>();
    private Context context ;

    public MyListViewHolder(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.recycle1_item,viewGroup,false);
        }
        TextView textView = view.findViewById(R.id.recycle_item_text);
        textView.setText(data.get(i));
        return view ;
    }
}
```

# 点击事件

```java
@Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.recycle1_item,viewGroup,false) ;
        }
        TextView textView = view.findViewById(R.id.recycle_item_text);
        textView.setText(data.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("text", "onClick: "+data.get(i));
            }
        });
        return view;
    }
```
