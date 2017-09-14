package com.example.matrix.accountms;

import android.app.Activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Activity_Home extends Activity {

    private ListView in_list;
    private ListView out_list;
    private ListView note_list;
    private List<Map<String, Object>> inData;
    private List<Map<String, Object>> outData;
    private List<Map<String, Object>> noteData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        in_list = findViewById(R.id.in_zuijin);
        out_list = findViewById(R.id.out_zuijin);
        note_list = findViewById(R.id.note_zuijin);
        noteData = getnoteData();
        outData = getoutData();
        inData = getinData();
        Home_List_Adapter_note adapternote = new Home_List_Adapter_note(noteData, Activity_Home.this);
        Home_List_Adapter_in adapterin = new Home_List_Adapter_in(inData, Activity_Home.this);
        Home_List_Adapter_in adapterout = new Home_List_Adapter_in(outData, Activity_Home.this);

        out_list.setAdapter(adapterout);
        in_list.setAdapter(adapterin);
        note_list.setAdapter(adapternote);
    }


    private List<Map<String, Object>> getinData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list_cont", "+120");
        map.put("list_date", "2017-9-14 12:10");
        list.add(map);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("list_cont", "+300");
        map2.put("list_date", "2017-9-14 12:10");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("list_cont", "+1200");
        map3.put("list_date", "2017-9-14 12:10");
        list.add(map3);


        return list;
    }

    private List<Map<String, Object>> getoutData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list_cont", "-120");
        map.put("list_date", "2017-9-14 12:10");
        list.add(map);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("list_cont", "-300");
        map2.put("list_date", "2017-9-14 12:10");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("list_cont", "-1200");
        map3.put("list_date", "2017-9-14 12:10");
        list.add(map3);


        return list;
    }

    private List<Map<String, Object>> getnoteData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list_cont", "明天要交学费，1000000块！！");
        map.put("list_date", "2017-9-14 12:10");
        list.add(map);

        return list;
    }
}
