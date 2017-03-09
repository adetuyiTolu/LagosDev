package com.crevator.lagosdev.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */

public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request newRequest = original.newBuilder()
                //.addHeader("authorization", "")
                //.addHeader("x-api-version", "1.0")
                .build();
        return chain.proceed(newRequest);
    }
}
