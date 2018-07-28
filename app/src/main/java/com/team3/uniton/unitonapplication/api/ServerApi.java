package com.team3.uniton.unitonapplication.api;

import com.team3.uniton.unitonapplication.model.Info;
import com.team3.uniton.unitonapplication.model.Status;
import com.team3.uniton.unitonapplication.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

  @GET("/auth/login/fail")
  Call<Status> getLogin();

  @POST("/auth/register")
  Call<Status> setLogin(
    @Body
      Token token
  );

  @POST("/user/{id}/info")
  Call<Status> setInfo(
      @Path("id")
      int userId,
      @Body
      Info info
  );
}
