package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView contactName;
    private TextView contactPhoneNumber;
    private TextView contactEmail;
    private Button updateButton;
    private Button deleteButton;

    private String name;
    private String phone;
    private String email;

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
        // Start an activity for updating the contact
        Intent intent = new Intent(ContactDetailActivity.this, AddEditContactActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("phoneNumber", phone);
        intent.putExtra("email", email);
        startActivityForResult(intent, 1);  // Request code for update
    }

    private void deleteContact() {
        // Prepare to return result for deletion
        Intent resultIntent = new Intent();
        resultIntent.putExtra("DELETE", true);
        resultIntent.putExtra("NAME", name);
        setResult(RESULT_OK, resultIntent);
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
