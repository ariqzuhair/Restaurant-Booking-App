package com.example.fasttable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FileFeedback extends AppCompatActivity {

    private Firebase myRef;
    private EditText username,feedback;
    private Button SendFeedback;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_feedback);
        username     = (EditText) findViewById(R.id.username);
        feedback     = (EditText) findViewById(R.id.feedback);
        SendFeedback = (Button)findViewById(R.id.btn_feedback);
        getSupportActionBar().hide();

        SendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSent();
            }
        });
    }

    public void feedbackSent() {
        String usernameInput = username.getText().toString();
        String feedbackInput = feedback.getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("User Feedback").child(usernameInput);
        myRef.setValue(feedbackInput);
        Toast.makeText(FileFeedback.this,"Feedback has been sent", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FileFeedback.this,Dashboard.class
        ));
    }
}