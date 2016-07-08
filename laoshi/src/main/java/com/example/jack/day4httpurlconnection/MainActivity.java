package com.example.jack.day4httpurlconnection;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getButton;
    private TextView showText;
    private String urlPath = "http://10.0.2.2:8080/ser";

    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 0:
                    String string = (String) msg.obj;
                    showText.setText(string);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButton = (Button) findViewById(R.id.get_button);
        showText = (TextView) findViewById(R.id.show_text);
        getButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_button:
                getResult();
                break;
        }
    }

    private void getResult() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //先创建出了一个URL对象，urlPath：是我们访问接口地址
                    URL url = new URL(urlPath);
                    //URL链接对象，通过URL对象打开一个connection链接对像
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    //设置urlConnection对象链接超时
                    urlConnection.setConnectTimeout(5000);
                    //设置urlConnection对象获取数据超时
                    urlConnection.setReadTimeout(5000);
                    //设置本次urlConnection请求方式
                    urlConnection.setRequestMethod("GET");
                    //调用urlConnection的链接方法，线程等待，等待的是服务器所给我们返回的结果集
                    urlConnection.connect();
                    //获取本次网络请求的状态码
                    int code = urlConnection.getResponseCode();
                    //如果本次返回的状态吗是200（成功）
                    if (code == 200) {
                        //调用urlConnection.getInputStream得到本次请求所返回的结果流
                        InputStream inputStream = urlConnection.getInputStream();
                        //创建一个BufferedReader，去读取结果流
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String readLine;
                        StringBuffer buffer = new StringBuffer();
                        while ((readLine = reader.readLine()) != null){
                            buffer.append(readLine);
                        }
                        //读取完结果流之后所得到的结果
                        String result = buffer.toString();
                        Message message = new Message();
                        message.what = 0;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
