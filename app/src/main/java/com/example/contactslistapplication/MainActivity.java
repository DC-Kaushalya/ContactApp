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

//        Map data = app.getOuterMap();
        Map<String, Map<String, String>> returnedMap = app.getOuterMap();

//        Map<String, String> details1 = new HashMap<>();
//        details1.put("name", "Name 1");
//        details1.put("phone_number", "Phone 1");
//        details1.put("email", "Email 1");
//        outerMap.put("1", details1);
//
//        Map<String, String> details2 = new HashMap<>();
//        details2.put("name", "Name 2");
//        details2.put("phone_number", "Phone 2");
//        details2.put("email", "Email 2");
//        outerMap.put("2", details2);

        // Creating a list to hold Person objects
        List<Person> personList = new ArrayList<>();

        // Iterating over the outerMap to create Person objects
        for (Map.Entry<String, Map<String, String>> entry : returnedMap.entrySet()) {
            String id = entry.getKey();
            Map<String, String> details = entry.getValue();
            String name = details.get("name");
            String phoneNumber = details.get("phone_number");
            String email = details.get("email");

//            Person person = new Person(id, name, phoneNumber, email);
//            personList.add(person);

//            Contact person = new Contact(id, name, phoneNumber, email);
            contactsList.add(new Contact(name, phoneNumber, email));
        }

//        saveButton = findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("KUSH", "SAVE BUTTON CLICKED");
//            }
//        });
        // Printing the list of Person objects
        System.out.println(personList);

        Log.i("KUSH", String.valueOf(personList));

        Log.i("KUSH", "This is a debug log message");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("KUSH", "MainActivity.java String.valueOf(R.id.recyclerView)");
        Log.i("KUSH", String.valueOf(R.id.recyclerView));
        recyclerView = findViewById(R.id.recyclerView);
        Log.i("KUSH", "MainActivity.java String.valueOf(recyclerView)");
        Log.i("KUSH", String.valueOf(recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        initializeContacts(); // Load the contacts data
        Log.i("KUSH", String.valueOf(contactsList));
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
