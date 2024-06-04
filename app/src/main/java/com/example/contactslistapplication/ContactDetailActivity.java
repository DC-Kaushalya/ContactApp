package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView contactName;
    private TextView contactPhoneNumber;
    private TextView contactEmail;
    private Button updateButton;
    private Button deleteButton;

    private String name;
    private String phone;
    private String email;

    public ContactsAdapter adapter;

    public List<Contact> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contactName = findViewById(R.id.contactName);
        contactPhoneNumber = findViewById(R.id.contactPhoneNumber);
        contactEmail = findViewById(R.id.contactEmail);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        // Get the data passed from MainActivity
        name = getIntent().getStringExtra("NAME");
        phone = getIntent().getStringExtra("PHONE");
        email = getIntent().getStringExtra("EMAIL");

        contactName.setText(name);
        contactPhoneNumber.setText(phone);
        contactEmail.setText(email);

        updateButton.setOnClickListener(v -> updateContact());
        deleteButton.setOnClickListener(v -> deleteContact());
    }

    private void updateContact() {
        MyApplication app = (MyApplication) getApplicationContext();
        app.deletedData(phone, email);

        // Start an activity for updating the contact
        Intent intent = new Intent(ContactDetailActivity.this, AddEditContactActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("phoneNumber", phone);
        intent.putExtra("email", email);
        startActivityForResult(intent, 1);  // Request code for update
    }

    private void deleteContact() {
        MyApplication app = (MyApplication) getApplicationContext();
        app.deletedData(phone, email);
        Map<String, Map<String, String>> returnedMap = app.getOuterMap();

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
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle the result of AddEditContactActivity
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Update the displayed contact details if they are changed
            name = data.getStringExtra("name");
            phone = data.getStringExtra("phoneNumber");
            email = data.getStringExtra("email");

            contactName.setText(name);
            contactPhoneNumber.setText(phone);
            contactEmail.setText(email);
        }
    }
}
