package com.sameeksha.multiplegeofences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.emergency.EmergencyNumber;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
  CardView geofence_cardview;
    CardView addcontacts_cardview;
    CardView emergencylist_cardview;
    CardView helpnumbers_cardview;



    //TextView geofence_textview;
    //TextView addcontacts_textview;
    //Button signout;
   // TextView emergencylist_textview;
    private FirebaseAuth myFirebaseAuth;
    private FirebaseUser myFirebaseUser;
   // TextView dailnumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startActivity(new Intent(MainActivity.this,dashboard.class));


      // Toast.makeText(MainActivity.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        this.setTitle("Spot-n-Alert");

        myFirebaseAuth = FirebaseAuth.getInstance();
        myFirebaseUser  = myFirebaseAuth.getCurrentUser();
        geofence_cardview = findViewById(R.id.geofences1);
        geofence_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        addcontacts_cardview=findViewById(R.id.phonebook1);
       addcontacts_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ContactList.class));
            }
        });
       /* signout = findViewById(R.id.signout_button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFirebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });*/
        emergencylist_cardview = findViewById(R.id.emergencycontact1);
        emergencylist_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Emergencylist.class));
            }
        });
        helpnumbers_cardview=findViewById(R.id.helplinenumbers1);
        helpnumbers_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,helpline_numbers.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.Signout==item.getItemId()) {
            myFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
