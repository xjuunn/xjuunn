# BaseRvAdapter

~~~ java
public class BaseRecyclerAdapter<T1 extends ViewBinding,T2 extends List> extends RecyclerView.Adapter<BaseRecyclerAdapter<T1,T2>.ViewHolder> implements IgetViewBinding2<T1>{
    protected T1 mbinding;
    protected T2 mdata;
    public BaseRecyclerAdapter(T2 data) {
        this.mdata = data;
    }
    protected IRecyclerBinding<T1> mBindData;
    public void setBindData(IRecyclerBinding<T1> bindData){
        mBindData = bindData;
    }
    @NonNull
    @Override
    public BaseRecyclerAdapter<T1, T2>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mbinding = getViewBinding(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(mbinding.getRoot());
    }
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter<T1, T2>.ViewHolder holder, int position) {
        if (mBindData!=null){
            mBindData.bindData(mbinding,position);
        }
    }
    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
~~~

接口

~~~ java
public interface IRecyclerBinding <T extends ViewBinding>{
    void bindData(T mBinding,int pos);
}
~~~

### 使用

~~~ java
List<String> data = new ArrayList<>();
BaseRecyclerAdapter<RecycleItemBinding,List<String>> adapter= new BaseRecyclerAdapter<RecycleItemBinding, List<String>>(data){};
adapter.setBindData(new IRecyclerBinding<RecycleItemBinding>() {
    @Override
    public void bindData(RecycleItemBinding mBinding, int pos) {
        mBinding.title.setText("test");
    }
});
binding.recycle.setAdapter(adapter);
binding.recycle.setLayoutManager(new GridLayoutManager(TestActivity1.this,2));
~~~

