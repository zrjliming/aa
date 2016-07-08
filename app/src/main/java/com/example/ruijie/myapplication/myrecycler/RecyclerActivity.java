package com.example.ruijie.myapplication.myrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruijie.myapplication.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();
        recyclerView.setAdapter(new MyAdapter());
    }

    public void onChange(View view){
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
    private void initData() {
        list =   new ArrayList<String>();
        for (int i = 0; i <10 ; i++) {
           list.add("我是你"+i+"大爷");
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{

        //        返回一个viewholder
        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            myViewHolder my=new myViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.recycler_item,parent,false));
            return my;
        }

        //       对绑定组件添加数据
        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
           holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class myViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            //            这一步进行优化进行查找
            public myViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.item_re_tv);
            }
        }

    }


}
