package com.example.fasttable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private CardView Restaurant;
    private CardView Logout;
    private CardView AboutUs;
    private CardView Profile;
    private CardView Feedback;
    private CardView Receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Fast Table");

        firebaseAuth = FirebaseAuth.getInstance();

        Logout     = (CardView)findViewById(R.id.cv_logout);
        Restaurant = (CardView)findViewById(R.id.cv_bookNow);
        AboutUs    = (CardView)findViewById(R.id.cv_aboutUs);
        Profile    = (CardView)findViewById(R.id.cv_profile);
        Feedback   = (CardView)findViewById(R.id.feedback);
        Receipt    = (CardView)findViewById(R.id.receipt);



        Restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, BookNow.class));
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Dashboard.this,Login.class));
            }
        });

        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AboutUs.class));
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, ProfileActivity.class));
            }
        });

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, FileFeedback.class));
            }
        });

        Receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Receipt.class));
            }
        });

    }
}