package com.sameeksha.multiplegeofences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {
    EditText reg_email;
    Button reset_bn;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseUser myFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        this.setTitle("Reset Password");
        myFirebaseAuth = FirebaseAuth.getInstance();
        reg_email = findViewById(R.id.email_text);
        reset_bn=findViewById(R.id.reset_button);
        reset_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reg_email.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(ResetPassword.this,"Please enter registered email",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    myFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful())
                          {
                              Toast.makeText(ResetPassword.this,"Email sent",Toast.LENGTH_SHORT).show();
                              Intent i = new Intent(ResetPassword.this,LoginActivity.class);
                              startActivity(i);
                          }
                          else
                          {
                              Toast.makeText(ResetPassword.this,"Error occured",Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
                }
            }
        });

    }
}
