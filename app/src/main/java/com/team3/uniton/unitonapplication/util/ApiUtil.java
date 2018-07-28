package com.team3.uniton.unitonapplication.util;

import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.module.RetrofitClient;

public class ApiUtil {
  public static final String SERVER_URL = "http://httpbin.org/";

  public static ServerApi getServerApi(String url) {
    return RetrofitClient.getClient(url)
      .create(ServerApi.class);
  }
}
