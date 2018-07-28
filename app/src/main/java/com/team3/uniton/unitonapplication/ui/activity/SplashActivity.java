package com.team3.uniton.unitonapplication.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team3.uniton.unitonapplication.R;

public class SplashActivity extends AppCompatActivity {

  private Handler handler = new Handler();
  private static final int ACTIVITY_START_DELAY_MILLIS = 1000 * 3;
  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    init();
  }

  private void init() {
    startMainActivityWithDelay();
  }

  private void startMainActivityWithDelay() {
    Runnable startActivityRunnable = new Runnable() {
      @Override
      public void run() {
        Boolean isCurrent = checkingUserData();
        changeActivityManager(isCurrent);
      }
    };

    handler.postDelayed(
      startActivityRunnable,
      ACTIVITY_START_DELAY_MILLIS
    );
  }

  private Boolean checkingUserData() {
    sharedPreferences = getSharedPreferences("USER_MODEL", MODE_PRIVATE);
    String userID = sharedPreferences.getString("USER_ID","");

    if ("".equals(userID)){
      return true;
    }else{
      return false;
    }
  }

  private void changeActivityManager(boolean isCurrent) {
    if(isCurrent) {
      startIntroActivity();

    }else {

      String currentCompany = sharedPreferences.getString("USER_COMPANY", "");

      if ("".equals(currentCompany)) {
        startCreateFormActivity();
      } else {
        startMainActivity();
      }
    }
  }

  private void startCreateFormActivity() {
    startActivity(new Intent(this, CreateFormActivity.class));
    finish();
  }

  private void startIntroActivity() {
    startActivity(new Intent(this, TutorialActivity.class));
    finish();
  }

  private void startMainActivity() {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
}
