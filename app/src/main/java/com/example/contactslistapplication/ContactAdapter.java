package com.example.contactslistapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private Context context;

    public ContactAdapter(List<Contact> contactList, Context context) {
        this.contactList = new ArrayList<>(contactList);
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneNumberTextView.setText(contact.getPhoneNumber());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ContactDetailActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("name", contact.getName());
            intent.putExtra("phoneNumber", contact.getPhoneNumber());
            intent.putExtra("email", contact.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
        notifyItemInserted(contactList.size() - 1);
    }

    public void updateContact(int position, Contact contact) {
        contactList.set(position, contact);
        notifyItemChanged(position);
    }

    public void deleteContact(int position) {
        contactList.remove(position);
        notifyItemRemoved(position);
    }

    public void filterList(List<Contact> filteredList) {
        contactList = new ArrayList<>(filteredList);
        notifyDataSetChanged();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneNumberTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
        }
    }
}
