package com.example.ruijie.myapplication.myimagedemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ruijie.myapplication.R;
import com.lidroid.xutils.BitmapUtils;
import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private ArrayList<ImageBean> list;
    private BitmapUtils bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mrecyclerView = (RecyclerView) findViewById(R.id.my_recy);
//        默认是linerlayout
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        bu = new BitmapUtils(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }).start();
        Myadapter my = new Myadapter();
        mrecyclerView.setAdapter(my);

    }

    class Myadapter extends RecyclerView.Adapter<Myadapter.myViewHolder> {

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            把item的布局放到viewholder中
//            在holder进行查找组件
//            在onbind中设置值
            myViewHolder viewHolder=new myViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.recycler_item,parent,false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            ImageBean bean = list.get(position);
            holder.textView.setText(bean.getName());
//            bu.display(holder.imageView,bean.getImage_url());
            holder.sm.setImageUrl(bean.getImage_url());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder {
          TextView textView;
//          ImageView imageView;
           SmartImageView sm;
            public myViewHolder(View itemView) {
                super(itemView);
                  textView = (TextView) itemView.findViewById(R.id.item_re_tv);
//                imageView =  (ImageView) findViewById(R.id.item_iv);
                  sm= (SmartImageView)itemView.findViewById(R.id.smart_iv);
            }
        }
    }

    /**
     * curl -H "Content-type: application/json" -X POST -d '{"listType":"actor_list_by_select", "typeName":"actor_select_latest","page":1, "size":10 }' http://121.42.60.133:8080/GuDuo/publisher/getActorList
     * HttpClient  不能进行链接
     * HttpClient hc=new DefaultHttpClient();
     * HttpPost hp=new HttpPost(url);
     * try {
     * HttpResponse response = hc.execute(hp);
     * int statusCode = response.getStatusLine().getStatusCode();
     * if(statusCode==200){
     * HttpEntity entity = response.getEntity();
     * String string = EntityUtils.toString(entity);
     * System.out.println("__---"+string);
     * makeJosn(string);
     * }
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     */

    private void initData() {
        String url = "http://121.42.60.133:8080/GuDuo/publisher/getActorList";
        try {
            URL murl = new URL(url);
            HttpURLConnection mhc = (HttpURLConnection) murl.openConnection();
            mhc.setReadTimeout(5000);
            mhc.setConnectTimeout(5000);
            mhc.setRequestMethod("POST");
            mhc.setRequestProperty("Content-Type","application/json");
            mhc.connect();
            JSONObject mjo=new JSONObject();
            mjo.put("listType","actor_list_by_select");
            mjo.put("typeName","actor_select_latest");
            mjo.put("page","1");
            mjo.put("size","10");
            OutputStream os = mhc.getOutputStream();
            os.write(mjo.toString().getBytes());

            int responseCode = mhc.getResponseCode();
            if(responseCode==200){
                InputStream in = mhc.getInputStream();
                String json = IoUtils.getJson(in);
                System.out.println(json+"___________");
                makeJosn(json);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void makeJosn(String string) {
        list.clear();
        try {
            JSONObject jo=new JSONObject(string);
            JSONArray ja = jo.getJSONArray("actorList");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject joo = ja.getJSONObject(i);
                ImageBean bean = new ImageBean();
                String name = joo.getString("name");
                String image_url = joo.getString("primaryImageUrl");
                bean.setName(name);
                bean.setImage_url(image_url);
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //    改变方式
//    打一个标记 进行更改
    boolean flag=false;
    public void onImageChange(View view) {
       if(!flag){
           flag=true;
           mrecyclerView.setLayoutManager(new GridLayoutManager(this,2));
       }else{
           flag=false;
           mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
       }
    }
    public void onStargged(View view){

    }

}
