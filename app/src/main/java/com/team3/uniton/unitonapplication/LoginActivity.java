package com.team3.uniton.unitonapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.model.Movie;
import com.team3.uniton.unitonapplication.model.Token;
import com.team3.uniton.unitonapplication.util.ApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button m_btn_login;
    private ServerApi mServerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login);
        
        init();
    }

    private void init() {
        initRetrofit();
        initLogin();
    }

    private void initRetrofit() {
        mServerApi = ApiUtil.getServerApi();
    }

    private void initLogin() {
        m_btn_login = findViewById(R.id.btn_login);

        m_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestLogin();
            }
        });
    }

    private void requestLogin() {
        mServerApi.getMovie()
          .enqueue(new Callback<Movie>() {
              @Override
              public void onResponse(Call<Movie> call, Response<Movie> response) {
                  Log.e("login",response.toString());
              }

              @Override
              public void onFailure(Call<Movie> call, Throwable t) {
                  Log.e("login", "error");
              }
          });
    }
}
