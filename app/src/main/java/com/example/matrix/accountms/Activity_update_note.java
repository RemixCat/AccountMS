package com.example.matrix.accountms;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Activity_update_note extends AppCompatActivity {

    public final static int RESULT_CODE = 102;
    private EditText edit_notetext;
    private ImageButton btn_note_back;
    private ImageButton btn_note_save;
    private DaoSession daoSession;
    private Tb_flagDao tbdao;
    private Long tvnoteid;
    private String tvnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        daoSession = DAOManager.getDaoSession(this);
        tbdao = daoSession.getTb_flagDao();
        edit_notetext = (EditText) findViewById(R.id.edupdate);
        Intent getIntent = getIntent();
        tvnote = getIntent.getStringExtra("tvnote");
        tvnoteid = Long.parseLong(getIntent.getStringExtra("tvnoteid"));
        edit_notetext.setText(tvnote);
        btn_note_back = (ImageButton) findViewById(R.id.btn_update_back);
        btn_note_save = (ImageButton) findViewById(R.id.btn_note_update);

        btn_note_back.setOnClickListener(new MyListener());
        btn_note_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String txt = edit_notetext.getText().toString();
                    if (txt.trim().length() == 0) {
                        Toast.makeText(Activity_update_note.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Tb_flag flag = new Tb_flag();
                        flag.setFlag(txt);
                        flag.set_id(tvnoteid);
                        tbdao.update(flag);Toast.makeText(Activity_update_note.this, "保存成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Activity_update_note.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            setResult(RESULT_CODE, intent);
            Activity_update_note.this.finish();
        }
    }
}
