package com.brekhov1.contactshw;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContactsViewHolder> {

    private ArrayList<Contact> contacts;
    private Context context;

    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactsViewHolder(view);
    }

    public RVAdapter(ArrayList<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    public void onBindViewHolder(final ContactsViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.setRecord(contact, position);
        holder.name.setText(contact.getName());
        holder.phoneNr.setText(contact.getPhoneNr());
        holder.birthDate.setText(contact.getBirthDate());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNr, birthDate;
        CardView cardView;
        ImageButton call;
        Contact contact;
        int position;


        public ContactsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phoneNr = view.findViewById(R.id.phoneNr);
            birthDate = view.findViewById(R.id.birthDate);
            cardView = view.findViewById(R.id.cardView);
            call = view.findViewById(R.id.call);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("contact", position);
                    MainActivity.fa.finish();
                    context.startActivity(intent);


                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callNew = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNr.getText().toString().trim()));
                    context.startActivity(callNew);
                }
            });

        }

        void setRecord(Contact contact,int position){
            this.contact = contact;
            this.position = position;
        }
    }

}