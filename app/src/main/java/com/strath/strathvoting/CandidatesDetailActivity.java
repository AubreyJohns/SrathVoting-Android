package com.strath.strathvoting;

import com.squareup.picasso.Picasso;
import com.strath.strathvoting.RetroUsers;
import com.strath.strathvoting.GetData;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
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
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));
        final int id = getIntent().getIntExtra(ARG_ITEM_ID, 0);

        final TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        final TextView placeLocation =  (TextView) findViewById(R.id.place_location);
        final ImageView placePicutre = (ImageView) findViewById(R.id.image);

        service = RetrofitClient.buildService(GetData.class);
        Call<RetroUsers> call = service.getAllUsers(id);
        call.enqueue(new Callback<RetroUsers>() {

            @Override
            public void onResponse(Call<RetroUsers> call, Response<RetroUsers> response) {
                candidateDetails=response.body();
                collapsingToolbar.setTitle(candidateDetails.getUser());
                placeDetail.setText(candidateDetails.getPosition());
                placeLocation.setText(candidateDetails.getManifesto());
                Picasso.get().load(url+candidateDetails.getImage()).placeholder(R.drawable.a).into(placePicutre);
                //placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

            }

            @Override
            public void onFailure(Call<RetroUsers> call, Throwable throwable) {
                Log.e(TAG, "onFailure: "+throwable.getMessage());
                Toast.makeText(CandidatesDetailActivity.this, "Unable to load candidates details"+throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}

