package com.example.matrix.accountms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Remixcat
 * Created by ios05 on 2017/9/14.
 */

public class Home_List_Adapter_note extends BaseAdapter {



    private List<Map<String, Object>> mData;
    private LayoutInflater mInflater = null;


    public Home_List_Adapter_note(List<Map<String,Object>> mData,Context context)
    {

        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Home_List_data list_data = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            list_data = new Home_List_data();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.home_notelist, null);
            list_data.list_cont = (TextView) convertView.findViewById(R.id.list_cont2);
            list_data.list_date = (TextView)convertView.findViewById(R.id.list_date2);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(list_data);
        }else
        {
            list_data = (Home_List_data)convertView.getTag();
        }

        list_data.list_cont.setText((String)mData.get(position).get("list_cont"));
        list_data.list_date.setText((String)mData.get(position).get("list_date"));


        return convertView;
    }
}
