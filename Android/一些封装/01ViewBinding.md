[TOC]



# ViewBinding

开启viewBinding

~~~ json
    buildFeatures {
        viewBinding true
    }
~~~

在创建Activity的时候，AndroidStudio会自动根据xml生成一个类`activiyt_test1.xml`:point_right:`ActivityTest1Binding.class`,可以根据这个类，来设置布局里面的控件和返回content

~~~ java
ActivityTest1Binding binding;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityTest1Binding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.test1Btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.test1Text.setText("文本改变");
        }
    });
}
~~~

在fragment中原理相同，但inflate中的参数不同

~~~ java
//后面的参数都是fragment的onCreateView传过来的
binding = FragmentHomeBinding.inflate(inflate,container,false);
~~~

## 初步封装

### Activity

~~~ java
public abstract class BaseActivity1<T extends ViewBinding> extends AppCompatActivity {
    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewBinding();
        setContentView(binding.getRoot());
        initView();
    }
    protected abstract T getViewBinding();
    protected abstract void initView();
}

~~~

使用

~~~ java
public class TestActivity1 extends BaseActivity1<ActivityTest1Binding> {
    @Override
    protected ActivityTest1Binding getViewBinding() {
        return ActivityTest1Binding.inflate(getLayoutInflater());
    }
    @Override
    protected void initView() {  }
}
~~~

### fragment

~~~ java
public abstract class BaseFragment1<T extends ViewBinding> extends Fragment {
    protected T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding(inflater,container,false);
        initView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected abstract void initView();
    protected abstract T getViewBinding(LayoutInflater inflater, ViewGroup container, boolean b);
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
~~~

使用

~~~ java
public class DashboardFragment extends BaseFragment1<FragmentDashboardBinding> {
    @Override
    protected FragmentDashboardBinding getViewBinding(LayoutInflater inflater, ViewGroup container, boolean b) {
        return FragmentDashboardBinding.inflate(inflater,container,b);
    }
    @Override
    protected void initView() { }
}
~~~

## 进一步封装  （利用反射节省getViewBinding()方法）

~~~ java
public abstract class BaseActivity2 <T extends ViewBinding> extends AppCompatActivity {
    protected T binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBindingView();
        setContentView(binding.getRoot());
        initView();

    }
    protected abstract void initView();
    private T getBindingView() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType){
            Class<?> viewBindingClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
            try {
                Method inflate = viewBindingClass.getDeclaredMethod("inflate", LayoutInflater.class);
                binding = (T) inflate.invoke(null,getLayoutInflater());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return binding;
    }
}
~~~

将反射获取binding的方法写进接口

~~~ java
public interface IgetViewBinding2<T extends ViewBinding>{
    default T getViewBinding(LayoutInflater inflater, ViewGroup container,boolean b ){
        T binding = null;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType){
            Class<?> viewBindingClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
            try {
                if (container==null){
                    Method inflate = viewBindingClass.getDeclaredMethod("inflate",LayoutInflater.class);
                    binding = (T) inflate.invoke(null,inflater);
                }else{
                    Method inflate = viewBindingClass.getDeclaredMethod("inflate",LayoutInflater.class,ViewGroup.class,boolean.class);
                    binding = (T) inflate.invoke(null,inflater,container,b);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return binding;
    }

}
~~~

使用

~~~ java
public abstract class BaseFragment6 <T extends ViewBinding> extends Fragment implements IgetViewBinding2<T> {
    protected T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding(inflater,container,false);
        initView();
        return binding.getRoot();
    }
    protected abstract void initView();
}

~~~

















