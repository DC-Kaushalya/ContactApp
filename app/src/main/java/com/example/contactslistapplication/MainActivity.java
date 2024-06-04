package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
//import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    public RecyclerView recyclerView;
    public ContactsAdapter adapter;
    public List<Contact> contactsList = new ArrayList<>();

//    private Button saveButton;

//    public Map<String, Map<String, String>> outerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MyApplication app = (MyApplication) getApplicationContext();
        app.setSharedData("Name One", "1111111111", "email1@gmail.com");
        app.setSharedData("Name Two", "2222222222", "email2@gmail.com");

        Map<String, Map<String, String>> returnedMap = app.getOuterMap();

        // Creating a list to hold Person objects
        List<Person> personList = new ArrayList<>();

        // Iterating over the outerMap to create Person objects
        for (Map.Entry<String, Map<String, String>> entry : returnedMap.entrySet()) {
            String id = entry.getKey();
            Map<String, String> details = entry.getValue();
            String name = details.get("name");
            String phoneNumber = details.get("phone_number");
            String email = details.get("email");


            contactsList.add(new Contact(name, phoneNumber, email));
        }

        // Printing the list of Person objects
        System.out.println(personList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        initializeContacts(); // Load the contacts data
        adapter = new ContactsAdapter(contactsList, this);
        recyclerView.setAdapter(adapter);

        // Set RecyclerView and adapter in RecyclerViewManager
        RecyclerViewManager.getInstance().setRecyclerView(recyclerView, adapter);
//        adapter.notifyDataSetChanged();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // Intent to open the AddEditContactActivity
            Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
            startActivity(intent);
        });
    }
}
