package com.sameeksha.multiplegeofences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Emergencylist extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    ListView l2;

    private ArrayList<String> arrayList = new ArrayList<>();

    DatabaseReference databaseReference;
    DatabaseReference childdatabasereference;
   // Button btndel;
    String str;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emergencylist);
        this.setTitle("Emergency Contact List");
        Toast.makeText(Emergencylist.this,"Tip: Long Press to remove from emergency contacts",Toast.LENGTH_LONG).show();


//l2.setAdapter(arrayAdapter);
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        childdatabasereference = databaseReference.child("Emergency contacts");

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.modification_list, arrayList);
        l2 = findViewById(R.id.emergencylistview);
        l2.setAdapter(arrayAdapter);
     //   btndel = findViewById(R.id.delbutton);


        childdatabasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String s1 = dataSnapshot.child("emergency").getValue(String.class);
                System.out.println(s1);
                arrayList.add(s1);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String s1 = dataSnapshot.child("emergency").getValue(String.class);

                arrayList.add(s1);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String s1 = dataSnapshot.child("emergency").getValue(String.class);

                arrayList.remove(s1);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                str = arrayList.get(position);


            }
        });

        /*  btndel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                //  databaseReference.child("emergency").child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                      deleteEmergency();

                      }});
                  Toast.makeText(Emergencylist.this,"Emergency contact deleted!",Toast.LENGTH_LONG).show();

              }
              public void deleteEmergency()
              {
                  DatabaseReference user=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                  DatabaseReference emergency=user.child("Emergency contacts ");
                  emergency.removeValue();
                  Toast.makeText(this,"Emercency Contact is deleted",Toast.LENGTH_LONG).show();
              }*/

        l2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final int n=position;
                new AlertDialog.Builder(Emergencylist.this)
                        .setTitle("Are you sure")
                        .setMessage("Do you want to delete this contact")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // DatabaseReference user=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                                //DatabaseReference emergency=user.child("Emergency contacts ");
                                //emergency.removeValue();
                                arrayList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(Emergencylist.this,"Emercency Contact is deleted",Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("No",null).show();
                return  true;

            }
        });

    }
}



