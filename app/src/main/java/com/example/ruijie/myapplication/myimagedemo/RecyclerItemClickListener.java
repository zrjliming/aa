package com.example.ruijie.myapplication.myimagedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ruijie on 2016/7/8.
 *
 *  在adpter进行写的话
 我们会发现，上面的代码耦合性还是有点高，事件直接与适配器发生了耦合，
 除此之外，我们还应该有更好的方法来处理这个点击事件。
 是的，不是有OnItemTouchListener监听器吗，再配合手势不就可以吗。

 自己定义一个类RecyclerItemClickListener 去继承RecyclerView.OnItemTouchListener
 里面定义一个接口 是点击事件 和长安事件的抽象方法
 RecyclerItemClickListener的构造函数
 final RecyclerView recyclerView, OnItemClickListener listener
 通过Gesture手势进判断

 recyclerView.addOnItemTouchListener 直接这样进行调用就行了

 *
 *
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetector detector;

    //    1接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
//   构造函数  里面去实现手势监听
    private OnItemClickListener mListener;
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener monItemTouchListener){
        mListener=monItemTouchListener;
        detector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

//            点击
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // 轻击触摸屏后,弹起，必须返回true，否则无法触发单击
                return true;
            }
//            长安
            @Override
            public void onLongPress(MotionEvent e) {
                // 根据findChildViewUnder(float x, float y)来算出哪个item被选择了
                View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
                // 有item被选则且监听器不为空触发长按事件
                if(childViewUnder!=null&&mListener!=null){
                    mListener.onItemLongClick(childViewUnder,recyclerView.getChildAdapterPosition(childViewUnder));
                }
            }
        });
    }
//       事件拦截 mGestureDetector.onTouchEvent(e)
//       返回的是true 的话 是证明那么点击已经弹起来
//        返回就是true
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if(childView!=null&&mListener!=null&&detector.onTouchEvent(e)){
            mListener.onItemClick(childView,rv.getChildAdapterPosition(childView));
            return  true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}



/*

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mListener;

    private GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // 轻击触摸屏后,弹起，必须返回true，否则无法触发单击
                return true;
            }


            @Override
            public void onLongPress(MotionEvent e) {
                // 长按
// 根据findChildViewUnder(float x, float y)来算出哪个item被选择了
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
// 有item被选则且监听器不为空触发长按事件
                if (childView != null && mListener != null) {
                    mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    //        事件拦截 mGestureDetector.onTouchEvent(e) 返回的是true 的话 是证明那么点击已经弹起来
//        返回就是true
    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
// 触发单击事件
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}
*/









