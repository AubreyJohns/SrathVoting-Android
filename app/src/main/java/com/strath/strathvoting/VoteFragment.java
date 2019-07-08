package com.strath.strathvoting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.strath.strathvoting.Adapter.VoteAdapter;
import com.strath.strathvoting.Models.VoteChild;
import com.strath.strathvoting.Models.VoteCreator;
import com.strath.strathvoting.Models.VoteParent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteFragment extends Fragment {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    PackageRecyclerViewAdapter recyclerViewAdapter;
    private List<VoteList> positionsList;
    private GetResults service;
    private static final String TAG = "VoteFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        myRecyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(layoutManager);

        service = RetrofitClient.buildService(GetResults.class);
        Call<List<VoteList>> call = service.getAllPositions();
        call.enqueue(new Callback<List<VoteList>>() {

            @Override
            public void onResponse(Call<List<VoteList>> call, Response<List<VoteList>> response) {
                positionsList=response.body();
                recyclerViewAdapter = new PackageRecyclerViewAdapter(myRecyclerView.getContext(),positionsList);
                myRecyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<VoteList>> call, Throwable throwable) {
                Log.e(TAG, "onFailure: "+throwable.getMessage());
                Toast.makeText(getActivity(), "Unable to load positions"+throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return myRecyclerView;

    }

}