package com.team3.uniton.unitonapplication.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.model.Info;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateFormActivity extends AppCompatActivity {

    ImageButton calenderBtn;
    TextView dateText;
    Button m_btn_apply;
    private EditText et_company;
    private EditText et_department;
    private EditText et_position;

    public String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_form );

        calenderBtn = findViewById( R.id.calender_Btn );
        dateText = findViewById( R.id.dateText );
        m_btn_apply = findViewById(R.id.applyBtn);
        et_company = findViewById(R.id.et_company);
        et_department = findViewById(R.id.et_department);
        et_position = findViewById(R.id.et_position);


        m_btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestInfoManager();
                startMain();
            }
        });

        setDate();
    }

//    private void requestInfoManager() {
//        Info info = getInfoData();
//    }

//    private Info getInfoData() {
//        String currentCompany = et_company.get
//
//
//        Info currentInfo = new Info();
//
//        return currentInfo;
//    }

    private void startMain() {
        Intent intent = new Intent(CreateFormActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void setDate() {
        final Calendar cal = Calendar.getInstance();
        String nowDateString = getDateString();
        dateText.setText(nowDateString);

        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(CreateFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateString = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth);
                        dateText.setText(dateString);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });
    }

    // 현재 날짜 세팅
    public String getDateString() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
        return simpleDateFormat.format(date);
    }
}
