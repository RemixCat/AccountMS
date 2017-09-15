package com.example.matrix.accountms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import entity.DAOManager;
import entity.DaoMaster;
import entity.DaoSession;
import entity.Tb_inaccount;
import entity.Tb_inaccountDao;

public class Income extends AppCompatActivity {
    private EditText money;
    private EditText time;
    private Spinner type;
    private String typer2;
    private EditText payer;
    private EditText remark;
    private DaoSession session;
    private Tb_inaccountDao income;
    private Tb_inaccount tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 第一种方法
        setContentView(R.layout.activity_income);

        datePicker();//生成时间拾取器
        addIncome();//相关事件
    }

    //定义时间拾取器
    public void datePicker(){
        final EditText getTime = (EditText)this.findViewById(R.id.getdatetime);
        getTime.setInputType(InputType.TYPE_NULL);
        final InputMethodManager inp = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);//键盘操作
        DatePicker picker = new DatePicker(this);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(picker);
        Calendar calendar = Calendar.getInstance();
//        初始化DatePicker控件
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                getTime.setText(i+":"+(i1+1)+":"+i2);
                getTime.setGravity(Gravity.CENTER);
                dialog.cancel();
            }
        });


        //选择时间按钮
        getTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(Income.this, "haha", Toast.LENGTH_SHORT).show();
                if(inp.isActive()){
                    inp.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
//                datepicker.setVisibility(View.VISIBLE);
                dialog.show();
                return false;
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


//    添加收入
    public void addIncome(){
        Button btnAdd = (Button)this.findViewById(R.id.btn_add_income);
        Button btnDefault = (Button)this.findViewById(R.id.btn_default_income);

        money = (EditText) this.findViewById(R.id.income_money);
        time = (EditText) this.findViewById(R.id.getdatetime);
        type = (Spinner) this.findViewById(R.id.income_type);
        payer = (EditText) this.findViewById(R.id.income_payer);
        remark = (EditText) this.findViewById(R.id.income_remark);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typer2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //添加收入记录
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    session = DAOManager.getDaoSession(Income.this);
                    income = session.getTb_inaccountDao();

                    tb = new Tb_inaccount();
                    tb.setMoney(Double.valueOf(money.getText().toString()));
                    tb.setTime(time.getText().toString());
                    tb.setHandler(payer.getText().toString());
                    tb.setType(typer2);
                    tb.setMark(remark.getText().toString());
                    income.insert(tb);
                    Income.this.finish();
                }
                catch(Exception ex){
                    showT(ex.getStackTrace().toString());
                }
            }
        });

//        取消按钮，关闭当前的页面
        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Income.this.finish();
            }
        });

    }
    
    public void showT(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
