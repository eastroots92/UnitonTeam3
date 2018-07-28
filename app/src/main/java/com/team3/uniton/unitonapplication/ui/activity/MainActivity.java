package com.team3.uniton.unitonapplication.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.team3.uniton.unitonapplication.App;
import com.team3.uniton.unitonapplication.model.MainModel;
import com.team3.uniton.unitonapplication.model.MainResignationItem;
import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.ui.customView.CustomMainItemView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<MainResignationItem> mDatas = new ArrayList<>();

    LinearLayout mListLayout;

    Button mWriteResignationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mWriteResignationButton = findViewById(R.id.write_button);

        mWriteResignationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteResignationActivity.class);
                startActivity(intent);
            }
        });


        requestDatas();

        for (int i = 0 ;i< 10 ; i++) {
            MainResignationItem data = new MainResignationItem();
            ArrayList<String> contents = new ArrayList<>();
            contents.add("contents 0");
            contents.add("contents 1");
            contents.add("contents 2");
            data.setContents(contents);
            data.setDate("2018-06-20");

            mDatas.add(data);
        }

        mListLayout = findViewById(R.id.list_layout);


        for (MainResignationItem item : mDatas) {

            CustomMainItemView view = new CustomMainItemView(this);
            view.setData(item);
            mListLayout.addView(view);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    void requestDatas() {
        SharedPreferences currentSP = getSharedPreferences("USER_MODEL", MODE_PRIVATE);
        String userID = currentSP.getString("USER_ID","");

        App.serverApi.getMain(userID).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {

            }
        });
    }
}
