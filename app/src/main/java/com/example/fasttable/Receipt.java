package com.example.fasttable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Receipt extends AppCompatActivity {

    private TextView BookingName, BookingDate, BookingTime, NumberOfPax, TotalPrice;
    private Button Done;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        getSupportActionBar().setTitle("Your Receipt");

        receiptSetup();
        setDetail();

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Receipt.this, Dashboard.class));
            }
        });

    }

    private void receiptSetup(){
        BookingName = (TextView)findViewById(R.id.tv_bookingName);
        BookingDate = (TextView)findViewById(R.id.tv_bookingDate);
        BookingTime = (TextView)findViewById(R.id.tv_bookingTime);
        NumberOfPax = (TextView)findViewById(R.id.tv_numberOfPax);
        TotalPrice  = (TextView)findViewById(R.id.tv_totalPrice);
        Done        = (Button)  findViewById(R.id.btn_done);
    }

    private void setDetail(){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User Booking").child(firebaseAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue().toString();
                BookingName.setText(name);
                String date = dataSnapshot.child("Date").getValue().toString();
                BookingDate.setText(date);
                String time = dataSnapshot.child("Time").getValue().toString();
                BookingTime.setText(time);
                String pax = dataSnapshot.child("Pax").getValue().toString();
                NumberOfPax.setText(pax);
                String totalPrice = dataSnapshot.child("Total Price").getValue().toString();
                TotalPrice.setText(totalPrice);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Receipt.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}