package com.example.matrix.accountms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import entity.DaoMaster;
import entity.DaoSession;

public class update_Income extends AppCompatActivity {

    private DaoSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__income);
        Intent intent = getIntent();
        String  id = intent.getStringExtra("id");
        try {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "accountms.db", null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            session = daoMaster.newSession();
        }
        catch(Exception e){
            Toast.makeText(update_Income.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
