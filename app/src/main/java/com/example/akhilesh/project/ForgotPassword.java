package com.example.akhilesh.project;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText ForgotPassword_Email;
    Button button;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forgot_password);
        button = findViewById (R.id.button);
        ForgotPassword_Email = findViewById (R.id.ForgotPassword_Email);
        progressBar = findViewById (R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance ();
        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog (ForgotPassword.this);
                dialog.setTitle ("Sending Email");
                dialog.setMessage ("Please wait");
                dialog.show ();
                firebaseAuth.sendPasswordResetEmail (ForgotPassword_Email.getText ().toString ())
                        .addOnCompleteListener (new OnCompleteListener<Void> () {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility (View.GONE);
                                if(task.isSuccessful ()){
                                    Toast.makeText (ForgotPassword.this,"Password sent successfully",Toast.LENGTH_SHORT).show ();
                                    dialog.dismiss ();
                                    Intent LOGIN = new Intent (ForgotPassword.this,MainActivity.class);
                                    startActivity (LOGIN);
                                }
                                else{
                                    dialog.dismiss ();
                                    Toast.makeText (ForgotPassword.this,task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                                }
                            }
                        });
            {



                }
            }
        });


    }
}
