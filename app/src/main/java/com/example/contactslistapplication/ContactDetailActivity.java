package com.example.contactslistapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView phoneNumberTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        nameTextView = findViewById(R.id.nameTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
        emailTextView = findViewById(R.id.nameTextView);

        String name = getIntent().getStringExtra("name");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String email = getIntent().getStringExtra("email");

        nameTextView.setText(name);
        phoneNumberTextView.setText(phoneNumber);
        emailTextView.setText(email);
    }
}