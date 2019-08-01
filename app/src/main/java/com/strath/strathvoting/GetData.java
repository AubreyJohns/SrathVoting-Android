package com.strath.strathvoting;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetData {
    @GET("candidates")
    Call<List<RetroUsers>> getAllUsers();
    @GET("candidates/{id}")
    Call<RetroUsers> getAllUsers(@Path("id")int id);
}
