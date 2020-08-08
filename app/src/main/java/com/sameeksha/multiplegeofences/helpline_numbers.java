package com.sameeksha.multiplegeofences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class helpline_numbers extends AppCompatActivity {
    String[] helplinenumbers=new String[]{"NATIONAL EMERGENCY" ,"POLICE","AMBULANCE","FIRE","WOMEN HELPLINE","ROAD ACCIDENT"
            ,"SENIOR CITIZEN HELPLINE","TOURIST HELPLINE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_helpline_numbers);
        this.setTitle("Helpline Numbers");
        ListView listView=(ListView) findViewById(R.id.helpline_numbers);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helplinenumbers);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:112"));
                    startActivity(intent);
                }
               else if(position==1)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:100"));
                    startActivity(intent);
                }
                else if(position==2)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:102"));
                    startActivity(intent);
                }
                else if(position==3)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:101"));
                    startActivity(intent);
                }
                else if(position==4)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:1091"));
                    startActivity(intent);
                }
                else if(position==5)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:1073"));
                    startActivity(intent);
                }
                else if(position==6)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:1091"));
                    startActivity(intent);
                }
                else if(position==7)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:1363"));
                    startActivity(intent);
                }
            }
        });
    }
}
