package com.example.fasttable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class FileFeedback extends AppCompatActivity {
    private Firebase Ref;
    private EditText username,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_feedback);
        username = (EditText) findViewById(R.id.username);
        feedback = (EditText) findViewById(R.id.feedback);
        Firebase.setAndroidContext(this);
        Ref = new Firebase("https://fast-table-835f7-default-rtdb.firebaseio.com/");
        getSupportActionBar().hide();
    }
    public void feedbacksent(View view) {
        String usernameinput=username.getText().toString();
        String feedbackinput=feedback.getText().toString();
        Firebase Reusername=Ref.child("Username");
        Reusername.setValue(usernameinput);
        Firebase Reffeedback=Ref.child("Feedback");
        Reffeedback.setValue(feedbackinput);

    }
}