package com.crevator.lagosdev.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */


public interface ApiService {


    @GET("search/users")
    Call<DevelopersResponse> getAllDevelopers(@Query("q") String condition);

    @GET("search/users")
    Call<String[]> nextDevelopers(@Path("since") String lastId);
}
