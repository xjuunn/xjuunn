~~~ java
 public class InItModel<T1,T2>{
    public List<T2> initModel(List<T1> bean){
        List<T2> model = new ArrayList<>();
        for (T1 item : bean) {
            model.add(t2IModelgetItemData.getItemData(item));
        }
        return model;
    }
    private IModelgetItemData<T1,T2> t2IModelgetItemData;
    public List<T2> init(List<T1> bean,IModelgetItemData<T1, T2> t2IModelgetItemData){
        this.t2IModelgetItemData = t2IModelgetItemData;
        return initModel(bean);
    }
}
~~~

~~~ java
public interface IModelgetItemData<T1,T2> {
    T2 getItemData(T1 item);
}
~~~





~~~ java
List<ListModel> model = new InItModel<NewsListBean.RowsBean, ListModel>().init(bean.getRows(), item -> new ListModel(
         item.getCover(), item.getTitle(), item.getContent(), item.getLikeNum() + "", item.getReadNum() + ""
));
~~~



~~~ java
    public BaseRecyclerAdapter setallserverList(List model){
        BaseRecyclerAdapter<ListItemBinding, ListModel> adapter = new BaseRecyclerAdapter<ListItemBinding, ListModel>(model) {};
        adapter.setT1T2IRecyclergetItem(new IRecyclergetItem<ListItemBinding, ListModel>() {
            @Override
            public void getItem(ListItemBinding item, ListModel data, int pos) {
                Glide.with(ActivityModel.this).load(OkHttpUtil.baseUrl+data.getImgUlr()).fitCenter().into(item.img);
                item.title.setText(data.getTitle());
                item.content.setText(data.getContent());
                item.left.setText(data.getLeft());
                item.right.setText(data.getRight());
            }
        });
        binding.newsList.setAdapter(adapter);
        binding.newsList.setLayoutManager(new GridLayoutManager(ActivityModel.this,1));
        return adapter;
    }
~~~









# 2

~~~ java
public class InItModel<T1,T2>{
    public List<T2> initModel(List<T1> bean) {
        return bean.stream().map(t2IModelgetItemData::getItemData).collect(Collectors.toList());
    }

    private IModelgetItemData<T1,T2> t2IModelgetItemData;
    public List<T2> init(List<T1> bean,IModelgetItemData<T1, T2> t2IModelgetItemData){
        this.t2IModelgetItemData = t2IModelgetItemData;
        return initModel(bean);
    }
}
~~~

# 3

~~~ java
public class InItModel<T1, T2> {
    private IModelgetItemData<T1, T2> t2IModelgetItemData;
    public List<T2> init(List<T1> bean, IModelgetItemData<T1, T2> t2IModelgetItemData) {
        this.t2IModelgetItemData = t2IModelgetItemData;
        return bean.stream()
                .map(t2IModelgetItemData::getItemData)
                .collect(Collectors.toList());
    }
}
~~~

~~~ java
setallserverList(new InItModel<NewsListBean.RowsBean, ListModel>().init(bean.getRows(), item -> new ListModel(
                        item.getCover(), item.getTitle(), item.getContent(), item.getLikeNum() + "", item.getReadNum() + ""
                )));
~~~











# IgetBinding简化

~~~ java
  default T getBinding(LayoutInflater inflater, ViewGroup container, boolean b) {
        try {
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Method inflateMethod = clazz.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            return (T) inflateMethod.invoke(null, inflater, container, b);
        } catch (Exception e) {
            throw new RuntimeException("Unable to inflate binding. Error: " + e.getMessage(), e);
        }
    }
~~~

