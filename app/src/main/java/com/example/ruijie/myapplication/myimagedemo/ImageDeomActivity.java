package com.example.ruijie.myapplication.myimagedemo;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.ruijie.myapplication.R;


/**
 * 用tabhost进行搭建
 * 要在实现上啦刷新
 *
 */
public class ImageDeomActivity extends TabActivity {


    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_deom);
        rg = (RadioGroup) findViewById(R.id.rg);
        final TabHost th = getTabHost();
        th.addTab(th.newTabSpec("主页1").setIndicator("主页").setContent(new Intent(this,HomeActivity.class)));
        th.addTab(th.newTabSpec("收藏1").setIndicator("收藏").setContent(new Intent(this,CollectActivity.class)));
        th.addTab(th.newTabSpec("我的1").setIndicator("我的").setContent(new Intent(this,MyActivity.class)));

//        设置默认的
        th.setCurrentTabByTag("主页1");
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                  switch (checkedId){
                      case R.id.rb1:
                         th.setCurrentTabByTag("主页1");
                          break;
                      case R.id.rb2:
                          th.setCurrentTabByTag("收藏1");
                          break;
                      case R.id.rb3:
                          th.setCurrentTab(2);
                          break;
                  }
            }
        });
    }


}
