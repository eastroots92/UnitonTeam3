package com.team3.uniton.unitonapplication.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.team3.uniton.unitonapplication.App;
import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.model.Status;
import com.team3.uniton.unitonapplication.model.Token;
import com.team3.uniton.unitonapplication.util.ApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ISessionCallback  {

  private static String TAG = LoginActivity.class.getSimpleName();

  private static String USER_MODEL = "USER_MODEL";
  private static String USER_ID = "USER_MODEL";
  private static String USER_NAME = "USER_NAME";
  private static String USER_COMPANY = "USER_COMPANY";


  private Button m_btn_login;
  private SharedPreferences sp_userData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    init();
  }

  private void init() {
    initLogin();
  }

  private void initLogin() {
    m_btn_login = findViewById(R.id.btn_login);

    m_btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        kakaoLogin();
      }
    });
  }

  private void kakaoLogin() {
    Log.e("버튼 클릭", "클릭클릭");
    Session session = Session.getCurrentSession();
    session.addCallback(this);
    session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
  }


    // 로그인에 성공한 상태
    @Override
    public void onSessionOpened() {
      Log.e("requestMe", "시작 전!");
      requestMe();
    }

    // 로그인에 실패한 상태
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
      exception.printStackTrace();
      Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
    }



    // 사용자 정보 요청
    public void requestMe() {
      Log.e("requestMe", "헤헤");
      // 사용자정보 요청 결과에 대한 Callback
      UserManagement.requestMe(new MeResponseCallback() {
        // 세션 오픈 실패. 세션이 삭제된 경우,
        @Override
        public void onSessionClosed(ErrorResult errorResult) {
          Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage());
        }

        // 회원이 아닌 경우,
        @Override
        public void onNotSignedUp() {
          Log.e("SessionCallback :: ", "onNotSignedUp");
        }

        // 사용자정보 요청에 성공한 경우,
        @Override
        public void onSuccess(UserProfile userProfile) {
          Log.e("SessionCallback :: ", "onSuccess");
          String nickname = userProfile.getNickname();
          long id = userProfile.getId();

          Log.e("Profile : ", nickname + "");
          Log.e("Profile : ", id + "");

          boolean isLogined = checkUserData();

          if (isLogined) {
            boolean isCreateForm = checkCreateForm();

            if (isCreateForm) {
              startMain();
            } else {
              startCreateForm();
            }
          } else {
//            setUserData(id, nickname);
            requestUserData(String.valueOf(id), nickname);
          }
        }

        private boolean checkCreateForm() {
          String currentCompany = sp_userData.getString(USER_COMPANY, "");

          if ("".equals(currentCompany)) {
            return false;
          }else {
            return true;
          }
        }

        private void setUserData(long id, String nickname) {
          SharedPreferences.Editor editor = sp_userData.edit();
          editor.putString(USER_NAME, nickname);
          editor.putString(USER_ID, String.valueOf(id));
          editor.commit();
        }

        private Boolean checkUserData(){
          sp_userData = getSharedPreferences(USER_MODEL, MODE_PRIVATE);
          String currentName = sp_userData.getString(USER_NAME, "");

          if ("".equals(currentName)) {
            return false;
          }else {
            return true;
          }
        }

        // 사용자 정보 요청 실패
        @Override
        public void onFailure(ErrorResult errorResult) {
          Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage());
        }
      });


  }

  private void requestUserData(String id, String nickname) {
    Token token = new Token(id, nickname);

    App.serverApi.setLogin(token)
      .enqueue(new Callback<Status>() {
        @Override
        public void onResponse(Call<Status> call, Response<Status> response) {
          Log.e(TAG, "onResponse");
          String result = response.body().getStatus();
          if ("200".equals(result)) {

            startCreateForm();
          }
        }

        @Override
        public void onFailure(Call<Status> call, Throwable t) {
          Log.e("REQUESTLOGIN_ERROR",t.toString());
        }
      });
  }

  private void startMain() {
    startActivity(new Intent( this, MainActivity.class ));
    finish();
  }

  private void startCreateForm() {
    startActivity(new Intent( this, CreateFormActivity.class ));
    finish();
  }

}
