package com.example.contactslistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditContactActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, phoneNumberEditText, emailEditText;
    private Button saveButton;
    private ImageView avatarImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        // Initialize views
        avatarImageView = findViewById(R.id.avatar);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.email);
        saveButton = findViewById(R.id.saveButton);

        // Setup save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        // Extract text from EditText fields
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        // Validate the input
        if (validateInput(firstName, lastName, phoneNumber, email)) {
            // Prepare data intent
            Intent data = new Intent();
            data.putExtra("firstName", firstName);
            data.putExtra("lastName", lastName);
            data.putExtra("phoneNumber", phoneNumber);
            data.putExtra("email", email);
            setResult(RESULT_OK, data);
            finish();
        } else {
            // Show error message or alert
            firstNameEditText.setError("Please check the fields, all fields are required.");
        }
    }

    private boolean validateInput(String firstName, String lastName, String phone, String email) {
        // Simple validation: check if any field is empty
        return !(firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty());
    }
}
