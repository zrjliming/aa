package com.example.ruijie.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.ruijie.myapplication.httpweather.WeatherActivity;
import com.example.ruijie.myapplication.myasystask.AsynTaskActivity;
import com.example.ruijie.myapplication.myimagedemo.ImageDeomActivity;
import com.example.ruijie.myapplication.myimageloader.MiageLoderActivity;
import com.example.ruijie.myapplication.myrecycler.RecyclerActivity;
import com.example.ruijie.myapplication.mywebview.WedViewActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.kobjects.util.Util;
import org.ksoap2.transport.HttpsTransportSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_name;
    // 设置访问的url地址
//    static final String myURL = "http://web.36wu.com/WeatherService.asmx?WSDL";
    static final String httpURL = "http://10.0.2.2:8080/wang1409f/My";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_name = (TextView) findViewById(R.id.main_tv);
        startActivity(new Intent(this,ImageDeomActivity.class));
/*//        HttpsTransportSE transportSE = new HttpsTransportSE();
        new Thread() {
            @Override
            public void run() {
//                httpGet();
//                fanPost();
            }

        }.start();*/



    }

    private void fanPost() {
        /**
         * 创建客户端对象
         * 请求的方式HttpGet
         * 通过客户端发送请求
         * 传参
         *httpResponse 获得结果集
         *
         */
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp = new HttpPost(httpURL);
        try {
            List<NameValuePair> parametes = new ArrayList<NameValuePair>();
            parametes.add(new BasicNameValuePair("name", "zhangsan"));
            parametes.add(new BasicNameValuePair("age","18"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parametes, "utf-8");
            hp.setEntity(entity);
            HttpResponse response = hc.execute(hp);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity res_entity = response.getEntity();
                final String s = EntityUtils.toString(res_entity);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_name.setText(s);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void httpGet() {
        /**
         * 创建客户端对象
         * 请求的方式HttpGet
         * 通过客户端发送请求
         *httpResponse 获得结果集
         *
         */
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(httpURL);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
//          获得是io流     httpResponse.getEntity().getContent();
                HttpEntity entity = httpResponse.getEntity();
                final String s = EntityUtils.toString(entity, "utf-8");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_name.setText(s);
                    }
                });

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
