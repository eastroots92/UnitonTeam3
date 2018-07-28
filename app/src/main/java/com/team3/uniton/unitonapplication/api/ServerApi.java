package com.team3.uniton.unitonapplication.api;

import com.team3.uniton.unitonapplication.model.Token;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {

  @GET("/auth/login/kakao")
  Call<Token> getLogin();


}
