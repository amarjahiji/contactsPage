package com.example.contactspage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetails extends AppCompatActivity {

    private TextView contactName, contactNumber, contactEmail;
    private ImageView cicon;
    private Button back;
    @Override
    protected void  onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.contactdetails);

        back=findViewById(R.id.backbutton);
        contactName = findViewById(R.id.contactName);
        contactNumber=findViewById(R.id.contactNumber);
        contactEmail=findViewById(R.id.contactEmail);
        cicon=findViewById(R.id.cicon);

        back.setOnClickListener(v ->
        {
            Intent intent = new Intent(ContactDetails.this, MainActivity.class);
            startActivity(intent);
        });

        String receivedName = getIntent().getStringExtra("contactName");
        if (receivedName != null) {
            contactName.setText(receivedName);
    }
        String receivedPhoneNumber = getIntent().getStringExtra("phoneNumber");
        String receivedEmail = getIntent().getStringExtra("email");

        if (receivedPhoneNumber != null) {
            contactNumber.setText(receivedPhoneNumber);
        } else {
            contactNumber.setText(" ");
        }

        if (receivedEmail != null) {
            contactEmail.setText(receivedEmail);
        } else {
            contactEmail.setText(" ");
        }
}}
