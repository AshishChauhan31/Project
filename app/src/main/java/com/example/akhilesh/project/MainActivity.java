package com.example.akhilesh.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView login_heading;
    private EditText email, password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    Button login_button;
    TextView forget_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance ();
        login_heading = findViewById (R.id.Login_heading);
        email = (EditText) findViewById (R.id.email);
        password = (EditText) findViewById (R.id.pass);
        findViewById (R.id.loginButton).setOnClickListener (this);
        forget_password = findViewById (R.id.Forget_password);

        forget_password.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

    }

    private void login(){

            String inputemail = email.getText ().toString ().trim ();
            final String inputpassword = password.getText ().toString ();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences (this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Useremail",inputemail);
            editor.apply();


            if(!Patterns.EMAIL_ADDRESS.matcher (inputemail).matches ()){
                email.setError ("Please enter Correct Email Address");
                email.requestFocus ();
                return ;
             }
            if(inputemail.isEmpty ()){
                email.setError ("Please enter Email Address");
                email.requestFocus ();
                return ;
            }
        mAuth.signInWithEmailAndPassword (inputemail,inputpassword).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                final ProgressDialog dialog = new ProgressDialog (MainActivity.this);
                dialog.setTitle ("Logging In");
                dialog.setMessage ("Please wait");
                dialog.show ();
                if(task.isSuccessful ()){
                    Toast.makeText (MainActivity.this,"Login success",Toast.LENGTH_LONG).show ();
                    dialog.dismiss ();
                    Intent subjectintent = new Intent (getApplicationContext (),SubjectChoice2.class);
                    startActivity (subjectintent);
                }else{
                    Toast.makeText (MainActivity.this ,task.getException ().getMessage (),Toast.LENGTH_SHORT).show();
                    dialog.dismiss ();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        login ();


    }




}