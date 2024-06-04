package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactslistapplication.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AddEditContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private Button saveButton;

    private int position = -1;

    public List<Contact> contactsList = new ArrayList<>();
    public ContactsAdapter adapter;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        nameEditText = findViewById(R.id.fullName);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.email);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            setTitle("Edit Contact");
            nameEditText.setText(intent.getStringExtra("name"));
            phoneNumberEditText.setText(intent.getStringExtra("phoneNumber"));
            emailEditText.setText(intent.getStringExtra("email"));
            position = intent.getIntExtra("position", -1);
        } else {
            setTitle("Add Contact");
        }

        saveButton.setOnClickListener(v -> saveContact());
    }

    private void saveContact() {

        String name = nameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();

        if (name.trim().isEmpty() || phoneNumber.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a name and phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        MyApplication app = (MyApplication) getApplicationContext();
        app.setSharedData(name, phoneNumber, email);
        Map<String, Map<String, String>> returnedMap = app.getOuterMap();
        List<Person> personList = new ArrayList<>();

        // Iterating over the outerMap to create Person objects
        for (Map.Entry<String, Map<String, String>> entry : returnedMap.entrySet()) {
            String id = entry.getKey();
            Map<String, String> details = entry.getValue();
            String name_new = details.get("name");
            String phoneNumber_new = details.get("phone_number");
            String email_new = details.get("email");
            contactsList.add(new Contact(name_new, phoneNumber_new, email_new));
        }

        adapter = new ContactsAdapter(contactsList, this);
        RecyclerViewManager.getRecyclerView().setAdapter(adapter);;
        RecyclerViewManager.getInstance().getAdapter();
        RecyclerViewManager.getInstance().refreshRecyclerView();

        Intent data = new Intent();
        data.putExtra("name", name);
        data.putExtra("phoneNumber", phoneNumber);
        data.putExtra("email", email);
        data.putExtra("position", position);

        setResult(RESULT_OK, data);

        finish();
    }

}
