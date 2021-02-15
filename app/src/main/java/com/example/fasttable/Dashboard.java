package com.example.fasttable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private Button userBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");

        firebaseAuth = FirebaseAuth.getInstance();
        userBooking = (Button)findViewById(R.id.btn_restaurantList);

        userBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,restaurant_list.class));
            }
        });

    }

    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Dashboard.this,Login.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.logoutMenu:
            {
                Logout();
                break;
            }
            case R.id.profileMenu:
            {
                startActivity(new Intent(Dashboard.this, ProfileActivity.class));
                break;
            }
            case R.id.AboutUsMenu:
            {
                startActivity(new Intent(Dashboard.this,AboutUs.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }


}