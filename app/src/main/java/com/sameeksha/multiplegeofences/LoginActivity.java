package com.sameeksha.multiplegeofences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText uname,upass;
    Button login_bn;
    TextView tv;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseUser myFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        myFirebaseAuth = FirebaseAuth.getInstance();
        myFirebaseUser  = myFirebaseAuth.getCurrentUser();
        if (myFirebaseUser != null)
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        uname = findViewById(R.id.username_text);
        upass = findViewById(R.id.password_text);
        login_bn = findViewById(R.id.login_button);
        login_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = uname.getText().toString();
                String pass = upass.getText().toString();
                if((email.isEmpty()) || (pass.isEmpty()))
                    Toast.makeText(LoginActivity.this, "Both fields are required.", Toast.LENGTH_SHORT).show();

                myFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Toast.makeText(SignInActivity.this, "LogIn Successful.", Toast.LENGTH_SHORT).show();
                            //checkEmailVerification();
                            finish();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            Toast.makeText(LoginActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            // startActivity(new Intent(SignInActivity.this,WelcomeActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "Wrong Credentials. LogIn Failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
        tv = findViewById(R.id.textView4);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
    }

    /*
    private void checkEmailVerification(){
        FirebaseUser firebaseUser1 = myFirebaseAuth.getCurrentUser();
        Boolean emailFlag = firebaseUser1.isEmailVerified();
        if(emailFlag)
        {
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        else
        {
            Toast.makeText(this, "Email is not verified. Please verify your email.", Toast.LENGTH_SHORT).show();
            myFirebaseAuth.signOut();
        }
    }
     */
}
