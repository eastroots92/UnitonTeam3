package com.team3.uniton.unitonapplication.ui.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.model.ResignationItem;
import com.team3.uniton.unitonapplication.util.ApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResignationActivity extends AppCompatActivity {
    private ServerApi serverApi;
    private SharedPreferences sharedPreferences;
    private String uid;
    private String name;
    private String company;
    private String department;
    private String position;
    private String date;
    private ResignationItem resignationItem;

    private boolean isRaw;

    private TextView tv_info_department;
    private TextView tv_info_position;
    private TextView tv_info_name;
    private TextView tv_resign_01;
    private TextView tv_resign_02;
    private TextView tv_resign_03;
    private TextView tv_date;
    private TextView tv_info_sign_name;
    private Button btn_apply;

    int mRid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_resignation );

        mRid = getIntent().getIntExtra("ID", 1);

        init();
    }

    private void init() {
        serverApi = ApiUtil.getServerApi();
        initData();
        initListener();

        resignChanger();
    }

    private void resignChanger() {
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initResignLayout(isRaw);
            }
        });
    }

    private void initListener() {
        tv_info_department = findViewById(R.id.tv_info_department);
        tv_info_position = findViewById(R.id.tv_info_position);
        tv_info_name = findViewById(R.id.tv_info_name);
        tv_info_sign_name = findViewById(R.id.tv_info_sign_name);
        tv_resign_01 = findViewById(R.id.tv_resign_01);
        tv_resign_02 = findViewById(R.id.tv_resign_02);
        tv_resign_03 = findViewById(R.id.tv_resign_03);
        tv_date = findViewById(R.id.tv_date);
        btn_apply = findViewById(R.id.btn_apply);
    }

    private void initData() {
        sharedPreferences = getSharedPreferences("USER_MODEL", MODE_PRIVATE);
        uid = sharedPreferences.getString("USER_ID", "");
        name = sharedPreferences.getString("USER_NAME","");
        company = sharedPreferences.getString("USER_COMPANY", "");
        department = sharedPreferences.getString("USER_DEPARTMENT", "");
        position = sharedPreferences.getString("USER_POSITION", "");

        requestResign();

    }

    private void requestResign() {
        SharedPreferences currentSP = getSharedPreferences("USER_MODEL", MODE_PRIVATE);
        String userID = currentSP.getString("USER_ID","");

        serverApi.getResign(userID,String.valueOf(mRid)) // mock "3", "1"
          .enqueue(new Callback<ResignationItem>() {
              @Override
              public void onResponse(Call<ResignationItem> call, Response<ResignationItem> response) {
                  resignationItem = response.body();
                  isRaw = true;
                  initInfoLayout();
                  initResignLayout(isRaw);
              }

              @Override
              public void onFailure(Call<ResignationItem> call, Throwable t) {

              }
          });
    }

    private void initInfoLayout() {
        tv_info_name.setText(name);
        tv_info_department.setText(department);
        tv_info_position.setText(position);
        tv_info_sign_name.setText("신청인:" + name);
        tv_date.setText("");
    }

    private void initResignLayout(boolean raw) {
        if (raw) {
            tv_resign_01.setText(resignationItem.before_first_reason);
            tv_resign_02.setText(resignationItem.before_second_reason);
            tv_resign_03.setText(resignationItem.before_third_reason);
            isRaw = false;
        } else {
            tv_resign_01.setText(resignationItem.after_first_reason);
            tv_resign_02.setText(resignationItem.after_second_reason);
            tv_resign_03.setText(resignationItem.after_third_reason);
            isRaw = true;
        }
    }
}
