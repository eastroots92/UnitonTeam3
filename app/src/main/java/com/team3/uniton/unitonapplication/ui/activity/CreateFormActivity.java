package com.team3.uniton.unitonapplication.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.model.Info;
import com.team3.uniton.unitonapplication.model.Status;
import com.team3.uniton.unitonapplication.util.ApiUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFormActivity extends AppCompatActivity {
    private ServerApi serverApi;

    ImageButton calenderBtn;
    TextView dateText;
    Button m_btn_apply;
    private EditText et_company;
    private EditText et_department;
    private EditText et_position;

    private SharedPreferences sharedPreferences;
    private Info info;


    public String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_form );

        calenderBtn = findViewById( R.id.calender_Btn );
        dateText = findViewById( R.id.dateText );
        m_btn_apply = findViewById(R.id.btn_apply);
        et_company = findViewById(R.id.et_company);
        et_department = findViewById(R.id.et_department);
        et_position = findViewById(R.id.et_position);

        serverApi = ApiUtil.getServerApi();
        m_btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestInfoManager();
            }
        });

        setDate();
    }

    private void requestInfoManager() {
        info = getInfoData();

        Gson gson = new Gson();
        String json = gson.toJson(info);
        Log.e("JSON", json);

        requestInfoData(info);
    }

  private void requestInfoData(Info info) {
      SharedPreferences currentSP = getSharedPreferences("USER_MODEL", MODE_PRIVATE);
      String userID = currentSP.getString("USER_ID","");
      serverApi.setInfo(userID, info)
        .enqueue(new Callback<Status>() {
          @Override
          public void onResponse(Call<Status> call, Response<Status> response) {
            String result = response.body().status;
            if ("200".equals(result)) {
              setUserInfo();
              startMain();
            }
          }

          @Override
          public void onFailure(Call<Status> call, Throwable t) {
            Log.e("REQUESTLOGIN_ERROR",t.toString());
          }
        });
  }

  private void setUserInfo() {
    sharedPreferences = getSharedPreferences("USER_MODEL", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("USER_COMPANY", info.getCompany_name());
    editor.putString("USER_DEPARTMENT", info.getDepartment());
    editor.putString("USER_POSITION", info.getPosition());
    editor.putString("USER_YEAR", info.getJoin_year());
    editor.putString("USER_MONTH", info.getJoin_month());
    editor.putString("USER_DAY", info.getJoin_day());
    editor.commit();
  }

  private Info getInfoData() {
        String currentCompany = et_company.getText().toString();
        String currentDepartment = et_department.getText().toString();
        String currentPosition = et_position.getText().toString();
        String currentDate = dateText.getText().toString();
        String currentYear = currentDate.split("년 ")[0];
        currentDate = currentDate.split("년 ")[1];
        String currentMonth = currentDate.split("월 ")[0];
        currentDate = currentDate.split("월 ")[1];
        String currentDay = currentDate.split("일")[0];

        Info currentInfo = new Info(currentYear,currentMonth,currentDay,currentCompany,currentDepartment,currentPosition);

        return currentInfo;
    }

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
                final DatePickerDialog dialog = new DatePickerDialog(CreateFormActivity.this, new DatePickerDialog.OnDateSetListener() {
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
