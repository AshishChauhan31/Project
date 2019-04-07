package com.example.akhilesh.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView login_heading;
    Button login_button;
    TextView forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        login_button = findViewById (R.id.loginButton);
        login_heading = findViewById (R.id.Login_heading);
        editText = findViewById (R.id.editText);
        editText2 = findViewById (R.id.editText2);
        forget_password = findViewById (R.id.Forget_password);

        login_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (MainActivity.this, "welcome you are logeed in successfully!!", Toast.LENGTH_SHORT).show ();
            }
        });

        forget_password.setOnLongClickListener (new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent (getApplicationContext (),ForgotPassword.class);
                startActivity (intent);
                return false;
            }
        });

    }
}
