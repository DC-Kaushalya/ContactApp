package com.example.contactslistapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        TextView contactName = findViewById(R.id.contactName);
        TextView contactPhoneNumber = findViewById(R.id.phoneNumber);
        TextView contactEmail = findViewById(R.id.email);

        // Get the data passed from MainActivity
        String name = getIntent().getStringExtra("NAME");
        String phone = getIntent().getStringExtra("PHONE");
        String email = getIntent().getStringExtra("EMAIL");

        contactName.setText(name);
        contactPhoneNumber.setText(phone);
        contactEmail.setText(email);
    }
}
