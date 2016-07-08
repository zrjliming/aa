package com.example.ruijie.myapplication.myasystask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ruijie.myapplication.R;

public class AsynTaskActivity extends AppCompatActivity {

    private TextView tv;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task2);
        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(10);
    }

    /**
     *
     * 执行之后
     * 第一个是prameter
     * Intent 这个参数是执行的进度
     * 第三个是结果
     * @param view
     */
    public  void onbutton(View view){

      new AsyncTask<String, Integer, String>() {

//          开始
          @Override
          protected void onPreExecute() {
              super.onPreExecute();
              tv.setText("开始执行");
          }
          //任务执行的方法，相当于开了一个子线程
          @Override
          protected String doInBackground(String... params) {

              for (int i = 0; i < 100; i++) {
                  try {
                      Thread.sleep(100);
                      publishProgress(i+1);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
              return "执行完成";
          }
//          执行中间进更新
          @Override
          protected void onProgressUpdate(Integer... values) {
              super.onProgressUpdate(values);
              pb.setProgress(values[0]);
          }

          //任务处理完成后调用该方法
          @Override
          protected void onPostExecute(String s) {
              super.onPostExecute(s);
              tv.setText(s);
          }
      }.execute("","");

/*

   new AsyncTask<String, Void, Bitmap>(){
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected Bitmap doInBackground(String... params) {
           return null;
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           super.onPostExecute(bitmap);
       }


   }.execute("");*/





    }

}
