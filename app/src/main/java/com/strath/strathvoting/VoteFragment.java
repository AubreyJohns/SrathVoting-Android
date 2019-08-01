package com.strath.strathvoting;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.io.IOException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteFragment extends Fragment {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    VoteAdapter recyclerViewAdapter;
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
                if(response.isSuccessful()){
                    positionsList=response.body();
                    recyclerViewAdapter = new VoteAdapter(myRecyclerView.getContext(),positionsList);
                    myRecyclerView.setAdapter(recyclerViewAdapter);
                }else if(response.code()==401){
                    Toast.makeText(getActivity(),"Your session has expired",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<VoteList>> call, Throwable throwable) {
                if(throwable instanceof IOException){
                    Toast.makeText(getActivity(),"A connection error occurred",Toast.LENGTH_LONG).show();
                }else{
                    Log.e(TAG, "onFailure: "+throwable.getMessage());
                    Toast.makeText(getActivity(), "Unable to load positions"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return myRecyclerView;

    }

}