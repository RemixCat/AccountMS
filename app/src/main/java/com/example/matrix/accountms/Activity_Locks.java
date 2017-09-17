package com.example.matrix.accountms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import entity.DAOManager;
import entity.DaoMaster;
import entity.DaoSession;
import entity.Tb_flag;
import entity.Tb_pwd;

public class Activity_Locks extends AppCompatActivity {

    private Button lockbtn;
    private EditText pwdtext;
    DaoSession session;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__locks);

        //创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "accountms.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();

        session = DAOManager.getDaoSession(Activity_Locks.this);
        List<Tb_pwd> tblist  = session.loadAll(Tb_pwd.class);
        lockbtn = (Button) this.findViewById(R.id.lockbtn);
        pwdtext = (EditText) this.findViewById(R.id.pwdtext);

        int size = tblist.size();


        if (size ==0){
            Intent itent =new Intent(Activity_Locks.this,MainActivity.class);
            startActivity(itent);
        }
        else {
            pwd = tblist.get(0).getPassword();
            if (pwd.length() ==0){

                Intent itent =new Intent(Activity_Locks.this,MainActivity.class);
                startActivity(itent);

            }

        }


        pwd = tblist.get(0).getPassword();


        lockbtn.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View view) {

                String txpwd = pwdtext.getText().toString();
                if(txpwd.equals(pwd)){

                    Toast.makeText(Activity_Locks.this, "密码正确", Toast.LENGTH_SHORT)
                            .show();

                    Intent itent =new Intent(Activity_Locks.this,MainActivity.class);
                    startActivity(itent);

                }
                else {

                    Toast.makeText(Activity_Locks.this, "密码错误", Toast.LENGTH_SHORT)
                            .show();

                }

            }
        });




    }
}
