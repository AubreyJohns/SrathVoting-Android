package com.strath.strathvoting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetResults {
    @GET("positions")
    Call<List<VoteList>> getAllPositions();
}

