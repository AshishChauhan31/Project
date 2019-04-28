package com.example.akhilesh.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button REGISTER;
    RadioButton Male ,Female;
    TextView Register;
    EditText Email ,Phone ,Name,Last_Name,Password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    String flag = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_registration);

        Email = (EditText)findViewById (R.id.Email);
        Phone = (EditText)findViewById (R.id.Phone);
        Name = (EditText)findViewById (R.id.Name);
        Last_Name = (EditText)findViewById (R.id.Last_Name);
        Password = (EditText)findViewById (R.id.Password);
        progressBar = findViewById (R.id.progressBar);
        progressBar.setVisibility (View.GONE);
        findViewById (R.id.REGISTER).setOnClickListener (this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart ();
        if (mAuth.getCurrentUser ()!= null){


        }
    }

    private void Registeruser() {

          final  String email = Email.getText ().toString ().trim();
          final  String phone = Phone.getText ().toString ().trim();
          final String name = Name.getText ().toString ().trim();
          final  String last_name = Last_Name.getText ().toString ().trim();
            String password = Password.getText ().toString ().trim();

                if(email.isEmpty ()){
                    Email.setError ("Please enter Email");
                    Email.requestFocus ();
                    return ;

                }
                if(!Patterns.EMAIL_ADDRESS.matcher (email).matches ()){
                    Email.setError ("Please enter Correct Email Address");
                    Email.requestFocus ();
                    return ;
                }
                if(phone.isEmpty()){
                    Phone.setError ("Please enter Phone number");
                    Phone.requestFocus ();
                    return ;
                }
                if(name.isEmpty()){
                    Name.setError ("Please enter Name");
                    Name.requestFocus ();
                    return ;
                }
                if(last_name.isEmpty()){
                    Last_Name.setError ("Please enter Last Name");
                    Last_Name.requestFocus ();
                    return ;
                }
                if(password.isEmpty()){
                    Password.setError ("Please enter password");
                    Password.requestFocus ();
                    return ;
                }
                if(password.length ()<6){
                    Password.setError ("Please enter password more then 6 char");
                    Password.requestFocus ();
                    return ;

                }


                final ProgressDialog dialog = new ProgressDialog (Registration.this);
                dialog.setTitle ("Registering User");
                dialog.setMessage ("Please wait");
                dialog.show ();
                mAuth.createUserWithEmailAndPassword (email,password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       progressBar.setVisibility (View.GONE);
                        if(task.isSuccessful ()){

                            userPojo user = new userPojo (email,phone,name,last_name);

                            FirebaseDatabase.getInstance ().getReference ("Users")
                                    .child (FirebaseAuth.getInstance ().getCurrentUser ().getUid ())
                                    .setValue (user).addOnCompleteListener (new OnCompleteListener<Void> () {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful ()){
                                        Toast.makeText (Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show ();
                                        dialog.dismiss ();
                                        Intent intent = new Intent (getApplicationContext (), MainActivity.class);
                                        startActivity (intent);
                                    }
                                }
                            });


                        }else{
                            Toast.makeText (Registration.this,task.getException ().getMessage (), Toast.LENGTH_SHORT).show ();
                            dialog.dismiss ();
                        }

                    }
                });
        }

    @Override
    public void onClick(View v) {
        Registeruser ();



    }
}
