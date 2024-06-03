package com.example.contactslistapplication;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize contacts list
        List<Contact> contacts = loadContacts();
        contactsAdapter = new ContactsAdapter(contacts, this);
        recyclerView.setAdapter(contactsAdapter);

        setupSearchView();
    }

    private void setupSearchView() {
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // No action on query submit for now
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactsAdapter.getFilter().filter(newText); // Filter on text change
                return true;
            }
        });
    }

    private List<Contact> loadContacts() {
        // Dummy method to simulate loading contacts
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Doe", "1234567890", "johndoe@example.com"));
        contacts.add(new Contact("Jane Smith", "0987654321", "janesmith@example.com"));
        contacts.add(new Contact("Emily Johnson", "1112233445", "emilyj@example.com"));
        return contacts;
    }

    // Dummy Contact class (make sure to use your actual Contact class)
    static class Contact {
        private String name;
        private String phoneNumber;
        private String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }
    }
}
