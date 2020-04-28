package com.sameeksha.multiplegeofences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.emergency.EmergencyNumber;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView geofence_textview;
    TextView addcontacts_textview;
    Button signout;
    TextView emergencylist_textview;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseUser myFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseAuth = FirebaseAuth.getInstance();
        myFirebaseUser  = myFirebaseAuth.getCurrentUser();
        geofence_textview = findViewById(R.id.geofencing_link);
        geofence_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        addcontacts_textview=findViewById(R.id.addcontacts);
       addcontacts_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ContactList.class));
            }
        });
        signout = findViewById(R.id.signout_button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFirebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        emergencylist_textview = findViewById(R.id.emergencylist);
        emergencylist_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Emergencylist.class));
            }
        });
    }
}
