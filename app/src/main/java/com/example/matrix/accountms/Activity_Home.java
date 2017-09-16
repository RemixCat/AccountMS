package com.example.matrix.accountms;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.greendao.annotation.OrderBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.DAOManager;
import entity.DaoSession;
import entity.Tb_flag;
import entity.Tb_inaccount;
import entity.Tb_outaccount;


public class Activity_Home extends Activity {

    private ListView in_list;
    private ListView out_list;
    private ListView note_list;
    private List<Map<String, Object>> inData;
    private List<Map<String, Object>> outData;
    private List<Map<String, Object>> noteData;
    DaoSession session;
    private TextView mony_in;
    private TextView mony_out;
    private LinearLayout addincomeli; //添加收入
    private LinearLayout adddisbursementli; //添加支出
    private LinearLayout Controlincomeli;//收入管理
    private LinearLayout Controldisbursementli;//支出管理
    Home_List_Adapter_note adapternote;
    Home_List_Adapter_in adapterin;
    Home_List_Adapter_in adapterout;


    protected void onResume() {
        super.onResume();
        onCreate(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        in_list = findViewById(R.id.in_zuijin);
        out_list = findViewById(R.id.out_zuijin);
        note_list = findViewById(R.id.note_zuijin);
        mony_in = findViewById(R.id.mony_in);
        mony_out = findViewById(R.id.mony_out);
        noteData = getnoteData();
        outData = getoutData();
        inData = getinData();
        adapternote = new Home_List_Adapter_note(noteData, Activity_Home.this);   //实例化适配器
        adapterin = new Home_List_Adapter_in(inData, Activity_Home.this);
        adapterout = new Home_List_Adapter_in(outData, Activity_Home.this);

        //刷新执行
        final Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                // 在此处添加执行的代码

                noteData = getnoteData();
                outData = getoutData();
                inData = getinData();
                adapternote = new Home_List_Adapter_note(noteData, Activity_Home.this);   //实例化适配器
                adapterin = new Home_List_Adapter_in(inData, Activity_Home.this);
                adapterout = new Home_List_Adapter_in(outData, Activity_Home.this);
                note_list.setAdapter(adapternote);//设置适配器
                in_list.setAdapter(adapterin);
                out_list.setAdapter(adapterout);

                mony_in.setText(getallin());
                mony_out.setText(getallout());
                handler.postDelayed(this, 50);// 50是延时时长
            }
        };
        handler.postDelayed(runnable, 50);// 打开定时器，执行操作




        note_list.setAdapter(adapternote);//设置适配器
        in_list.setAdapter(adapterin);
        out_list.setAdapter(adapterout);


        mony_in.setText(getallin());
        mony_out.setText(getallout());


        //收入管理
        Controlincomeli = (LinearLayout)findViewById(R.id.Controlincomeli);
        Controlincomeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent =new Intent(Activity_Home.this,IncomeList.class);
                startActivity(itent);

            }
        });

        //支出管理
        Controldisbursementli = (LinearLayout)findViewById(R.id.Controldisbursementli);
        Controldisbursementli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent =new Intent(Activity_Home.this,outaccount_update.class);
                startActivity(itent);

            }
        });

        //添加收入
        addincomeli = (LinearLayout)findViewById(R.id.addincomeli);
        addincomeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent =new Intent(Activity_Home.this,Income.class);
                startActivity(itent);

            }
        });

        //添加支出
        adddisbursementli = (LinearLayout)findViewById(R.id.adddisbursementli);
        adddisbursementli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent =new Intent(Activity_Home.this,Outcome.class);
                startActivity(itent);

            }
        });

    }



    //获取收入总和
    private String getallin(){

        session = DAOManager.getDaoSession(Activity_Home.this);
        List<Tb_inaccount> tblist  = session.loadAll(Tb_inaccount.class);


        double sum = 0;

        if (tblist.size() ==0){
            return "0";
        }else {
            for (int i =0;i<tblist.size();i++){

                 sum +=tblist.get(i).getMoney();

            }
            return sum+"";
        }


    }


    //获取收入总和
    private String getallout(){

        session = DAOManager.getDaoSession(Activity_Home.this);
        List<Tb_outaccount> tblist  = session.loadAll(Tb_outaccount.class);


        double sum = 0;

        if (tblist.size() ==0){
            return "0";
        }else {
            for (int i =0;i<tblist.size();i++){

                sum +=tblist.get(i).getMoney();

            }
            return "-"+sum;
        }


    }




    //获取最近收入
    private List<Map<String, Object>> getinData() {


        session = DAOManager.getDaoSession(Activity_Home.this);


        List<Tb_inaccount> tblist  = session.loadAll(Tb_inaccount.class);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        int size = tblist.size();

        if(size == 0){

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list_cont", "");
            map.put("list_date", "");
            list.add(map);


        }
        if (size <3){

            for (int i =size-1;i>=0;i--){

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list_cont", "+"+tblist.get(i).getMoney());
                map.put("list_date", tblist.get(i).getTime());
                list.add(map);

            }

        }else {

            for (int i =size-1;i>=size-3;i--){

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list_cont", "+"+tblist.get(i).getMoney());
                map.put("list_date", tblist.get(i).getTime());
                list.add(map);

            }


        }




        return list;
    }


    //获取最近支出
    private List<Map<String, Object>> getoutData() {

        session = DAOManager.getDaoSession(Activity_Home.this);
        List<Tb_outaccount> tblist  = session.loadAll(Tb_outaccount.class);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        int size = tblist.size();

        if(size == 0){

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list_cont", "");
            map.put("list_date", "");
            list.add(map);


        }
        if (size<3){

            for (int i =size-1;i>=0;i--){

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list_cont", "-"+tblist.get(i).getMoney());
                map.put("list_date", tblist.get(i).getTime());
                list.add(map);

            }

        }else {

            for (int i =size-1;i>=size-3;i--){

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("list_cont", "-"+tblist.get(i).getMoney());
                map.put("list_date", tblist.get(i).getTime());
                list.add(map);

            }


        }



        return list;
    }


    //获取最近便签
    private List<Map<String, Object>> getnoteData() {

        session = DAOManager.getDaoSession(Activity_Home.this);
        List<Tb_flag> tblist  = session.loadAll(Tb_flag.class);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        if (tblist.size() ==0){

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list_cont", "");
            map.put("list_date", "");
            list.add(map);

        }else {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list_cont", tblist.get(tblist.size()-1).getFlag());
            map.put("list_date", "最近");
            list.add(map);

        }

        return list;
    }


//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return true;
//        }
//        return false;
//    }

    //记录用户首次点击返回键的时间
    private long firstTime=0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(Activity_Home.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
