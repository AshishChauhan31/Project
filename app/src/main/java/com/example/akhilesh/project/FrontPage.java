package com.example.akhilesh.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FrontPage extends AppCompatActivity {

    TextView textView2;
    Button button2;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_front_page);
        button2 = findViewById (R.id.button2);
        button4 = findViewById (R.id.button4);

        button4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext (),MainActivity.class);
                startActivity (intent);
            }
        });
        button2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent Register = new Intent (getApplicationContext (),Registration.class);
                startActivity (Register);
            }
        });
    }
}
