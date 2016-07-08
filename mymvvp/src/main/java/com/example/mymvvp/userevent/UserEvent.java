package com.example.mymvvp.userevent;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.mymvvp.bean.User;

/**
 * Created by ruijie on 2016/6/12.
 */
public class UserEvent {
    private User user;
    public UserEvent(User user) {
        this.user = user;
    }

    public TextWatcher user_name=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            s.append(user.name);
        }
    };
    public  TextWatcher user_age=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            s.append(user.age);
        }
    };


}
