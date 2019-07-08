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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class ResultsDetailActivity extends AppCompatActivity {


    List<RetroUsers> candidateDetails;
    private GetCandidate service;

    private String url="https://red-mountie-10018.herokuapp.com/uploads/";
    public static final String ARG_ITEM_POSITION ="position";
    private static final String TAG = "ResultsDetails";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_detail);

        // Set Collapsing Toolbar layout to the screen
        final CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));
        final String position = getIntent().getStringExtra(ARG_ITEM_POSITION);
        if(position==null||position.length()==0){
            Toast.makeText(ResultsDetailActivity.this, "position is empty", Toast.LENGTH_LONG).show();
        }
        Log.e(TAG, "Candidates Position: "+position);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_example);


        final ImageView placePicutre = (ImageView) findViewById(R.id.image);

        service = RetrofitClient.buildService(GetCandidate.class);
        Call<List<RetroUsers>> call = service.getAllCandidates(position);
        call.enqueue(new Callback<List<RetroUsers>>() {

            @Override
            public void onResponse(Call<List<RetroUsers>> call, Response<List<RetroUsers>> response) {
                candidateDetails=response.body();
                collapsingToolbar.setTitle(position);
                List<String> priceList = new ArrayList<String>();
                for(int i=0;i<candidateDetails.size();i++){
                    final TextView placeDetail = (TextView) findViewById(R.id.place_detail);
                    Log.e(TAG, "Candidates List: "+candidateDetails.get(i).getUser());
                    priceList.add(candidateDetails.get(i).getUser()+"             "+candidateDetails.get(i).getVotes());
                    Picasso.get().load(url+candidateDetails.get(i).getImage()).placeholder(R.drawable.a).into(placePicutre);
                }

                for(String price : priceList){
                    final TextView textView1 = new TextView(ResultsDetailActivity.this);
                    textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView1.setText(price);
                    linearLayout.addView(textView1);
                }

                //placeDetail.setText(candidateDetails.getPosition());
                //placeLocation.setText(candidateDetails.getManifesto());
                //Picasso.get().load(url+candidateDetails.getImage()).placeholder(R.drawable.a).into(placePicutre);
                //placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

            }

            @Override
            public void onFailure(Call<List<RetroUsers>> call, Throwable throwable) {
                Log.e(TAG, "onFailure: "+throwable.getMessage());
                Toast.makeText(ResultsDetailActivity.this, "Unable to load candidates details"+throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}