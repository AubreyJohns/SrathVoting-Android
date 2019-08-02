package com.strath.strathvoting;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatesDetailActivity extends AppCompatActivity {
    RetroUsers candidateDetails;
    private GetData service;
    private String url="https://red-mountie-10018.herokuapp.com/uploads/";
    public static final String ARG_ITEM_ID = "position";
    private static final String TAG = "CandidatesDetails";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates_detail);
        // Set Collapsing Toolbar layout to the screen
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        final int id = getIntent().getIntExtra(ARG_ITEM_ID, 0);
        final TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        final TextView placeLocation =  (TextView) findViewById(R.id.place_location);
        final ImageView placePicutre = (ImageView) findViewById(R.id.image);

        service = RetrofitClient.buildService(GetData.class);
        Call<RetroUsers> call = service.getAllUsers(id);
        call.enqueue(new Callback<RetroUsers>() {

            @Override
            public void onResponse(Call<RetroUsers> call, Response<RetroUsers> response) {
                if(response.isSuccessful()){
                candidateDetails=response.body();
                collapsingToolbar.setTitle(candidateDetails.getUser());
                placeDetail.setText(candidateDetails.getPosition());
                placeLocation.setText(candidateDetails.getManifesto());
                Picasso.get().load(url+candidateDetails.getImage()).placeholder(R.drawable.a).into(placePicutre);
                }else if(response.code()==401){
                    Toast.makeText(CandidatesDetailActivity.this,"Your session has expired",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CandidatesDetailActivity.this,"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RetroUsers> call, Throwable throwable) {
                if(throwable instanceof IOException){
                    Toast.makeText(CandidatesDetailActivity.this,"A connection error occurred",Toast.LENGTH_LONG).show();
                }else {
                    Log.e(TAG, "onFailure: " + throwable.getMessage());
                    Toast.makeText(CandidatesDetailActivity.this, "Unable to load candidates details" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

