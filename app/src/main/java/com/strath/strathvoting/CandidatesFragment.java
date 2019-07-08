package com.strath.strathvoting;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Provides UI for the view with List.
 */
public class CandidatesFragment extends Fragment {
    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<RetroUsers> usersList;
    private GetData service;
    private static final String TAG = "CandidatesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myRecyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(layoutManager);

        service = RetrofitClient.buildService(GetData.class);
        Call<List<RetroUsers>> call = service.getAllUsers();
        call.enqueue(new Callback<List<RetroUsers>>() {

            @Override
            public void onResponse(Call<List<RetroUsers>> call, Response<List<RetroUsers>> response) {
                usersList=response.body();
                myAdapter = new MyAdapter(myRecyclerView.getContext(),usersList);
                myRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<RetroUsers>> call, Throwable throwable) {
                Log.e(TAG, "onFailure: "+throwable.getMessage());
                Toast.makeText(getActivity(), "Unable to load candidates"+throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return myRecyclerView;
    }

}
