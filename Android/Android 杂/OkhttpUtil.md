# OkhttpUtil

```java
package com.example.mybanner.Activity;
import android.util.Log;import com.example.mybanner.OkHttpCallback;
import com.example.mybanner.R;
import com.example.mybanner.bean.AdBean;
import com.google.gson.Gson;import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class OkHttpUtil {
    //个人写的工具类 没有进行封装 
    //    public static String BASE_URL = "http:
    //" + MyApplication.getSp().getString("ip", "dasai.sdvcst.edu.cn") +
    //            ":" + MyApplication.getSp().getString("port", "8080");
    //    public static HttpUtil getBaseUrl() {
    //        if (mHttpUtil == null) {
    //            synchronized (HttpUtil.class) {
    //                if (mHttpUtil == null) {
    //                    mHttpUtil = new HttpUtil();
    //                }
    //            }
    //        }
    //        return mHttpUtil;
    //    }
    //    public static String getBaseUrl() {
    ////        return "";
    //    }    public static String token = "";
    //异步get请求   
    public static void doGet(String url, OkHttpCallback okHttpCallback) {    
        new Thread(new Runnable() {      
            @Override     
            public void run() {         
                OkHttpClient client = new OkHttpClient.Builder()      
                    .readTimeout(5, TimeUnit.SECONDS)        
                    .build();       
                Request request = new Request.Builder()      
                    .url("http://10.10.2.77:10001/" + url)   
                    .get()           
                    .build();   
                Call call = client.newCall(request);   
                call.enqueue(new Callback() {       
                    @Override        
                    public void onFailure(Call call, IOException e) {     
                        Log.i("异步get请求失败", "请求失败");   
                    }               
                    @Override         
                    public void onResponse(Call call, Response response) throws IOException {    
                        Log.i("异步get请求成功", response.toString());
                        //       Log.i("异步get请求成功",response.body().string()); 
                        // response.body().string();只能被使用一次，若第二次使用，则返回为null  因为response.body().string();调用后会触发关闭响应体  
                        String s = response.body().string();      
                        Log.i("okhttp测试", s);
                        //                    String s ="123456";            
                        // 通过多态，使用接口OkHttpCallback的实现类的对象okHttpCallback，调用其onResponse方法   
                        // 而onResponse具体在哪实现：在HomeFragment中通过调用doGet方法时，传入的匿名内部类new OkHttpCallback(){}中来实现   
                        okHttpCallback.onResponse(s);    
                    }                });
                //            try{
                //                Response response = call.execute();
                //                Log.i("同步Get请求","response:"+response.body().string());
                //                String successString="成功";
                //                AdBean adBean = new Gson().fromJson(successString,AdBean.class);
                //                
                //将返回的数据打印到控制台
                //            }catch (IOException e){
                //                e.printStackTrace();
                //打印堆栈轨迹
                //            }
            }        
        }).start(); 
    }   
    public static Call doGet2(String url) {   
        Call call = null;   
        OkHttpClient okHttpClient = new OkHttpClient();  
        Request request = new Request.Builder()   
            .get()              
            .addHeader("Authorization", token)       
            .url("http://10.10.2.77:10001" + url)     
            .build();    
        call = okHttpClient.newCall(request);    
        return call;  
    }   
    public static Call doPost(String url, String jsonS) {   
        Call call = null;  
        MediaType mediaType = MediaType.parse("application/json");    
        //jsonS请求参数    
        RequestBody requestBody = RequestBody.create(mediaType, jsonS);  
        Request request = new Request.Builder()   
            .post(requestBody)          
            .url("http://10.10.2.77:10001" + url)   
            .addHeader("Authorization", token)          
            .build();      
        OkHttpClient okHttpClient = new OkHttpClient(); 
        call = okHttpClient.newCall(request);    
        return call; 
    }   
    public void doPost() {     
        new Thread(new Runnable() {  
            @Override      
            public void run() {      
                OkHttpClient okHttpClient = new OkHttpClient.Builder()           
                    .readTimeout(5, TimeUnit.SECONDS)                 
                    .build();   
                FormBody formBody = new FormBody.Builder() 
                    //post请求声名FormBody对象 
                    .add("author", "xx")   
                    .add("bookName", "《android》")     
                    .build();          
                Request request = new Request.Builder()   
                    .url("http://10.10.2.77:10001")     
                    .post(formBody)               
                    .build();      
                Call call = okHttpClient.newCall(request);  
                try {                  
                    Response response = call.execute();    
                    Log.i("同步Post请求", "response:" + response.body().string());   
                } catch (IOException e) {        
                    e.printStackTrace();     
                }          
            }      
        }).start();
        //局部创建线程方法new一个线程放入Ruanable 写run方法 最后.start()开启  
    }   
    public static Call doPut(String url,String json){     
        Call call=null;      
        MediaType mediaType = MediaType.parse("application/json");    
        //jsonS请求参数    
        RequestBody requestBody = RequestBody.create(mediaType, json);  
        Request request = new Request.Builder()     
            .put(requestBody)          
            .url("http://10.10.2.77:10001" + url)   
            .addHeader("Authorization", token)   
            .build();      
        OkHttpClient okHttpClient = new OkHttpClient();    
        call = okHttpClient.newCall(request);  
        return call; 
    }    
    public static String getBaseUrl() {  
        return "http://10.10.2.77:10001/"; 
    }}
```
