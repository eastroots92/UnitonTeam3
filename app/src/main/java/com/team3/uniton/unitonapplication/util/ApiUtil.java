package com.team3.uniton.unitonapplication.util;

import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.module.RetrofitClient;

public class ApiUtil {
  public static final String BASE_URL = "http://172.20.10.2:3000";

  public static ServerApi getServerApi() {
    return RetrofitClient.getClient(BASE_URL)
      .create(ServerApi.class);
  }
}
