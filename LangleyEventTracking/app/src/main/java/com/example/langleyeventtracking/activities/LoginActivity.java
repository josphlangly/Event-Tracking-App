package com.example.langleyeventtracking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.langleyeventtracking.data.EventAppRepository;
import com.example.langleyeventtracking.R;

/**
 * Login Activity
 * <p>
 * The LoginActivity class handles the login functionality. It includes:
 * - Verifying that the email and password fields are not empty.
 * - Authenticating the user input with the records in the database.
 * - Navigating to the RegisterActivity for new users.
 * - Displaying appropriate messages for successful or failed login attempts.
 * <p>
 * This class serves as the starting point when the application is launched.
 *
 * @author Joseph Langley
 */
public class LoginActivity extends AppCompatActivity {

    private EventAppRepository repository; // Repository for database interactions
    private EditText emailEditText, passwordEditText; // EditTexts for user input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen); // Set the layout for the activity

        // Initialize EditTexts and Buttons
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        // Buttons for login and register actions declared as local variables
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        // Initialize the repository
        repository = new EventAppRepository(getApplication());

        // Set the login button click listener
        loginButton.setOnClickListener(v -> {
            // Get the email and password from the EditTexts
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Check if the email and password are not empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Authenticate the user
            repository.getUser(email, password).observe(this, user -> {
                if (user != null) {
                    // If user is authenticated, show success message
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    // Extract the first name from the user's full name
                    String firstName = user.getName().split(" ")[0];

                    // Create an intent to navigate to EventActivity
                    Intent intent = new Intent(LoginActivity.this, EventActivity.class);
                    intent.putExtra("user id", user.getId());
                    intent.putExtra("first name", firstName);
                    intent.putExtra("birthday", user.getBirthday());
                    startActivity(intent); // Start EventActivity
                    finish(); // Finish LoginActivity
                } else {
                    // If authentication fails, show error message
                    Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Set the register button click listener to navigate to RegisterActivity
        registerButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}
