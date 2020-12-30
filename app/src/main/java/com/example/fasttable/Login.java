package com.example.fasttable;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button Register;
    private TextView Attempts;
    private FirebaseAuth firebaseAuth;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email            = (EditText)findViewById(R.id.et_userEmail);
        Password         = (EditText)findViewById(R.id.et_userPassword);
        Attempts         = (TextView)findViewById(R.id.tv_attempts);
        Login            = (Button)findViewById(R.id.btn_login);
        Register         = (Button)findViewById(R.id.btn_signup);

        Attempts.setText("No of attempts remaining: 3");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null)
        {
            finish();
            startActivity(new Intent(Login.this,Dashboard.class));
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });
    }

    private void validate(String userEmail, String userPassword)
    {
        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    checkEmailVerification();
                }else
                    {
                        Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_SHORT).show();
                        counter--;
                        Attempts.setText("No of attempts remaining: " + counter);
                        if(counter == 0)
                        {
                            Login.setEnabled(false);
                        }
                    }
            }
        });
    }

    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag)
        {
            finish();
            startActivity(new Intent(Login.this,Dashboard.class));
        }else
            {
                Toast.makeText(this,"Please verify your email", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
            }
    }
}