package com.example.fasttable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookNow extends AppCompatActivity {

    private EditText Name;
    private EditText NumberofPax;
    private EditText Date;
    private EditText Time;
    private Button ReservationForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        getSupportActionBar().setTitle("Book Your Table Now");

        Name                = (EditText)findViewById(R.id.pt_name);
        NumberofPax         = (EditText)findViewById(R.id.tv_numpax);
        ReservationForm     = (Button)findViewById(R.id.btn_reserve);
        Date                = (EditText) findViewById(R.id.tv_date);
        Time                = (EditText) findViewById(R.id.tv_time);


        ReservationForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });




    }
}