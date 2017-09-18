package com.example.matrix.accountms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import entity.DaoMaster;
import entity.DaoSession;
import entity.Tb_inaccount;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class update_income2 extends Activity {
    private DaoSession session;
    private Tb_inaccount tb;
    private EditText money;
    private EditText remark;
    private EditText per;
    private TextView time;
    private Spinner type;
    private Button update;
    private Button delete;
    final int DATE_DIALOG = 1;

    private int year2;
    private int month2;
    private int day2;
    private int hour2;
    private int minute2;

    private String changetime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_income2);

        Calendar calendar = Calendar.getInstance();
        year2 =calendar.get(Calendar.YEAR);
         month2 =calendar.get(Calendar.MONTH);
         day2 =calendar.get(Calendar.DAY_OF_MONTH);
         hour2 =calendar.get(Calendar.HOUR_OF_DAY);
         minute2 =calendar.get(Calendar.MINUTE);






        Intent intent = getIntent();
        int id =Integer.parseInt(intent.getStringExtra("id"));
        try {


            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "accountms.db", null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            session = daoMaster.newSession();
            tb = session.getTb_inaccountDao().load((long)id);
             money = (EditText)findViewById(R.id.money);
            money.setText(tb.getMoney()+"");
             remark = (EditText)findViewById(R.id.remark);
            remark.setText(tb.getMark()+"");
             per = (EditText)findViewById(R.id.per);
            per.setText(tb.getHandler()+"");
             time = (TextView)findViewById(R.id.time);
            time.setText(tb.getTime()+"");
            type = (Spinner)findViewById(R.id.type);
            Resources res=getResources();
            String[] citys=res.getStringArray(R.array.type);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,citys);
            type.setAdapter(adapter2);
            for(int i=0;i<adapter2.getCount();i++){
                if(tb.getType().equals(adapter2.getItem(i).toString())){
                    type.setSelection(i,true);
                    break;
                }
            }


        }
        catch(Exception e){
            Toast.makeText(update_income2.this,e.toString(),Toast.LENGTH_LONG).show();
        }



        Button btn = (Button)findViewById(R.id.showtime);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(StrToDate(tb.getTime().trim()));

                    dialog(v, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
                }
                catch (Exception e)
                {

                    Toast.makeText(update_income2.this, e.toString(), Toast.LENGTH_LONG).show();

                }
                    //Toast.makeText(update_income2.this,calendar1.get(Calendar.YEAR)+"",Toast.LENGTH_SHORT).show();





            }
        });

        ;




        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        //修改
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tb.setHandler(per.getText().toString());
                tb.setMark(remark.getText().toString());
                tb.setTime(time.getText().toString());
                tb.setMoney( Double.parseDouble(money.getText().toString()));
                tb.setType(type.getSelectedItem().toString());
                session.getTb_inaccountDao().update(tb);
                Toast.makeText(update_income2.this,"修改成功",Toast.LENGTH_SHORT).show();
                setResult(1,new Intent());
                finish();

            }
        });

        //删除
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.getTb_inaccountDao().delete(tb);
                Toast.makeText(update_income2.this,"删除成功",Toast.LENGTH_SHORT).show();
                setResult(1,new Intent());
                finish();


            }
        });


    }



    public void dialog(View v,int year,int month,int day){

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Toast.makeText(update_income2.this,year+"-"+(month+1)+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
                changetime = year+"-"+(month+1)+"-"+dayOfMonth;
                time.setText(changetime);
            }
        };
        //两个参数的构造方法  不建议使用  对低版本不兼容
//        DatePickerDialog dialog = new DatePickerDialog(this,DatePickerDialog.THEME_DEVICE_DEFAULT_DARK);
//        DatePickerDialog dialog = new DatePickerDialog(this,DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT);
      // DatePickerDialog dialog = new DatePickerDialog(this,DatePickerDialog.THEME_HOLO_DARK);
//        DatePickerDialog dialog = new DatePickerDialog(this,DatePickerDialog.THEME_HOLO_LIGHT);
//        DatePickerDialog dialog = new DatePickerDialog(this,DatePickerDialog.THEME_TRADITIONAL);
        //多参数构造方法  对低版本兼容
       // DatePickerDialog dialog = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_DARK, dateSetListener, 1949, 10, 1);
//        DatePickerDialog dialog = new DatePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, dateSetListener, 1949, 10, 1);
          DatePickerDialog dialog = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_DARK, dateSetListener, year, month, day);
        //DatePickerDialog dialog = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_LIGHT, dateSetListener, 1949, 10, 1);
//        DatePickerDialog dialog = new DatePickerDialog(this, DatePickerDialog.THEME_TRADITIONAL, dateSetListener, 1949, 10, 1);
         dialog.show();


    }

    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
