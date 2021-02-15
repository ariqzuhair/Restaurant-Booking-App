package com.example.fasttable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

public class restaurant_list extends AppCompatActivity {

    ImageView Restaurant1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);
        getSupportActionBar().setTitle("List of Restaurants");

        Restaurant1 = (ImageView)findViewById(R.id.iv_restaurant_1);

        Restaurant1.setImageResource(R.drawable.fast_table_logo);
    }
}