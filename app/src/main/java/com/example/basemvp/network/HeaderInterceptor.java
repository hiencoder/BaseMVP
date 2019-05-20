package com.example.basemvp.network;

import android.text.TextUtils;

import com.example.basemvp.common.Constants;
import com.example.basemvp.common.utils.LogUtils;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (!TextUtils.isEmpty(Constants.ACCESS_TOKEN)) {
            LogUtils.log("ACCESS_TOKEN: ",Constants.ACCESS_TOKEN);
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + Constants.ACCESS_TOKEN)
                    .build();
        }
        return chain.proceed(request);
    }
}