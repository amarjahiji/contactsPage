package com.example.contactspage;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList<ContactModel> contactList;
    private DatabaseHelper databaseHelper;


    public ContactAdapter(Context context, ArrayList<ContactModel> contactList) {
        this.context=context;
        this.contactList = contactList;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contactsrows,parent,false);
        ContactViewHolder vh = new ContactViewHolder(view);
        return vh;
    }

    @Override //This method is called to bind data to a viewholder at a specific position
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            ContactModel contactModel = contactList.get(position);
            String firstname = contactModel.getFirstname();
            String lastname = contactModel.getLastname();
             String number = contactModel.getNumber();
            String email = contactModel.getEmail();

        holder.contactName.setText(firstname + " " + lastname);
        holder.contactIcon.setImageResource(R.drawable.person_male_svgrepo_com);
        holder.infoIcon.setImageResource(R.drawable.inffoo);
//

        holder.infoIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, ContactDetails.class);
            intent.putExtra("contactId", contactModel.getId());
            intent.putExtra("contactName", contactModel.getFirstname() + " " + contactModel.getLastname());
            intent.putExtra("number", number);
            intent.putExtra("email", email);
            context.startActivity(intent);


        });


    }

    @Override //total number of items in db
    public int getItemCount() {
        return contactList.size();
    }

    //method for updating the list of contacts that are displaying in the adapter
    public void updateList(ArrayList<ContactModel> newList) {
        this.contactList.clear();
        this.contactList.addAll(newList);
        notifyDataSetChanged();
    }


     class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView contactIcon, infoIcon;
        TextView contactName, contactEmail, contactNumber;
        public ContactViewHolder(@NonNull View itemView) { //// Constructor to initialize the ViewHolder
            super(itemView);

            contactIcon = itemView.findViewById(R.id.contactIcon);
            infoIcon=itemView.findViewById(R.id.infoIcon);
            contactName=itemView.findViewById(R.id.contactName);
            contactEmail=itemView.findViewById(R.id.contactEmail);
            contactNumber=itemView.findViewById(R.id.contactNumber);
        }
    }
}
