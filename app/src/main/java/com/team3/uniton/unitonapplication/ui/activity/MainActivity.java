package com.team3.uniton.unitonapplication.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team3.uniton.unitonapplication.App;
import com.team3.uniton.unitonapplication.model.MainModel;
import com.team3.uniton.unitonapplication.model.MainResignationItem;
import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.model.Resignation;
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

    TextView mTitle;
    TextView mImageText;
    TextView mResignationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mTitle = findViewById(R.id.title);
        mListLayout = findViewById(R.id.list_layout);
        mWriteResignationButton = findViewById(R.id.write_button);
        mImageText = findViewById(R.id.image_text);
        mResignationCount = findViewById(R.id.tv_my_resignation);

        mWriteResignationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteResignationActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mListLayout.removeAllViews();
        requestDatas();

    }

    void putDatas(MainModel data) {
        StringBuilder builder = new StringBuilder();
        builder.append(data.getCompany_name());
        builder.append("\n");
        builder.append(data.getAttendance_day());
        builder.append("일째 근무 ");

        mTitle.setText(builder.toString());

        StringBuilder builder1 = new StringBuilder();
        builder1.append(data.getCurrent_reason_count());
        builder1.append("/3완성");

        mImageText.setText(builder1.toString());

        mResignationCount.setText(String.format("내가 품은 사직서: %d개", data.getResignation().size()));

        for (final Resignation item : data.getResignation()) {

            CustomMainItemView view = new CustomMainItemView(this);
            view.setData(item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ResignationActivity.class);
                    intent.putExtra("ID", item.getResignation_id());
                    startActivity(intent);
                }
            });

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
                putDatas(response.body());
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {

            }
        });
    }


}
