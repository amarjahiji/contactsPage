package com.example.contactspage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetails extends AppCompatActivity {

    private TextView contactName, cpnumber, cemail;
    private ImageView cicon;
    @Override
    protected void  onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.contactdetails);

        contactName = findViewById(R.id.contactName);
        cpnumber=findViewById(R.id.cpnumber);
        cemail=findViewById(R.id.cemail);
        cicon=findViewById(R.id.cicon);

        String receivedName = getIntent().getStringExtra("contactName");
        if (receivedName != null) {
            contactName.setText(receivedName);
    }
}}
