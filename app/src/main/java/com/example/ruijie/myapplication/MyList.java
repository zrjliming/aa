package com.example.ruijie.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ruijie.myapplication.myasystask.Bean;

import java.util.ArrayList;

/**
 * Created by ruijie on 2016/6/13.
 * lru
 *缓存加载 图片
 *
 *
 *
 */
public class MyList extends BaseAdapter {
    private ArrayList<Bean> list ;
    private Context context;

    public MyList(ArrayList<Bean> list, Context context) {
        this.list = list;
        this.context = context;
         //    Options 是进行压缩
        //未避免图片太大导致oom，将图片压缩，同时使用lru算法加载图片
        BitmapFactory.Options  options=new BitmapFactory.Options();
        long maxMemory= Runtime.getRuntime().maxMemory()/1024;
        int size= (int) (maxMemory/8);

        //创建出一个LRU对行进行缓存图片
        LruCache<String,Bitmap> lru=new LruCache<String,Bitmap>(size){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getByteCount()/1024;
            }
        };

        //将图片压缩他的1/2
        options.inSampleSize=2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }



}
