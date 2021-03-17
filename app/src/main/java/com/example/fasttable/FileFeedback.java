package com.example.fasttable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FileFeedback extends AppCompatActivity {

    private EditText Feedback;
    private TextView Name;
    private Button SendFeedback;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_feedback);
        getSupportActionBar().setTitle("Fast Table");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupUIView();
        setUserName();

        SendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSent();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this,Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupUIView(){
        Name         = (TextView) findViewById(R.id.username);
        Feedback     = (EditText) findViewById(R.id.feedback);
        SendFeedback = (Button)findViewById(R.id.btn_feedback);
    }

    private void setUserName(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User Info").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                Name.setText(userProfile.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FileFeedback.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void feedbackSent() {

        String name = Name.getText().toString();
        String feedback = Feedback.getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("User Feedback").child(firebaseAuth.getUid());

        HashMap<String, String> feedbackMap = new HashMap<>();
        feedbackMap.put("Name" , name);
        feedbackMap.put("Feedback" , feedback);
        myRef.setValue(feedbackMap);

        Toast.makeText(FileFeedback.this,"Feedback has been sent", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FileFeedback.this,Dashboard.class
        ));
    }
}