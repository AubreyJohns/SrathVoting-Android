package com.strath.strathvoting;

import com.strath.strathvoting.Models.VoteChild;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetCandidates {
    @GET("candidates")
    Call<List<VoteChild>> getAllCandidates();
    @GET("candidates/{position}")
    Call<VoteChild> getAllCandidates(@Path("position")String position);
}
