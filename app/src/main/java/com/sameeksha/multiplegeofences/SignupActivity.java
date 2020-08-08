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

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    EditText uname, upass;
    Button signup_bn;
    TextView tv;
    private FirebaseAuth myFirebaseAuth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setTitle("Signup");

        myFirebaseAuth = FirebaseAuth.getInstance();
        uname = findViewById(R.id.uname);
        upass = findViewById(R.id.upass);
        signup_bn = findViewById(R.id.signup_button);
        tv = findViewById(R.id.textView3);
        signup_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = uname.getText().toString();
                String pass = upass.getText().toString();
                if (email.isEmpty()) {
                    uname.setError("Please enter email id.");
                    uname.requestFocus();
                } else if (pass.isEmpty()) {
                    upass.setError("Please enter password.");
                    upass.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    myFirebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignupActivity.this, "Signup Failed. Please try again later.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        FirebaseUser firebaseUser = myFirebaseAuth.getCurrentUser();
                                        String userid = firebaseUser.getUid();
                                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("id", userid);
                                        hashMap.put("username", email);

                                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                finish();
                                                startActivity(new Intent(SignupActivity.this, MainActivity.class));


                                            }
                                        });
                                    }



                                }
                            });
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(i);

                        }
                    });
                }
            }
        });
    }
}

