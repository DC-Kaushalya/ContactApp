package com.example.contactslistapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Contact> contacts;
    private Context context;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Contact contact = contacts.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactPhone.setText(contact.getPhoneNumber());
        holder.contactEmail.setText(contact.getEmail());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeContact(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void removeContact(int position) {
        contacts.remove(position);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView contactAvatar;
        TextView contactName, contactPhone, contactEmail;
        Button deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            contactAvatar = itemView.findViewById(R.id.avatar);
            contactName = itemView.findViewById(R.id.contactName);
            contactPhone = itemView.findViewById(R.id.phoneNumber);
            contactEmail = itemView.findViewById(R.id.email);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
