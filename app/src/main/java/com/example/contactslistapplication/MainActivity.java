package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

class Person {
    private String id;
    private String name;
    private String phone_number;
    private String email;

    public Person(String id, String name, String phone_number, String email) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"phone_number\":\"" + phone_number + '\"' +
                ", \"email\":\"" + email + '\"' +
                '}';
    }
}

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    public List<Contact> contactsList = new ArrayList<>();

    private Map<String, Map<String, String>> outerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Map<String, String> details1 = new HashMap<>();
        details1.put("name", "Name 1");
        details1.put("phone_number", "Phone 1");
        details1.put("email", "Email 1");
        outerMap.put("1", details1);

        Map<String, String> details2 = new HashMap<>();
        details2.put("name", "Name 2");
        details2.put("phone_number", "Phone 2");
        details2.put("email", "Email 2");
        outerMap.put("2", details2);

        // Creating a list to hold Person objects
        List<Person> personList = new ArrayList<>();

        // Iterating over the outerMap to create Person objects
        for (Map.Entry<String, Map<String, String>> entry : outerMap.entrySet()) {
            String id = entry.getKey();
            Map<String, String> details = entry.getValue();
            String name = details.get("name");
            String phoneNumber = details.get("phone_number");
            String email = details.get("email");

            Person person = new Person(id, name, phoneNumber, email);
            personList.add(person);
        }

        // Printing the list of Person objects
        System.out.println(personList);

        Log.i("KUSH", String.valueOf(personList));

        Log.i("KUSH", "This is a debug log message");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initializeContacts(); // Load the contacts data
        Log.i("KUSH", String.valueOf(contactsList));
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
        contactsList.add(new Contact("Nimal Perera", "0778890308", "nimal@example.com"));
        contactsList.add(new Contact("Sunil Dias", "0788890308", "sunil@example.com"));
        contactsList.add(new Contact("Kethaka Alwis", "0778890508", "kethaka@example.com"));
        contactsList.add(new Contact("Kushan Silva", "0118890308", "kushan@example.com"));
        contactsList.add(new Contact("Methmi Darmakeerthi", "0768890308", "methmi@example.com"));
        contactsList.add(new Contact("Dinushika Prasadini", "0778833308", "dinu@example.com"));
        contactsList.add(new Contact("Gayathma hiruni", "0718890308", "gaya@example.com"));
        contactsList.add(new Contact("Kavishka", "0778890333", "kavi@example.com"));
        contactsList.add(new Contact("Tharindu Nayanajith", "0778890336", "tharindu@example.com"));
        contactsList.add(new Contact("Sunil perera", "0788090308", "perera@example.com"));
        contactsList.add(new Contact("Danushkha Dias", "076903508", "dias@example.com"));
        contactsList.add(new Contact("Isiri Alwis", "071889508", "ishu@example.com"));
        contactsList.add(new Contact("Nipuni silva", "0778000308", "nipuni@example.com"));
        contactsList.add(new Contact("Kamal dilshan", "0760190308", "kamal@example.com"));
        contactsList.add(new Contact("Pabasara kurera", "0728833308", "paba@example.com"));
        contactsList.add(new Contact("Rashmi Diwyanjalee", "0768890308", "rash@example.com"));
        contactsList.add(new Contact("Sithum sankalpa", "0776690333", "sithum@example.com"));
        contactsList.add(new Contact("Tharindu Nipun", "0777790336", "thari@example.com"));
    }
}
