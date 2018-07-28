package com.team3.uniton.unitonapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.kakao.auth.KakaoSDK;
import com.team3.uniton.unitonapplication.adapter.KakaoSDKAdapter;
import com.team3.uniton.unitonapplication.api.ServerApi;
import com.team3.uniton.unitonapplication.util.ApiUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getPackageInfo;


public class App extends Application {
    private static App instance;
    public static ServerApi serverApi;

    public static App getAppContext() {
        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");
        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.e("App","HASH : " + getKeyHash(this));
        KakaoSDK.init(new KakaoSDKAdapter());

        serverApi = ApiUtil.getServerApi();
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("APPLICATION", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}

