package com.team3.uniton.unitonapplication.api;

import com.team3.uniton.unitonapplication.model.Status;
import com.team3.uniton.unitonapplication.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApi {

  @GET("/auth/login/fail")
  Call<Status> getLogin();

  @POST("/auth/register")
  Call<Status> setLogin(
    @Body
      Token token
  );

}
