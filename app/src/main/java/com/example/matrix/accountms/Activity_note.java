package com.example.matrix.accountms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import entity.DAOManager;
import entity.DaoSession;
import entity.Tb_flag;
import entity.Tb_flagDao;

public class Activity_note extends AppCompatActivity {


    private Button btn_new_flag;
    private ListView listviewnote;
    private DaoSession daoSession ;
    private final static int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        daoSession  = DAOManager.getDaoSession(this);

        listviewnote = (ListView)findViewById(R.id.listviewnote);

        //新建按钮
        btn_new_flag = (Button) findViewById(R.id.btn_new_flag);
        btn_new_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_note.this,Activity_new_note.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//
//        Toast.makeText(Activity_note.this, "sada", Toast.LENGTH_SHORT).show();
//        if (requestCode==REQUEST_CODE)
//        {
//            if (resultCode==Activity_new_note.RESULT_CODE)
//            {
//                //Tb_flag tb_flag = (Tb_flag)data.getSerializableExtra("edit_notetext");
//
//                String tb_flag = data.getStringExtra("edit_notetext").toString();
//
//                Toast.makeText(Activity_note.this, tb_flag, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Toast.makeText(Activity_note.this, "sada", Toast.LENGTH_SHORT).show();
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
}
