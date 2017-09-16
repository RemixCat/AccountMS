package com.example.matrix.accountms;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import entity.DaoMaster;
import entity.DaoSession;

public class MainActivity extends AppCompatActivity {


    //控件
    private ImageView home, note, set;
    private ViewPager vp;


    //LocalActivityManager用来获取每个activity的view,放于Adapter中
    //MyViewPageAdapter用来放viewpager的内容
    //OnClickListener设置底部图片的点击事件
    //OnPageChangeListener设置图片的滑动事件
    private LocalActivityManager manager;
    private Home_ViewPageAdapter viewPageAdapter;
    private View.OnClickListener clickListener;
    private ViewPager.OnPageChangeListener pageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "accountms.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        vp = (ViewPager) findViewById(R.id.viewpager);
        InitView();

    }



    private void InitView() {
        // TODO Auto-generated method stub
        home = (ImageView) findViewById(R.id.main_home);
        note = (ImageView) findViewById(R.id.main_note);
        set = (ImageView) findViewById(R.id.main_set);
        clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                switch (v.getId()) {
                    case R.id.main_home:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.main_note:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.main_set:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        };

        home.setOnClickListener(clickListener);
        note.setOnClickListener(clickListener);
        set.setOnClickListener(clickListener);
        InitPager();

    }


    private void InitPager() {
        // TODO Auto-generated method stub
        pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                switch (arg0) {
                    case 0:
                        home.setImageResource(R.drawable.icon_home_2);
                        note.setImageResource(R.drawable.icon_note_1);
                        set.setImageResource(R.drawable.seet2);
                        break;
                    case 1:
                        home.setImageResource(R.drawable.icon_home_1);
                        note.setImageResource(R.drawable.icon_note_2);
                        set.setImageResource(R.drawable.seet2);
                        break;
                    case 2:
                        home.setImageResource(R.drawable.icon_home_1);
                        note.setImageResource(R.drawable.icon_note_1);
                        set.setImageResource(R.drawable.seet2_2);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        };

        AddActivitiesToViewPager();
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(pageChangeListener);
    }

    private void AddActivitiesToViewPager() {
        List<View> mViews = new ArrayList<View>();
        Intent intent = new Intent();

        intent.setClass(this, Activity_Home.class);
        intent.putExtra("id", 1);
        mViews.add(getView("QualityActivity1", intent));

        intent.setClass(this, Activity_note.class);
        intent.putExtra("id", 2);
        mViews.add(getView("QualityActivity2", intent));

        intent.setClass(this, Activity_set.class);
        intent.putExtra("id", 3);
        mViews.add(getView("QualityActivity3", intent));


        viewPageAdapter = new Home_ViewPageAdapter(mViews);
        vp.setAdapter(viewPageAdapter);

    }

    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


}
