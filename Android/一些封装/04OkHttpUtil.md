# OkHttpUtil

~~~ java
public class OkHttpUtil {
    public static String BaseUrl = "";
    public static String token = "";
    private static OkHttpClient httpClient;
    private static MediaType mediaType = MediaType.parse("application/app");

    public static OkHttpClient getHttpClient(){
        if (httpClient==null)
            return new OkHttpClient();
        return httpClient;
    }
    public static Call getCall(String url){
        Request request = new Request.Builder()
                .url(BaseUrl+url)
                .addHeader("Authorization",token)
                .get()
                .build();
        return getHttpClient().newCall(request);
    }
    public static <T> void doGet(String url,Class<T> tClass,IRunOnUI<T> tiRunOnUI){
        getCall(url).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) { }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                T bean = BaseApp.gson(response.body().string(),tClass);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tiRunOnUI.run(bean);
                    }
                });
            }
        });
    }
}
~~~

接口

~~~ java
public interface IRunOnUI <T>{
    void run(T bean);
}
~~~

### 使用

~~~ java
OkHttpUtil.doGet("url", AllServeListBean.class, new IRunOnUI<AllServeListBean>() {
@Override
     public void run(AllServeListBean bean) {

     }
});
~~~

