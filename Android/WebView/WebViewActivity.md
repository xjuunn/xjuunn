# WebViewActivity

```java
public class DitieZonglantuActivity extends BaseActiVity { 
    private WebView ditieWebview;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);     
        setContentView(R.layout.activity_ditie_zonglantu);   
        showToolbar("线路总览图",0,true);      
        ditieWebview = findViewById(R.id.ditie_webview);    
        ditieWebview.getSettings().setJavaScriptEnabled(true); 
        ditieWebview.getSettings().setDomStorageEnabled(true);   
        ditieWebview.setWebViewClient(new WebViewClient());     
        String html = "<!DOCTYPE html><html lang=\"zh-CN\"><head><meta charset=\"utf-8\"><title>001</title></head><body><img src=\"http://10.10.2.77:10001/prod-api/profile/upload/image/2021/05/08/554f2392-1e1c-4449-b95c-327a5f7ec91d.jpeg\" width=\"1000\" /></body></html>";    
        WebSettings settings = ditieWebview.getSettings();      
        settings.setBuiltInZoomControls(true);  
        settings.setSupportZoom(true);      
        settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);    
        settings.setUseWideViewPort(true);     
        ditieWebview.setWebChromeClient(new WebChromeClient());    
        //ditieWebview.loadUrl("http://10.10.2.77:10001/prod-api/profile/upload/image/2021/05/08/554f2392-1e1c-4449-b95c-327a5f7ec91d.jpeg");
        ditieWebview.loadDataWithBaseURL(null, html,"text/html", "utf-8",null);     
        Toast.makeText(this, "双指缩放", Toast.LENGTH_LONG).show();  
    }}
```





### 核心代码

~~~ java
binding.webview.getSettings().setBuiltInZoomControls(true);//缩放
binding.webview.loadData(html,"text/html","UTF-8");//加载html
~~~

