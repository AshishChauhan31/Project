package com.example.akhilesh.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Submission extends AppCompatActivity {
    private TextView sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_submission);

        sub = findViewById (R.id.tv_Subjects);

        Intent intent = getIntent ();
        final String S1_firstCheckbox = intent.getStringExtra ("cb1");
        final String S1_secondCheckbox = intent.getStringExtra ("cb2");
        sub.setText ("Sub_choices:\n" + S1_firstCheckbox + "\n" + S1_secondCheckbox);


        final Button send = (Button) this.findViewById (R.id.Submission);
        send.setOnClickListener (new View.OnClickListener () {

            public void onClick(View v) {
                try {
                    GMailSender sender = new GMailSender ("Akhilesh.chauhan@auw.co.in", "Aksh@1234");
                    sender.sendMail ("This is Subject",
                            "This is subject Choice 1 " + S1_firstCheckbox+ "\n" + "This is Subject choice 2 " + S1_secondCheckbox,
                            "user@gmail.com",
                            "rohit.sh17@gmail.com");
                } catch (Exception e) {
                    Log.e ("SendMail", e.getMessage (), e);
                }

            }
        });
    }
}