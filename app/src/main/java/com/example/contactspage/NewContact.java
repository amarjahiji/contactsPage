package com.example.contactspage;
import android.content.Intent;
import android.os.Bundle;
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
        savebutton=findViewById(R.id.savebutton);
        dismissbutton=findViewById(R.id.dismissbutton);

        savebutton.setOnClickListener(v -> {

            ContactModel contactModel;
            contactModel= new ContactModel(firstName.getText().toString(),lastName.getText().toString(),phoneNumber.getText().toString(),email.getText().toString());
            Toast.makeText(NewContact.this,contactModel.toString(), Toast.LENGTH_SHORT).show();


            DatabaseHelper databaseHelper = new DatabaseHelper(NewContact.this);
            boolean test = databaseHelper.add(contactModel);
            Toast.makeText(NewContact.this, "test" + test, Toast.LENGTH_SHORT);
        {
            Intent intent = new Intent(NewContact.this, MainActivity.class);
            startActivity(intent);
        };
//
//            if (firstname.isEmpty()) {
//                        Toast.makeText(NewContact.this, "First name cannot be empty", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    if (phonenumber.isEmpty() || !phonenumber.matches("\\d+")) {
//                        Toast.makeText(NewContact.this, "Phone number must contain only digits", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    else{
//                        DatabaseHelper databaseHelper = new DatabaseHelper(NewContact.this);
//                    }
//                });
//
//
//            dismissbutton.setOnClickListener(v ->
//            {
//                Intent intent = new Intent(NewContact.this, MainActivity.class);
//                startActivity(intent);
            });
}}