package com.example.fasttable;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, age, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //upload data to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                sendEmailVerification();
                            }else
                                {
                                    Toast.makeText(Register.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }

    private void setupUIViews()
    {
        userEmail = (EditText)findViewById(R.id.reg_userEmail);
        userName = (EditText)findViewById(R.id.reg_userName);
        userPassword = (EditText)findViewById(R.id.reg_userPassword);
        regButton = (Button)findViewById(R.id.btn_regSignUp);
        userLogin = (TextView)findViewById(R.id.tv_userLogin);
        userAge = (EditText)findViewById(R.id.et_age);
        userProfilePic = (ImageView)findViewById(R.id.iv_profile);
    }

    private Boolean validate()
    {
        Boolean result = false;
        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }else
            {
                result = true;
            }

        return result;
    }

    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(Register.this,"Successfully registered, Verification email has been sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Register.this,Login.class));
                    }else
                        {
                            Toast.makeText(Register.this, "Verification mail has not been sent!", Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("User Info").child(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(age, email, name);
        myRef.setValue(userProfile);
    }
}