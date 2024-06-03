package com.example.contactslistapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> implements Filterable {

    private List<Contact> contacts;
    private List<Contact> contactsFull; // Full list to keep the original data
    private Context context;

    public ContactsAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
        this.contactsFull = new ArrayList<>(contacts); // Initialize with full list of contacts
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneNumberTextView.setText(contact.getPhoneNumber());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ContactDetailActivity.class);
            intent.putExtra("NAME", contact.getName());
            intent.putExtra("PHONE", contact.getPhoneNumber());
            intent.putExtra("EMAIL", contact.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    private Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(contactsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Contact item : contactsFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) ||
                            item.getPhoneNumber().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contacts.clear();
            contacts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, phoneNumberTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
        }
    }
}
