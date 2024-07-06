package com.example.contactspage;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;            //importing the necessary libraries
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
//declaring ui elements from layout activity_main.xml
    private Button plusbutton;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ContactAdapter contactAdapter;
private SearchView searchBar ;

//setting up the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializingg
        databaseHelper = new DatabaseHelper(this);
        plusbutton = findViewById(R.id.plusbutton);
        recyclerView = findViewById(R.id.recyclerView);
        searchBar=findViewById(R.id.searchbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //method for clicking the button thats used to get the user to the newcontact layout
        plusbutton.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);
        });

//calling function to retrieve and update data
        loadData();


        //function def for the search bar
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
//filtering contacts based on letters searched
            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter contact list based on search query
                ArrayList<ContactModel> filteredList = databaseHelper.searchContacts(newText.toLowerCase());
                contactAdapter.updateList(filteredList);
                return false;
            }
        });


    }

    //function definition
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

