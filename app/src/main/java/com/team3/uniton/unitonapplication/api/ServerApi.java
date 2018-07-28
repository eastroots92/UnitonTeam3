package com.team3.uniton.unitonapplication.api;

import com.team3.uniton.unitonapplication.model.Info;
import com.team3.uniton.unitonapplication.model.MainModel;
import com.team3.uniton.unitonapplication.model.ResignationItem;
import com.team3.uniton.unitonapplication.model.Reason;
import com.team3.uniton.unitonapplication.model.Reasons;
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
      String userId,
    @Body
      Info info
  );

  @GET("/user/{id}/main")
  Call<MainModel> getMain(
          @Path("id")
          String userId
  );

  @GET("/user/{id}/resignation/{resignationId}")
  Call<ResignationItem> getResign(
    @Path("id")
    String userId,
    @Path("resignationId")
    String resignationId
  );

  @GET("/user/{id}/resignation")
  Call<Reasons> getReasons(
          @Path("id")
          String userId
  );

  @POST("/user/{id}/resignation")
  Call<Status> postReasons(
          @Path("id")
          String userId,
          @Body
          Reason reason
  );
}
