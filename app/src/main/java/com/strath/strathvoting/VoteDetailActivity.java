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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.strath.strathvoting.CandidatesDetailActivity.ARG_ITEM_ID;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class VoteDetailActivity extends AppCompatActivity {
    // initialize list of candidates
    List<RetroUsers> candidatesList;
    // initialize list of a candidate's details
    List<RetroUsers> usersDetails;

    private GetCandidate service;
    private GetCandidate detailService;
    private GetCandidate ideaService;

    private RadioButton rb;
    // Start: initialize a candidate's details in variables
    private int id;
    private String name;
    private String position;
    private String manifesto;
    private int votes=1;
    // End: initialize a candidate's details in variables
    private int selectedId;
    private String url="https://red-mountie-10018.herokuapp.com/uploads/";
    public static final String ARG_ITEM_POSITION ="position1";
    private static final String TAG = "VoteDetails";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_detail);
        final String position1 = getIntent().getStringExtra(ARG_ITEM_POSITION);
        Log.e(TAG, "Candidate's Position: "+position1);

        // Set Collapsing Toolbar layout to the screen
        final CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        final RadioGroup priceGroup = (RadioGroup) findViewById(R.id.price_grp);
        final Button vote= (Button)findViewById(R.id.btn_login);
        final ImageView placePicutre = (ImageView) findViewById(R.id.image);
        // Start: setOnCheckedChangeListener
        priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
        // End: setOnCheckedChangeListener

        // Beginning of code to dynamically fill radio group with candidates
        service = RetrofitClient.buildService(GetCandidate.class);
        Call<List<RetroUsers>> call = service.getAllCandidates(position1);
        call.enqueue(new Callback<List<RetroUsers>>() {
            @Override
            public void onResponse(Call<List<RetroUsers>> call, Response<List<RetroUsers>> response) {
                if(response.isSuccessful()){
                candidatesList=response.body();
                collapsingToolbar.setTitle(position1);
                List<String> priceList = new ArrayList<String>();
                for(int i=0;i<candidatesList.size();i++){
                    Log.e(TAG, "Candidates List "+i+ ": "+candidatesList.get(i).getUser());
                    priceList.add(candidatesList.get(i).getUser());
                    Picasso.get().load(url+candidatesList.get(i).getImage()).placeholder(R.drawable.a).into(placePicutre);
                }
                int id = (1)*100;
                for(String price : priceList){
                    rb = new RadioButton(VoteDetailActivity.this);
                    rb.setId(id++);
                    rb.setText(price);
                    priceGroup.addView(rb);
                }
                }else if(response.code()==401){
                    Toast.makeText(VoteDetailActivity.this,"Your session has expired",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(VoteDetailActivity.this,"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<RetroUsers>> call, Throwable throwable) {
                if(throwable instanceof IOException){
                    Toast.makeText(VoteDetailActivity.this,"A connection error occurred",Toast.LENGTH_LONG).show();
                }else {
                    Log.e(TAG, "onFailure: " + throwable.getMessage());
                    Toast.makeText(VoteDetailActivity.this, "Unable to load candidates details" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        // End of code to dynamically fill radio group with candidates

        // set on click listener on the vote button
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                selectedId = priceGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                rb = (RadioButton) findViewById(selectedId);
                // get the text from selected radiobutton
                Toast.makeText(VoteDetailActivity.this, "Voted "+rb.getText()+" as president", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Candidate Selected "+rb.getText().toString());

                // Start: Get details of selected candidate
                detailService = RetrofitClient.buildService(GetCandidate.class);
                Call<List<RetroUsers>> call = detailService.getAllCandidateDetails(rb.getText().toString());
                call.enqueue(new Callback<List<RetroUsers>>() {
                    @Override
                    public void onResponse(Call<List<RetroUsers>> call, Response<List<RetroUsers>> response) {
                        if(response.isSuccessful()){
                        usersDetails=response.body();
                        for(int i=0;i<usersDetails.size();i++){
                            id=usersDetails.get(i).getId();
                            name=usersDetails.get(i).getUser();
                            position=usersDetails.get(i).getPosition();
                            manifesto=usersDetails.get(i).getManifesto();
                        }

                        // Start: Update selected candidate's details to add a vote
                        ideaService = RetrofitClient.buildService(GetCandidate.class);
                        Call<RetroUsers> updateRequest = ideaService.updateCandidate(
                                id,
                                name,
                                position,
                                manifesto,
                                votes
                        );
                        // Start: Log to see selected candidate's details in logcat
                        Log.e(TAG, "id of candidate selected: "+id);
                        Log.e(TAG, "name of candidate selected: "+name);
                        Log.e(TAG, "position of candidate selected: "+position);
                        Log.e(TAG, "manifesto of candidate selected: "+manifesto);
                        Log.e(TAG, "count of votes to cast: "+votes);
                        // End: Log to see a candidate's details in logcat
                        updateRequest.enqueue(new Callback<RetroUsers>() {
                            @Override
                            public void onResponse(Call<RetroUsers> request, Response<RetroUsers> response) {
                                Log.e(TAG, "Response: "+response);
                                Toast.makeText(VoteDetailActivity.this, "Update Successful. " , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<RetroUsers> request, Throwable t) {
                                Toast.makeText(VoteDetailActivity.this, "Failed to update candidate.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        // End: Update selected candidate's details to add a vote
                        }else if(response.code()==401){
                            Toast.makeText(VoteDetailActivity.this,"Your session has expired",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(VoteDetailActivity.this,"Failed to retrieve items",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<RetroUsers>> call, Throwable throwable) {
                        if(throwable instanceof IOException){
                            Toast.makeText(VoteDetailActivity.this,"A connection error occurred",Toast.LENGTH_LONG).show();
                        }else {
                            Log.e(TAG, "onFailure: " + throwable.getMessage());
                            Toast.makeText(VoteDetailActivity.this, "Unable to load candidates" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                // End: Get details of selected candidate


            }
        });
        //set on click listener on the vote button
    }
}