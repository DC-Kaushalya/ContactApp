package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private List<Contact> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initializeContacts(); // Load the contacts data
        adapter = new ContactsAdapter(contactsList, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // Intent to open the AddEditContactActivity
            Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
            startActivity(intent);
        });
    }

    private void initializeContacts() {
        // Here you would add hardcoded contacts
        contactsList.add(new Contact("Nimal perera", "0778890308", "nimal@example.com"));
        contactsList.add(new Contact("Sunil Dias", "0788890308", "sunil@example.com"));
        contactsList.add(new Contact("Kethaka alwis", "0778890508", "kethaka@example.com"));
        contactsList.add(new Contact("kushan silva", "0118890308", "kushan@example.com"));
        contactsList.add(new Contact("Methmi Darmakeerthi", "0768890308", "methmi@example.com"));
        contactsList.add(new Contact("Dinushika prasadini", "0778833308", "dinu@example.com"));
        contactsList.add(new Contact("Gayathma hiruni", "0718890308", "gaya@example.com"));
        contactsList.add(new Contact("Kavishka", "0778890333", "kavi@example.com"));
        contactsList.add(new Contact("Tharindu Nayanajith", "0778890336", "tharindu@example.com"));
        contactsList.add(new Contact("Sunil perera", "0788090308", "perera@example.com"));
        contactsList.add(new Contact("Danushkha Dias", "076903508", "dias@example.com"));
        contactsList.add(new Contact("Isiri alwis", "071889508", "ishu@example.com"));
        contactsList.add(new Contact("Nipuni silva", "0778000308", "nipuni@example.com"));
        contactsList.add(new Contact("Kamal dilshan", "0760190308", "kamal@example.com"));
        contactsList.add(new Contact("Pabasara kurera", "0728833308", "paba@example.com"));
        contactsList.add(new Contact("Rashmi Diwyanjalee", "0768890308", "rash@example.com"));
        contactsList.add(new Contact("Sithum sankalpa", "0776690333", "sithum@example.com"));
        contactsList.add(new Contact("Tharindu Nipun", "0777790336", "thari@example.com"));


    }
}
