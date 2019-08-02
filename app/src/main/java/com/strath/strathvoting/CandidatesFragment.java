package com.strath.strathvoting;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CandidatesFragment extends Fragment {
    private CandidatesAdapter candidatesAdapter;
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
                if(response.isSuccessful()){
                usersList=response.body();
                candidatesAdapter = new CandidatesAdapter(myRecyclerView.getContext(),usersList);
                myRecyclerView.setAdapter(candidatesAdapter);
                }else if(response.code()==401){
                    Toast.makeText(getActivity(),"Your session has expired",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<RetroUsers>> call, Throwable throwable) {
                if(throwable instanceof IOException){
                    Toast.makeText(getActivity(),"A connection error occurred",Toast.LENGTH_LONG).show();
                }else {
                    Log.e(TAG, "onFailure: " + throwable.getMessage());
                    Toast.makeText(getActivity(), "Unable to load candidates" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        return myRecyclerView;
    }
}
