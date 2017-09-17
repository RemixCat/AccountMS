package com.example.matrix.accountms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Handler;

public class Activity_splash extends Activity {

    //延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
        new Handler().postDelayed(new Runnable() {
            public void run() {
                goHome();
            }
        }, SPLASH_DELAY_MILLIS);
    }


    private void goHome() {
        Intent intent = new Intent(Activity_splash.this, Activity_Locks.class);
        Activity_splash.this.startActivity(intent);
        Activity_splash.this.finish();
    }

    }


