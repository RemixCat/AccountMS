package com.example.matrix.accountms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import entity.*;

public class Sysset extends Activity {
    EditText txtpwd;// 创建EditText对象
    Button btnSet, btnsetCancel;// 创建两个Button对象
    private DaoSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysset);// 设置布局文件

        txtpwd = (EditText) findViewById(R.id.txtPwd);// 获取密码文本框
        btnSet = (Button) findViewById(R.id.btnSet);// 获取设置按钮
       // btnsetCancel = (Button) findViewById(R.id.btnsetCancel);// 获取取消按钮
        session = new DAOManager().getDaoSession(Sysset.this);
        btnSet.setOnClickListener(new OnClickListener() {// 为设置按钮添加监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Tb_pwd pwd = new Tb_pwd();   // 创建Pwd对象
                List<Tb_pwd> list = session.loadAll(Tb_pwd.class);
                if (list.size()>0)//用户存在时直接修改第一条数据密码
                {
                     pwd = list.get(0);
                     pwd.setPassword(txtpwd.getText().toString());
                     session.update(pwd);

                }
                else//用户不存在时新建用户，新增数据库数据
                {
                    pwd.setPassword("");
                    session.insert(pwd);

                }
                Toast.makeText(Sysset.this, "〖密码〗设置成功！", Toast.LENGTH_SHORT)
                        .show();

                Intent itent =new Intent(Sysset.this,Activity_Home.class);
                startActivity(itent);

            }
        });

//        btnsetCancel.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                txtpwd.setText("");// 清空密码文本框
//                txtpwd.setHint("请输入密码");// 为密码文本框设置提示
//            }
//        });
    }
}


