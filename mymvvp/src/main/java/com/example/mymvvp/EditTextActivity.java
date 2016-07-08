package com.example.mymvvp;



import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.example.mymvvp.bean.User;
import com.example.mymvvp.databinding.ActivityMainBinding;
import com.example.mymvvp.userevent.UserEvent;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_text);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_text);
        User user=new User();
        UserEvent userEvent = new UserEvent(user);
        binding.setEvent(userEvent);

    }

    public void onorgin(View view) {


    }



}
