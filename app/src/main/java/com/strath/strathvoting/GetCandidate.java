package com.strath.strathvoting;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetCandidate {
    @GET("candidates")
    Call<List<RetroUsers>> getAllCandidates();
    @GET("candidatePosition/{position}")
    Call<List<RetroUsers>> getAllCandidates(@Path("position")String position);
    @GET("candidateDetails/{name}")
    Call<List<RetroUsers>> getAllCandidateDetails(@Path("name")String name);

    @Headers({"Accept: application/json",
            "Content-type:application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @PUT("candidates/{id}")
    Call<RetroUsers> updateCandidate(
            @Path("id")int id,
            @Field("name")String name,
            @Field("positon")String positon,
            @Field("manifesto")String manifesto,
            @Field("votes")int votes
    );
}
