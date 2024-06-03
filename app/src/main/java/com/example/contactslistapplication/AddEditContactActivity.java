package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private Button saveButton;

    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        nameEditText = findViewById(R.id.firstName + R.id.lastName);
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

        Intent data = new Intent();
        data.putExtra("name", name);
        data.putExtra("phoneNumber", phoneNumber);
        data.putExtra("email", email);
        data.putExtra("position", position);

        setResult(RESULT_OK, data);
        finish();
    }
}
