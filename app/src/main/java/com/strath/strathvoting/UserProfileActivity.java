package com.strath.strathvoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {
    Button logout ;
    TextView userNameShow ;
    CardView card_profile;
    CardView card_fees;
    CardView card_coursework;
    CardView card_attendance;
    CardView card_timetable;
    CardView card_library;
    CardView card_info;
    CardView card_voting;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        logout = (Button)findViewById(R.id.logout);
        userNameShow = (TextView)findViewById(R.id.user_email);
        card_profile = (CardView) findViewById(R.id.profile_card_view);
        card_fees = (CardView) findViewById(R.id.fees_card_view);
        card_coursework = (CardView) findViewById(R.id.coursework_card_view);
        card_attendance = (CardView) findViewById(R.id.attendance_card_view);
        card_timetable = (CardView) findViewById(R.id.timetable_card_view);
        card_library = (CardView) findViewById(R.id.library_card_view);
        card_info = (CardView) findViewById(R.id.info_card_view);
        card_voting = (CardView) findViewById(R.id.voting_card_view);
        firebaseAuth = FirebaseAuth.getInstance();

        // Start: Check whether there is a user previously logged in or not.
        if(firebaseAuth.getCurrentUser() == null){
            // Finishing current UserProfileActivity.
            finish();
            // If user is not already logged in then Redirect to LoginActivity
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        userNameShow.setText("Welcome " + firebaseUser.getEmail());

        card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_coursework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        card_voting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, VotingActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Destroying login season.
                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
