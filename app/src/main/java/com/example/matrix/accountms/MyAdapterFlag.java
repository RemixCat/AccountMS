package com.example.matrix.accountms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import entity.Tb_flag;

/**
 * Created by lws on 2017/9/14.
 */

public class MyAdapterFlag extends BaseAdapter {

    private Context mContext;

    private List<Tb_flag> mDatas;
    private LayoutInflater mInflater;
    public boolean flage = false;

    public MyAdapterFlag(Context mContext, List<Tb_flag> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(this.mContext);
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }
    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 下拉项布局
            convertView = mInflater.inflate(R.layout.activity_note_childitem, null);
            holder = new ViewHolder();
            holder.tvnote = (TextView) convertView.findViewById(R.id.tvnote);
            holder.tvnoteid = (TextView) convertView.findViewById(R.id.tvnoteid);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Tb_flag dataBean = mDatas.get(position);
        if (dataBean != null) {
            holder.tvnote.setText(dataBean.getFlag());
            holder.tvnoteid.setText(dataBean.get_id() + "");
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tvnote;
        public TextView tvnoteid;
    }

}
