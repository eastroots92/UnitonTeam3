package com.team3.uniton.unitonapplication.api;

import com.team3.uniton.unitonapplication.model.Movie;
import com.team3.uniton.unitonapplication.model.Token;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {

  @GET("/auth/login/fail")
  Call<Token> getLogin();

  @GET("/get")
  Call<Movie> getMovie();


}
