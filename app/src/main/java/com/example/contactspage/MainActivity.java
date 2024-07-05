package com.example.contactspage;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ImageButton plusbutton, infoIcon;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plusbutton.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);
        });


        loadData();
    }

    private void loadData() {
        ArrayList<ContactModel> contactList = databaseHelper.getAllData();
        if(contactList.isEmpty()){
            Log.d("MainActivity","Contacts retrieved: " + contactList.size());
        } else {
            Log.d("MainActivity", "Contacts retrieved: " + contactList.size());
        }
        contactAdapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }

@Override
    protected void onResume(){
        super.onResume();
        loadData();
}
}

