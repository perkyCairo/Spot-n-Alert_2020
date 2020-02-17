package com.sameeksha.multiplegeofences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LauncherActivity extends AppCompatActivity {

    Button login,signup;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseUser myFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        myFirebaseAuth = FirebaseAuth.getInstance();
        myFirebaseUser  = myFirebaseAuth.getCurrentUser();
        if (myFirebaseUser != null)
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        login = findViewById(R.id.click_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LauncherActivity.this,LoginActivity.class));

            }
        });
        signup = findViewById(R.id.click_Signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LauncherActivity.this,SignupActivity.class));
            }
        });
    }
}
