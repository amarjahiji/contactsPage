package com.example.contactspage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NewContact extends AppCompatActivity {

    private EditText firstName, lastName, phoneNumber, email;

    private Button dismissbutton, savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcontact);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        savebutton = findViewById(R.id.savebutton);
        dismissbutton = findViewById(R.id.dismissbutton);


        //when clicked dimiss button go back to main
        dismissbutton.setOnClickListener(v1 -> {
            Intent intent = new Intent(NewContact.this, MainActivity.class);
            startActivity(intent);
        });
    //when button clicked, this method will save a new contact
        savebutton.setOnClickListener(v -> {
            String fName = firstName.getText().toString().trim(); // Trim white spaces
            String lName = lastName.getText().toString().trim();
            String phone = phoneNumber.getText().toString().trim();
            String eMail = email.getText().toString().trim();

            //setting the requirements to be able to create a new account
            if (TextUtils.isEmpty(fName)) {
                firstName.setError("First name cannot be empty");
                firstName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(phone) || !phone.matches("^[+]*\\d+$")) {
                phoneNumber.setError("Phone number must contain only digits and optional + sign");
                phoneNumber.requestFocus();
                return;
            }
                //creating a contactmodel ovject and a databasehelper instance
            ContactModel contactModel = new ContactModel(fName, lName, phone, eMail);
            DatabaseHelper databaseHelper = new DatabaseHelper(NewContact.this);
            boolean success = databaseHelper.add(contactModel);

//helper method that will empty the fields  after (if) contact is created it
            if (success) {
                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                email.setText("");
                //when successfully creating account will send back to previous activity
                finish();
            }
            else {
                Toast.makeText(NewContact.this, "Failed to save contact.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
