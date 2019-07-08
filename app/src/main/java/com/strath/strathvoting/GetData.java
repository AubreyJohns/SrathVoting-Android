package com.strath.strathvoting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetData {
    /*
    @Headers({"Authorization: Bearer UChHok97fqsE7ZqtkiNpXqYMVmjsAdDb3PzaukSSzFU1kS8U5pheeXyYwJ9t"
    })*/
    @GET("candidates")
    Call<List<RetroUsers>> getAllUsers();
    @GET("candidates/{id}")
    Call<RetroUsers> getAllUsers(@Path("id")int id);
}
