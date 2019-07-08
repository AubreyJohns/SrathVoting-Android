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
    // Creating button.
    Button logout ;

    // Creating TextView.
    TextView userNameShow ;

    //Creating card view
    CardView card_profile;
    CardView card_fees;
    CardView card_coursework;
    CardView card_attendance;
    CardView card_timetable;
    CardView card_library;
    CardView card_info;
    CardView card_voting;

    // Creating FirebaseAuth.
    FirebaseAuth firebaseAuth ;

    // Creating FirebaseAuth.
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // Assigning ID's to button , CardView and TextView.
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


        // Adding FirebaseAuth instance to FirebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();

        // On activity start check whether there is user previously logged in or not.
        if(firebaseAuth.getCurrentUser() == null){

            // Finishing current Profile activity.
            finish();

            // If user is not already logged in then Redirect to LoginActivity .
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(UserProfileActivity.this, "Please Log in to continue", Toast.LENGTH_LONG).show();

        }

        // Adding firebaseAuth current user info into firebaseUser object.
        firebaseUser = firebaseAuth.getCurrentUser();

        // Getting logged in user email from firebaseUser.getEmail() method and set into TextView.

        // Getting logged in user name from Signup Activity Name EditText and set into TextView.
        userNameShow.setText("Welcome " + firebaseUser.getEmail());

        //Adding click listener on voting card view
        card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });
        //Adding click listener on voting card view
        card_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });
        //Adding click listener on voting card view
        card_coursework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });
        //Adding click listener on voting card view
        card_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });
        //Adding click listener on voting card view
        card_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });
        //Adding click listener on voting card view
        card_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });
        //Adding click listener on voting card view
        card_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing toast message.
                Toast.makeText(UserProfileActivity.this, "Only voting works", Toast.LENGTH_LONG).show();
            }
        });

        //Adding click listener on voting card view
        card_voting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Main Voting Activity after click on voting card.
                Intent intent = new Intent(UserProfileActivity.this, VotingActivity.class);
                startActivity(intent);
            }
        });

        // Adding click listener on logout button.
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Destroying login season.
                firebaseAuth.signOut();

                // Finishing current User Profile activity.
                finish();

                // Redirect to Login Activity after click on logout button.
                Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);

                // Showing toast message on logout.
                Toast.makeText(UserProfileActivity.this, "Logged Out Successfully.", Toast.LENGTH_LONG).show();

            }
        });
    }
}
