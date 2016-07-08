package com.example.ruijie.myapplication.myimagedemo;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ruijie on 2016/7/7.
 *  设置点击事件 和长安事件
 *
 *
 */
public class StaggeredAdapter  extends RecyclerView.Adapter<StaggeredAdapter.myViewHolder>
implements  View.OnClickListener ,View.OnLongClickListener{


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        public myViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 设置点击事件 和长安事件
     * 这边定义接口 和抽象方法
     * 在setAdapter 时候进行方法实现
     * postion 的实现就是在你 自定义接口的时候 postion v.getTag
     * 在onBindViewHolder中进行settag
     *
     */
    private onRecylerViewOnItem     onItemListener=null;
    private onRecylerViewOnLongItem onLongItemListener=null;

    public  interface  onRecylerViewOnItem{
        void onItemClick(View view , String data);
    }
    public  interface  onRecylerViewOnLongItem{
        void onItemLongClick(View view , String data);
    }
    public void setOnRecylerViewOnItem(onRecylerViewOnItem onItemListener){
       this.onItemListener=onItemListener;
    }
    public void setOnRecylerViewOnLongItem(onRecylerViewOnLongItem onLongItemListener){
        this.onLongItemListener=onLongItemListener;
    }
    @Override
    public void onClick(View v) {
          if(onItemListener!=null){
              onItemListener.onItemClick(v,(String) v.getTag());
          }
    }

    @Override
    public boolean onLongClick(View v) {
        if(onLongItemListener!=null){
            onLongItemListener.onItemLongClick(v, (String) v.getTag());
        }
//        事件被消费掉了
        return true;
    }

}
