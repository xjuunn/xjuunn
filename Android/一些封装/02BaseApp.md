# BaseApp

~~~ java
public class BaseApp extends Application {
    public static final String FIRST_IN = "FIRST_IN";
    public static final String TOKEN = "TOKEN";
    public static final String IPADDRESS = "IPADDRESS";
    public static final String PORT = "PORT";
    public static SharedPreferences sSharedPreferences;
    public static SharedPreferences.Editor sEditor;
    public static Context scontext;
    public static Gson sgson;
    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = getSharedPreferences(getString(R.string.app_name),Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
        scontext = this;
        sgson = new Gson();
    }
    public <T> T gson(String json,Class<T> tClass){
        T t = sgson.fromJson(json,tClass);
        return t;
    }
    public void glide(String url, ImageView target){
        Glide.with(scontext).load(OkHttpUtil.BaseUrl+url).fitCenter().into(target);
    }
    public void toast(String msg){
        Toast.makeText(scontext, msg, Toast.LENGTH_SHORT).show();
    }
    public void startIntent(Context source,Class<?> target){
        Intent intent = new Intent(source,target);
        startActivity(intent);
    }
    public void startIntent(Context source,Class<?> target, boolean isFinish){
        Intent intent = new Intent(source,target);
        startActivity(intent);
        if (isFinish)
            ((Activity)source).finish();
    }
}
~~~

