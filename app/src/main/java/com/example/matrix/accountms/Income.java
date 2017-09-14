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
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Income extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        datePicker();
    }

    //定义时间拾取器
    public void datePicker(){
        final EditText getTime = (EditText)this.findViewById(R.id.getdatetime);
        getTime.setInputType(InputType.TYPE_NULL);
        final InputMethodManager inp = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);//键盘操作
        DatePicker picker = new DatePicker(this);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(picker);
        final Date date = new Date(System.nanoTime());

        Calendar calendar = Calendar.getInstance();
//        初始化DatePicker控件
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                getTime.setText(i+":"+i1+":"+i2);
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


}
