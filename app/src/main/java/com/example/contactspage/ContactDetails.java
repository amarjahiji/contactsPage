package com.example.contactspage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetails extends AppCompatActivity {

    private TextView contactName, contactNumber, contactEmail;
    private Button back, deleteButton;
    private String contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactdetails);

        back = findViewById(R.id.backbutton);
        contactName = findViewById(R.id.contactName);
        contactNumber = findViewById(R.id.contactNumber);
        contactEmail = findViewById(R.id.contactEmail);
        deleteButton = findViewById(R.id.deletebutton);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(ContactDetails.this, MainActivity.class);
            startActivity(intent);
        });

        String receivedName = getIntent().getStringExtra("contactName");
        if (receivedName != null) {
            contactName.setText(receivedName);
        }

        String receivedNumber = getIntent().getStringExtra("number");
        String receivedEmail = getIntent().getStringExtra("email");

        if (receivedNumber != null) {
            contactNumber.setText(receivedNumber);
        } else {
            contactNumber.setText(" ");
        }

        if (receivedEmail != null) {
            contactEmail.setText(receivedEmail);
        } else {
            contactEmail.setText(" ");
        }

        deleteButton.setOnClickListener(v -> confirmDialog());
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Contact?");
        builder.setMessage("Are you sure you want to delete this contact?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(ContactDetails.this);
            databaseHelper.deleteContact(contactId);
            Toast.makeText(ContactDetails.this, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // Do nothing if 'No' is clicked
        });
        builder.create().show();
    }
}
