package com.sameeksha.multiplegeofences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Emergencylist extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    ListView l2;
    ArrayList<String> Emergencylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencylist);
        ContactList contactList= new ContactList();
        Emergencylist=(ArrayList<String>)getIntent().getSerializableExtra("EmergencyList");
        System.out.println(Emergencylist);

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Emergencylist);
        //l1.setAdapter(arrayList);
        l2.setAdapter(arrayAdapter);

    }
}
