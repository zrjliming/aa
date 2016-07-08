package com.example.ruijie.myapplication.httpweather;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ruijie.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * c78e0ec429d73dc224fa635ac4d605d0
 *
 * http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=您申请的KEY
 *
 *
 */
public class WeatherActivity extends AppCompatActivity {

    private String  str = "http://v.juhe.cn/weather/index?";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tv = (TextView) findViewById(R.id.tv);

        getWeatherJson();
        
    }


    public void getWeatherJson() {

            new AsyncTask<String,Void,String>(){

                private String strjson="1233";

                @Override
                protected String doInBackground(String... params) {
                    try {
                        URL url=new URL("http://v.juhe.cn/weather/index?"+"format=2&"+"key=c78e0ec429d73dc224fa635ac4d605d0"+"&cityname="+ URLEncoder.encode("北京","utf-8"));
                        HttpURLConnection hu= (HttpURLConnection) url.openConnection();

                        hu.setReadTimeout(500);
                        hu.setConnectTimeout(500);
                        hu.setRequestMethod("GET");
                        hu.getContent();
                        int responseCode = hu.getResponseCode();
                        if(responseCode==200){
                            InputStream in = hu.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                     /*       byte[] by=new byte[in.available()];
                            in.read(by);
                            strjson = new String(by,"utf-8");*/
                            strjson=bufferedReader.readLine();
                            System.out.println(strjson+"_______");
                        }

                    } catch (IOException e) {
                            e.printStackTrace();
                    }
                    return strjson;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if(s!=null){
                        tv.setText(s);
                    }

                }
            }.execute(str);







    }


}
