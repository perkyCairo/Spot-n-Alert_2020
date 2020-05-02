package com.sameeksha.multiplegeofences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class ContactList extends AppCompatActivity {
    ListView l1;
    String str1;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    ArrayList<String> EmergencyList;
    FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
   private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        l1=(ListView)findViewById(R.id.contactlist);
        arrayList=new ArrayList<>();
    // String   EmergencyList;

        search=(EditText)findViewById(R.id.seachbar);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else
        {
            getContact();
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().equals(""))
                    {
                        getContact();
                    }
                    else {
                        (ContactList.this).arrayAdapter.getFilter().filter(s);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

    }

    private void getContact() {
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mobile=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            arrayList.add(name+"\n"+mobile);
            Collections.sort(arrayList);
            arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
            //l1.setAdapter(arrayList);
            l1.setAdapter(arrayAdapter);
            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Object str=l1.getItemAtPosition(position);
                     str1=str.toString();
                     onPressed();
                }


            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED);
            {
                getContact();
            }
        }
    }


    public void onPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Add to Emergency Contacts ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      //  EmergencyList.add(str1);
                        saveEmergencycontact(str1);
                        Toast.makeText(ContactList.this,"Added successfully!",Toast.LENGTH_SHORT).show();
System.out.println(EmergencyList);

                    }

                    private void saveEmergencycontact(String str) {
                        //FirebaseUser user=firebaseAuth.getCurrentUser();
                        String id= databaseReference.push().getKey();
                        emergencyobject ob= new emergencyobject(str);
                      databaseReference.child("Emergency contacts/"+id).setValue(ob);
                    }

                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });

        AlertDialog alertDialog= builder.create();

alertDialog.show();



    }
}

