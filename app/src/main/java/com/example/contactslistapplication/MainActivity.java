package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;

    private static final int ADD_CONTACT_REQUEST = 1;
    private static final int EDIT_CONTACT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactList = new ArrayList<>();
        contactList.add(new Contact("Nimal perera", "0778890308", "nimal@example.com"));
        contactList.add(new Contact("Sunil Dias", "0788890308", "sunil@example.com"));
        contactList.add(new Contact("Kethaka alwis", "0778890508", "kethaka@example.com"));
        contactList.add(new Contact("kushan silva", "0118890308", "kushan@example.com"));
        contactList.add(new Contact("Methmi Darmakeerthi", "0768890308", "methmi@example.com"));
        contactList.add(new Contact("Dinushika prasadini", "0778833308", "dinu@example.com"));
        contactList.add(new Contact("Gayathma hiruni", "0718890308", "gaya@example.com"));
        contactList.add(new Contact("Kavishka", "0778890333", "kavi@example.com"));
        contactList.add(new Contact("Tharindu Nayanajith", "0778890336", "tharindu@example.com"));
        contactList.add(new Contact("Sunil perera", "0788090308", "perera@example.com"));
        contactList.add(new Contact("Danushkha Dias", "076903508", "dias@example.com"));
        contactList.add(new Contact("Isiri alwis", "071889508", "ishu@example.com"));
        contactList.add(new Contact("Nipuni silva", "0778000308", "nipuni@example.com"));
        contactList.add(new Contact("Kamal dilshan", "0760190308", "kamal@example.com"));
        contactList.add(new Contact("Pabasara kurera", "0728833308", "paba@example.com"));
        contactList.add(new Contact("Rashmi Diwyanjalee", "0768890308", "rash@example.com"));
        contactList.add(new Contact("Sithum sankalpa", "0776690333", "sithum@example.com"));
        contactList.add(new Contact("Tharindu Nipun", "0777790336", "thari@example.com"));

        contactAdapter = new ContactAdapter(contactList, this);
        recyclerView.setAdapter(contactAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
            startActivityForResult(intent, ADD_CONTACT_REQUEST);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterContacts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return false;
            }
        });

        return true;
    }

    private void filterContacts(String query) {
        List<Contact> filteredList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(contact);
            }
        }
        contactAdapter.filterList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String phoneNumber = data.getStringExtra("phoneNumber");
            String email = data.getStringExtra("email");

            if (requestCode == ADD_CONTACT_REQUEST) {
                Contact newContact = new Contact(name, phoneNumber, email);
                contactAdapter.addContact(newContact);
            } else if (requestCode == EDIT_CONTACT_REQUEST) {
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    Contact updatedContact = new Contact(name, phoneNumber, email);
                    contactAdapter.updateContact(position, updatedContact);
                }
            }
        }
    }
}
