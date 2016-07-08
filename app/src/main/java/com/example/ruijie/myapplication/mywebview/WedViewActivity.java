package com.example.ruijie.myapplication.mywebview;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ruijie.myapplication.MainActivity;
import com.example.ruijie.myapplication.R;

import org.kxml2.wap.wv.WV;

public class WedViewActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wed_view);
        wv = (WebView) findViewById(R.id.wv);
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);

    }


    /**
     * 在你去导入stuiod是没有assets的文件夹的
     *你要在app.iml 去看以它是在什么目录下面
     * 你在对应的目录下操作才能识别   映射的
     *
     * 你一定要记得打开的js的标记
     * web 与js的交互
     *
     * WebChromeClient
       webView辅助chrome处理Javascript的对话框，

     *webView中调用js
     *webView.loadUrl("javascript:js函数名 ")
     *
     * 在js中调用webView
     *javascript:window.javaCode.toast("我来自Js");
     *
     webView.addJavascriptInterface(Object obj, String interfaceName)/
    @JavascriptInterface
     把java类注册成javacript对象,以便调用;参数一:要调用的对象,参数二:x需要被js调用的对外接口名
     *
     * @param view
     */
    public void onbutton(View view){
//        String url="http://www.baidu.com";
//        加载本地的js
        String NATIVE_URL_PATH = "file:///android_asset/jstest.html";
        wv.loadUrl(NATIVE_URL_PATH);

        wv.setWebChromeClient(new WebChromeClient(){

            //当JS调用弹出一个对话框时，回调该方法
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                return super.onJsAlert(view, url, message, result);
                AlertDialog.Builder builder = new AlertDialog.Builder(WedViewActivity.this);
                builder.setTitle("弹出框");
                builder.setMessage(message);
                builder.create();
                builder.show();
//                为了会再次调用dalog
                result.confirm();
                return true;
            }

            //当JS调用确认框的时候，回调该方法
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                System.out.println("JS调用确认框的");
                return super.onJsConfirm(view, url, message, result);
            }

            //当JS调用输入框时，回调该方法
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                System.out.println("JS输入框");
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            //当JS进度条的改变
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        //在WebView中注册一个可供页面JS调用的方法，
        // 参数1:传入的方法的对象（Object），参数2:方法名，相当于Key
        wv.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public void toast(String message){
                Toast.makeText(WedViewActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        },"javaCode");

        wv.setWebViewClient(new WebViewClient(){

            private ProgressDialog progressDialog;

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始执行网页");
                super.onPageStarted(view, url, favicon);
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(WedViewActivity.this);
                    progressDialog.setTitle("正在加载");
                    progressDialog.create();
                }
                progressDialog.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
//            就是为了用webview进行加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
//  WebView 进行调用
    public  void onjs(View view){
       wv.loadUrl("javascript:testJavaCallJs()");
    }

}
