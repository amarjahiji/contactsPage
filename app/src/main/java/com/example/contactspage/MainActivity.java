package com.example.contactspage;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button plusbutton;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ContactAdapter contactAdapter;
private SearchView searchBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        plusbutton = findViewById(R.id.plusbutton);
        recyclerView = findViewById(R.id.recyclerView);
        searchBar=findViewById(R.id.searchbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plusbutton.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);
        });


        loadData();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
//
            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter contact list based on search query
                ArrayList<ContactModel> filteredList = databaseHelper.searchContacts(newText.toLowerCase());
                contactAdapter.updateList(filteredList);
                return false;
            }
        });


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


    }

@Override
    protected void onResume(){
        super.onResume();
        loadData();
}
}

