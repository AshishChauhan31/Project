package com.example.akhilesh.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {
    EditText editText4;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forgot_password);
        button4 = findViewById (R.id.button4);
        editText4 = findViewById (R.id.editText4);
    }
}
