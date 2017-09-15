package com.example.matrix.accountms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import entity.DAOManager;
import entity.DaoSession;
import entity.Tb_flag;
import entity.Tb_flagDao;


public class Activity_note extends Activity {

    private ImageButton btn_new_flag;
    private ListView listView;
    private DaoSession daoSession ;
    private final static int REQUEST_CODE=101;
    private Tb_flagDao dao;
    private List<Tb_flag> mDatas;
    private  MyAdapterFlag myAdapterFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        daoSession  = DAOManager.getDaoSession(this);
        dao = daoSession.getTb_flagDao();
        listView = (ListView)findViewById(R.id.list_item_none);


        flush();
        btn_new_flag = (ImageButton) findViewById(R.id.btn_new_flag);
        btn_new_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_note.this,Activity_new_note.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        //长安
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    LinearLayout layout = (LinearLayout)listView.getChildAt(i);// 获得子item的layout
                    TextView tvnote = (TextView) layout.findViewById(R.id.tvnoteid);// 从layout中获得控件,根据其id
                    TextView tvnoteid = (TextView) layout.findViewById(R.id.tvnoteid);// 从layout中获得控件,根据其id
                    Tb_flag F = new Tb_flag();
                    F.set_id(Long.parseLong(tvnoteid.getText().toString()) );
                    F.setFlag(tvnote.getText().toString());
                    dao.delete(F);
                    Toast.makeText(Activity_note.this, "删除成功" , Toast.LENGTH_SHORT).show();
                    return true;
                }catch (Exception e){

                    Toast.makeText(Activity_note.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //单击
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              LinearLayout layout = (LinearLayout)listView.getChildAt(i);// 获得子item的layout
              TextView tvnote = (TextView) layout.findViewById(R.id.tvnote);// 从layout中获得控件,根据其id
              TextView tvnoteid = (TextView) layout.findViewById(R.id.tvnoteid);// 从layout中获得控件,根据其id
              Intent iu = new Intent(Activity_note.this,Activity_update_note.class);
              iu.putExtra("tvnote",tvnote.getText().toString());
              iu.putExtra("tvnoteid",tvnoteid.getText().toString());
              startActivityForResult(iu, REQUEST_CODE);
          }
      });
    }


    public void   flush(){
        mDatas =getData();
        myAdapterFlag = new MyAdapterFlag(this, mDatas);
        listView.setAdapter(myAdapterFlag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        flush();
        myAdapterFlag.notifyDataSetChanged();
        Toast.makeText(Activity_note.this, "", Toast.LENGTH_LONG).show();
        if (requestCode==REQUEST_CODE)
        {
            if (resultCode==Activity_new_note.RESULT_CODE)
            {
                //Tb_flag tb_flag = (Tb_flag)data.getSerializableExtra("edit_notetext");
                String tb_flag = data.getStringExtra("edit_notetext").toString();
                Toast.makeText(Activity_note.this, tb_flag, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<Tb_flag> getData(){
        List<Tb_flag> list = new  ArrayList<Tb_flag>();
        List<Tb_flag> tblist = dao.queryBuilder().list();
        Toast.makeText(Activity_note.this, tblist.size()+"", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < tblist.size(); i++) {
            list.add(tblist.get(i));
        }
        return  list;
    }
}
