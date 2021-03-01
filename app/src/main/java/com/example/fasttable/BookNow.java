package com.example.fasttable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BookNow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        getSupportActionBar().setTitle("Book Your Table Now");


    }
}