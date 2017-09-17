package com.example.matrix.accountms;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import entity.DAOManager;
import entity.DaoSession;
import entity.Tb_inaccount;
import entity.Tb_inaccountDao;
import entity.Tb_outaccount;
import entity.Tb_outaccountDao;

public class Outcome extends AppCompatActivity {
    private EditText out_money;
    private EditText out_time;
    private EditText out_address;
    private EditText out_remark;
    private Spinner out_type;
    private String out_typestr;


    private DaoSession session;
    private Tb_outaccountDao outcome;
    private Tb_outaccount tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        generateDatePicker();
        outComeMethod();
    }

//    生成日期选择控件
    public void generateDatePicker(){
        out_time = (EditText)this.findViewById(R.id.out_time);
        out_time.setInputType(InputType.TYPE_NULL);

        final InputMethodManager inp = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);//键盘操作

        DatePicker picker = new DatePicker(this);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(picker);
        Calendar calendar = Calendar.getInstance();

        //选择时间按钮
        out_time.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(Income.this, "haha", Toast.LENGTH_SHORT).show();
                if(inp.isActive()){
                    inp.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                dialog.show();
                return false;
            }
        });

        //        初始化DatePicker控件
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                out_time.setText(i+":"+ (i1+1) +":"+i2);
                out_time.setGravity(Gravity.CENTER);
                dialog.cancel();
            }
        });

        //点击控件后消失
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

//    添加事件
    public void outComeMethod(){
        Button btnAdd = (Button)this.findViewById(R.id.btn_add_outcome);
        Button btnDefault = (Button)this.findViewById(R.id.btn_default_outcome);

        out_type = (Spinner)this.findViewById(R.id.outcome_type);
        out_address = (EditText)this.findViewById(R.id.outcome_address);
        out_remark = (EditText) this.findViewById(R.id.outcome_remark);
        out_money = (EditText) this.findViewById(R.id.outcome_money);
        out_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                out_typestr = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    session = DAOManager.getDaoSession(Outcome.this);
                    outcome = session.getTb_outaccountDao();

                    tb = new Tb_outaccount();
                    tb.setMoney(Double.valueOf(out_money.getText().toString()));
                    tb.setTime(out_time.getText().toString());
                    tb.setType(out_typestr);
                    tb.setAddress(out_address.getText().toString());
                    tb.setMark(out_remark.getText().toString());
                    outcome.insert(tb);

                    Outcome.this.finish();

                    Toast.makeText(Outcome.this, "保存成功！", Toast.LENGTH_SHORT)
                            .show();
                }
                catch(Exception ex){
                    Toast.makeText(Outcome.this, "哈哈系统出错拉！", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Outcome.this.finish();
            }
        });
    }

}
