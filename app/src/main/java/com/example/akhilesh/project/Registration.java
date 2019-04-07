package com.example.akhilesh.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Registration extends AppCompatActivity {

    Button REGISTER;
    RadioButton Male;
    RadioButton Female;
    TextView Register;
    EditText Email;
    EditText Phone;
    EditText Name;
    EditText Last_Name;
    EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_registration);
        Email = findViewById (R.id.Email);
        Phone = findViewById (R.id.Phone);
        Name = findViewById (R.id.Name);
        Last_Name = findViewById (R.id.Last_Name);
        Password = findViewById (R.id.Password);

    }
}
