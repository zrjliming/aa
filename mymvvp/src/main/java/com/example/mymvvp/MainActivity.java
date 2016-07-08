package com.example.mymvvp;





import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.example.mymvvp.bean.User;
import com.example.mymvvp.databinding.ActivityMainBinding;
import com.example.mymvvp.userevent.UserEvent;


/**
 * 在添加EditText内容事件的event和bean 好像是不能同时存在
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,EditTextActivity.class));
/*        User user = new User();

        user.age = "18ww";
        user.name = "dada";

        UserEvent uv=new UserEvent(user);
        ActivityMainBinding binding;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.setUser(user);
//        binding.setEvent();*/


    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);

    }





}
