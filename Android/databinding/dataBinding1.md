# dataBinding1

### 步骤

1. 在build.gradle中添加databinding = true
2. 创建data类
3. 布局文件修改为databinding格式
4. java文件中
   
    ```java
    private ActivityTestBinding binding;
    private String TAG = "aaaaaa";
    @Overrideprotected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test); 
        binding.setTest(new Bean(11,"佩奇","猫"));
    }
    ```
    

```java
public class TestActivity extends AppCompatActivity {   
    private ActivityTestBinding binding;  
    private String TAG = "aaaaaa";  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);    
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        Bean bean = new Bean();  
        bean.setName("111");   
        binding.setVariable(BR.test,bean);
        binding.executePendingBindings();   
    }}
```

