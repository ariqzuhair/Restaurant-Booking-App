package com.example.fasttable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
=======
import android.widget.EditText;
>>>>>>> 2bbed8c7e76e1db976b6ea33ad20652c57be458a

public class BookNow extends AppCompatActivity {

    private EditText Name;
<<<<<<< HEAD
    private TextView Pax;
    private Button DatePicker;
    private TextView TimePicker;
    private DatePickerDialog datePickerDialog;
    private Button Book;
    private Button Increment, Decrement;

    int count = 1;
    int hour, minute;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
=======
    private EditText NumberofPax;
    private EditText Date;
    private EditText Time;
    private Button ReservationForm;
>>>>>>> 2bbed8c7e76e1db976b6ea33ad20652c57be458a


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        getSupportActionBar().setTitle("Book Your Table Now");

<<<<<<< HEAD
        Name       = (EditText)     findViewById(R.id.tv_name);
        DatePicker = (Button)       findViewById(R.id.btn_datePicker);
        Pax        = (TextView)     findViewById(R.id.tv_pax);
        TimePicker = (TextView)     findViewById(R.id.tv_timePicker);
        Book       = (Button)       findViewById(R.id.btn_bookDetail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User Info").child(firebaseAuth.getUid());

        initDatePicker();
        DatePicker.setText(getTodaysDate());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                Name.setText(userProfile.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BookNow.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        TimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BookNow.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                hour = hourOfDay;
                                minute = minute;
                                //Store hour and minute in string
                                String time = hour + ":" + minute;
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    //Set selected time on button
                                    TimePicker.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous selected time
                timePickerDialog.updateTime(hour,minute);
                //Show dialog
                timePickerDialog.show();
            }
        });

        Book.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userBookingDetail();
                startActivity(new Intent(BookNow.this, Invoice.class));
            }
        });
}

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                DatePicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void increment(View v)
    {
        count++;
        Pax.setText("" + count);
    }

    public void decrement(View v)
    {
        if(count <= 0 ) count = 0;
        else count--;

        Pax.setText("" + count);
    }

    private void userBookingDetail()
    {
        String name = Name.getText().toString();
        String date = DatePicker.getText().toString();
        String time = TimePicker.getText().toString();
        String pax  = Pax.getText().toString();

        int numPax = Integer.parseInt(pax);
        double totalPrice = numPax*9;
        String strPax = String.valueOf(totalPrice);




        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("User Booking").child(firebaseAuth.getUid());

        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("Name" , name);
        userMap.put("Date" , date);
        userMap.put("Time" , time);
        userMap.put("Pax" , pax);
        userMap.put("Total Price" , strPax);
=======
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



>>>>>>> 2bbed8c7e76e1db976b6ea33ad20652c57be458a

        myRef.setValue(userMap);
    }
}