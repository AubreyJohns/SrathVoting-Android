package com.strath.strathvoting;

import com.strath.strathvoting.Models.VoteParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetVote {
    @GET("positions")
    Call<List<VoteParent>> getAllPositions();
}
