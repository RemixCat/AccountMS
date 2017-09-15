package com.example.matrix.accountms;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import entity.DAOManager;
import entity.DaoSession;
import entity.Tb_flag;
import entity.Tb_flagDao;

/**
 * Created by ios06 on 17/9/14.
 * wjl
 */

public class Activity_new_note extends AppCompatActivity {

    public final static int RESULT_CODE = 101;
    private EditText edit_notetext;
    private Button btn_note_back;
    private Button btn_note_save;
    private DaoSession daoSession;
    private Tb_flagDao tbdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        daoSession = DAOManager.getDaoSession(this);
        tbdao = daoSession.getTb_flagDao();
        edit_notetext = (EditText) findViewById(R.id.edit_notetext);
        btn_note_back = (Button) findViewById(R.id.btn_note_back);
        btn_note_save = (Button) findViewById(R.id.btn_note_save);
        btn_note_back.setOnClickListener(new MyListener());
        btn_note_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String txt = edit_notetext.getText().toString();
                    if (txt.trim().length() == 0) {
                        Toast.makeText(Activity_new_note.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Long maxid =0L;
                        List<Tb_flag> list = tbdao.queryBuilder().list();
                        Tb_flag flag = new Tb_flag();
                        if (list.size()!=0){
                            maxid = list.get(list.size() - 1).get_id();
                            flag.setFlag(txt);
                            flag.set_id(maxid+1);
                        }else{
                            flag.setFlag(txt);
                            flag.set_id(1);
                        }
                        tbdao.insertOrReplace(flag);
                        Toast.makeText(Activity_new_note.this, "保存成功" + txt + " maxid " + maxid, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(Activity_new_note.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            Tb_flag flag = new Tb_flag();
            flag.setFlag(edit_notetext.getText().toString());
            intent.putExtra("edit_notetext", "sadsasadasdSAA");
            setResult(RESULT_CODE, intent);
            Activity_new_note.this.finish();
        }
    }
}
