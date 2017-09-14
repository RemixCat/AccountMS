package com.example.matrix.accountms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import entity.Tb_flag;

/**
 * Created by ios06 on 17/9/14.
 * wjl
 */

public class Activity_new_note extends Activity {

    public final static int RESULT_CODE = 1;
    private EditText edit_notetext;

    private Button btn_note_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        edit_notetext = (EditText) findViewById(R.id.edit_notetext);

        btn_note_back = (Button) findViewById(R.id.btn_note_back);

        btn_note_back.setOnClickListener(new MyListener());


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
