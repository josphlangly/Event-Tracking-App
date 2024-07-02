package com.example.langleyeventtracking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.langleyeventtracking.AppUtility;
import com.example.langleyeventtracking.R;
import com.example.langleyeventtracking.data.EventAppRepository;
import com.example.langleyeventtracking.data.models.User;

/**
 * Register Activity
 * <p>
 * The RegisterActivity class handles the user registration process. It includes:
 * - Input fields for user details (name, birthday, email, password).
 * - Validation of user inputs.
 * - Interaction with the repository to check for existing users and insert new users.
 * - Navigation to the LoginActivity upon successful registration.
 * <p>
 * This class is used for creating new user accounts.
 *
 * @author Joseph Langley
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, birthdayEditText, emailEditText, passwordEditText, phoneEditText; // EditTexts for user details
    private EventAppRepository repository; // Repository for database interactions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_creation); // Set the layout for the activity

        // Initialize EditTexts and Button
        nameEditText = findViewById(R.id.name);
        birthdayEditText = findViewById(R.id.birthday);
        phoneEditText = findViewById(R.id.phone);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        // Buttons to create account or return to login as local variables
        Button createAccountButton = findViewById(R.id.createAccountButton);
        Button returnLoginButton = findViewById(R.id.returnLogin);

        // Initialize the repository
        repository = new EventAppRepository(getApplication());

        // Set the date picker for birthday input
        birthdayEditText.setOnClickListener(v -> AppUtility.showDatePickerDialog(RegisterActivity.this, birthdayEditText));

        // Set the create account button click listener
        createAccountButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String birthday = birthdayEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!AppUtility.validateText(this, nameEditText, birthdayEditText, phoneEditText, emailEditText, passwordEditText)) {
                return;
            }
            verifyEmailAndRegister(name, birthday, phone, email, password);
        });

        // Set the register button click listener to navigate back to LoginActivity
        returnLoginButton.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    /**
     * Verifies email looking at live data from database to ensure user doesn't already exist
     * Proceeds to register user if verified new user.
     */
    private void verifyEmailAndRegister(String name, String birthday, String phone, String email, String password) {
        LiveData<User> userLiveData = repository.getUserByEmail(email);
        userLiveData.observe(RegisterActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    emailEditText.setError("Email already in system");
                } else {
                    registerUser(name, birthday, phone, email, password);
                }
                userLiveData.removeObserver(this);
            }
        });
    }

    /**
     * Registers new user into database and returns to login screen.
     */
    private void registerUser(String name, String birthday, String phone, String email, String password) {
        // Create a new user object
        User newUser = new User();
        newUser.setName(name);
        newUser.setBirthday(birthday);
        newUser.setPhone(phone);
        newUser.setEmail(email);
        newUser.setPassword(password);

        // Insert the new user into the repository
        repository.insertUser(newUser);

        AppUtility.showToast(this, "Registration Successful");
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
