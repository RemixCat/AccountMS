package com.example.matrix.accountms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import entity.DAOManager;
import entity.DaoMaster;
import entity.DaoSession;
import entity.Tb_inaccount;
import entity.Tb_outaccount;

public class outaccount_update extends Activity {

    private List<Tb_outaccount> mData;
    private DaoSession session;
    private MyAdapter adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        //Toast.makeText(IncomeList.this,"dsad",Toast.LENGTH_LONG).show();
        mData= getData();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outaccount_update);

        mData= getData();
        adapter = new MyAdapter(this);


        ListView listView = (ListView) findViewById(R.id.list);
        //listView.setLayoutMode(android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ((int)l==-1){return;}
                try {
                    Intent intent = new Intent(outaccount_update.this, outaccount_update2.class);
                    intent.putExtra("id",mData.get(i).get_id().toString());
                    startActivityForResult(intent,1);
                    Toast.makeText(outaccount_update.this,mData.get(i).get_id().toString(),Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(outaccount_update.this,e.toString(),Toast.LENGTH_LONG).show();}
                //Toast.makeText(IncomeList.this,mData.get(i).get_id().toString(),Toast.LENGTH_SHORT).show();
            }
        });




    }


    private List<Tb_outaccount> getData() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "accountms.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        session = daoMaster.newSession();

        List<Tb_outaccount> listincome = session.loadAll(Tb_outaccount.class);

        return listincome;




    }




    public final class ViewHolder{
        public TextView Category;
        public TextView money;
        public TextView time;


    }


    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {

                holder=new ViewHolder();

                convertView = mInflater.inflate(R.layout.incomelistitem, null);
                holder.Category = (TextView) convertView.findViewById(R.id.Category);
                holder.money = (TextView)convertView.findViewById(R.id.money);
                holder.time = (TextView)convertView.findViewById(R.id.time);

                // holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            holder.Category.setText(mData.get(position).getType());
            holder.money.setText( "¥"+mData.get(position).getMoney());
            holder.time.setText((String)mData.get(position).getTime());



            return convertView;
        }




    }
}


