package com.example.contactspage;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ImageButton plusbutton;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ContactAdapter contactAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        plusbutton = findViewById(R.id.plusbutton);
        recyclerView = findViewById(R.id.recyclerView);

        plusbutton.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);
        });

        loadData();
    }

    private void loadData() {
        ArrayList<ContactModel> contactList = databaseHelper.getAllData();
        contactAdapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
    }
@Override
    protected void onResume(){
        super.onResume();
        loadData();
}
}

