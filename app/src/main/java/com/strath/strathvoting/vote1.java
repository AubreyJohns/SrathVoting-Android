package com.strath.strathvoting;

import com.strath.strathvoting.GetCandidates;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.strath.strathvoting.Models.VoteChild;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class vote1 {
    static vote1 _vote1;
    private static List<VoteChild> candidatesList;
    private GetCandidates service;
    private static final String TAG = "Vote1Fragment";
    Context context;

    public vote1(final Context context, final List<VoteChild> candidatesList){
        this.context=context;
        this.candidatesList=candidatesList;
        service = RetrofitClient.buildService(GetCandidates.class);
        Call<List<VoteChild>> call = service.getAllCandidates();
        call.enqueue(new Callback<List<VoteChild>>() {

            @Override
            public void onResponse(Call<List<VoteChild>> call, Response<List<VoteChild>> response) {
                final List<VoteChild> candidatesList = response.body();
                vote1 candidate=new vote1(context,candidatesList);
            }

            @Override
            public void onFailure(Call<List<VoteChild>> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());
                //Toast.makeText( VoteFragment,"Unable to load candidates" + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static vote1 get(Context context)
    {
        if(_vote1 == null)
            _vote1 = new vote1(context,candidatesList );
        return _vote1;
    }

    public  static List<VoteChild> getAll() {
        return candidatesList;
    }
}
