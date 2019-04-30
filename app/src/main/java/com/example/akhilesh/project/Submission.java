package com.example.akhilesh.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Submission extends AppCompatActivity {
    private TextView sub;
    private Button send;
    private TextView email;

    public Submission() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_submission);

        sub = findViewById (R.id.tv_Subjects);
        email = findViewById (R.id.tv_Email);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString("Useremail", "defaultValue");
        email.setText (value);

        Intent intent = getIntent ();
        final String S1_firstCheckbox = intent.getStringExtra ("cb1");
        final String S1_secondCheckbox = intent.getStringExtra ("cb2");
        sub.setText ("Sub_choices:\n" + S1_firstCheckbox + "\n" + S1_secondCheckbox);

        send = (Button) this.findViewById (R.id.Submission);
        send.setOnClickListener (new View.OnClickListener () {
            public void onClick(View v) {
                Toast.makeText (Submission.this, "submitted succesfully!!", Toast.LENGTH_SHORT).show ();
                sendMessage ( S1_firstCheckbox, S1_secondCheckbox);

            }
        });


    }

        private void sendMessage (String s1,String s2) {
            final String _1st = s1;
            final String _2nd = s2;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            final String value1 = preferences.getString("Useremail", "defaultValue");
            final ProgressDialog dialog = new ProgressDialog (Submission.this);
            dialog.setTitle ("Sending Email");
            dialog.setMessage ("Please wait");
            dialog.show ();
            Thread sender = new Thread (new Runnable () {
                @Override
                public void run() {
                    try {
                        GMailSender sender = new GMailSender ("projectsubjectify@gmail.com", "Shivam123");
                        sender.sendMail ("Subject Chosen by user " +value1,
                                "This is subject choice 1 : "+_1st+"\n"+"This is subject choice 2 :"+_2nd,
                                "Akhilesh",
                                "Chauhan124ashish@gmail.com,"+value1);
                        dialog.dismiss ();
                    } catch (Exception e) {
                        Log.e ("mylog", "Error: " + e.getMessage ());
                    }
                }
            });
            sender.start ();
        }


    }
