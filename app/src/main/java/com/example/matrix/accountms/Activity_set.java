package com.example.matrix.accountms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Activity_set extends AppCompatActivity {

    private LinearLayout setbtn;

    private LinearLayout helptbn;

    private LinearLayout aboutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);// 设置布局文件

        setbtn = (LinearLayout) findViewById(R.id.setbtn);
        helptbn =(LinearLayout) findViewById(R.id.helptbn);
        aboutbtn =(LinearLayout) findViewById(R.id.aboutbtn);

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent =new Intent(Activity_set.this,Sysset.class);
                startActivity(itent);
            }
        });



        helptbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences setting = getSharedPreferences("hah",0);
                setting.edit().putBoolean("FIRST", true).commit();
                Intent itent =new Intent(Activity_set.this,MainActivity.class);
                startActivity(itent);
            }
        });

        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent =new Intent(Activity_set.this,Activity_About.class);
                startActivity(itent);
            }
        });

    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }



}


