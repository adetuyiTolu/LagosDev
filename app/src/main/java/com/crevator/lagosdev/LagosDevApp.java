package com.crevator.lagosdev;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import com.crevator.lagosdev.rest.ApiService;
import com.crevator.lagosdev.rest.NetworkInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.crevator.lagosdev.util.Data.BASE_URL;

/**
 * Created by Adetuyi Tolu on 3/04/2017.
 */

public class LagosDevApp extends Application {

    private static Retrofit retrofit = null;
    private OkHttpClient client;
    private static LagosDevApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();
    }

    public static LagosDevApp getInstance() {
        if (instance == null) {
            instance = new LagosDevApp();
        }
        return instance;
    }


    /**
     * Setup retrofit and cache
     */
    private void initRetrofit() {
        // Cache cache = new Cache(getCacheDir(), 1024);
        client = new OkHttpClient.Builder()
                .addInterceptor(new NetworkInterceptor())
                // .cache(cache)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).client(client)
                .build();
    }

    /**
     * @return the Retrofit Rest Api Service
     */
    public static ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }


    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenet = connectivityManager.getActiveNetworkInfo();
        return activenet != null;
    }
}
