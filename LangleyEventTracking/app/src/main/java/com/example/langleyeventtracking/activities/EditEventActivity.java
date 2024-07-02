package com.example.langleyeventtracking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.langleyeventtracking.AppUtility;
import com.example.langleyeventtracking.R;
import com.example.langleyeventtracking.data.models.Event;
import com.example.langleyeventtracking.viewmodels.EventViewModel;

/**
 * Edit Event Activity
 * <p>
 * The EditEventActivity class handles the creation and editing of events. It includes:
 * - Displaying existing event details for editing.
 * - Saving new events or updating existing ones.
 * - Deleting events.
 * - Navigating back to the EventActivity.
 * <p>
 * This class is used for both adding new events and editing existing ones.
 *
 * @author Joseph Langley
 */
public class EditEventActivity extends AppCompatActivity {

    private EditText titleEditText, dateEditText, descEditText; // EditTexts for event details
    private EventViewModel eventViewModel; // ViewModel for event operations
    private int eventId = -1; // ID of the event being edited, -1 for new events
    private int userId = -1; // ID of the user creating or editing the event

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail); // Set the layout for the activity

        // Initialize EditTexts and Buttons
        titleEditText = findViewById(R.id.event_title_input);
        dateEditText = findViewById(R.id.event_date_input);
        descEditText = findViewById(R.id.event_description_input);

        // Buttons for saving and deleting events declared as local variables
        Button saveButton = findViewById(R.id.save_event_button);
        Button deleteButton = findViewById(R.id.delete_event_button);

        // Initialize the ViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        // Get the intent that started this activity
        Intent intent = getIntent();
        userId = intent.getIntExtra("user id", -1);
        if (intent.hasExtra("id")) {
            // Editing an existing event
            setTitle("Edit Event");
            eventId = intent.getIntExtra("id", -1);
            titleEditText.setText(intent.getStringExtra("title"));
            dateEditText.setText(intent.getStringExtra("date"));
            descEditText.setText(intent.getStringExtra("description"));
        } else {
            // Adding a new event
            setTitle("Add Event");
        }

        // Set the date picker for date input
        dateEditText.setOnClickListener(v -> AppUtility.showDatePickerDialog(EditEventActivity.this, dateEditText));

        // Set the save button click listener
        saveButton.setOnClickListener(v -> {
            // Get event details from EditTexts
            String title = titleEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();
            String description = descEditText.getText().toString().trim();

            // Validate inputs
            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(EditEventActivity.this, "Event must have title and date", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new event object
            Event event = new Event();
            event.setTitle(title);
            event.setDate(date);
            event.setDescription(description);
            event.setUserId(userId);

            // Save or update the event
            if (eventId != -1) {
                // Update existing event
                event.setId(eventId);
                eventViewModel.update(event);
                Toast.makeText(EditEventActivity.this, "Event updated", Toast.LENGTH_SHORT).show();
            } else {
                // Insert new event
                eventViewModel.insert(event);
                Toast.makeText(EditEventActivity.this, "Event created", Toast.LENGTH_SHORT).show();
            }

            // Navigate back to EventActivity
            Intent returnIntent = new Intent(EditEventActivity.this, EventActivity.class);
            returnIntent.putExtra("user id", userId);
            startActivity(returnIntent);
            finish();
        });

        // Set the delete button click listener
        deleteButton.setOnClickListener(v -> {
            if (eventId != -1) {
                // Delete the event
                Event event = new Event(); // Just for deletion, details don't matter
                event.setId(eventId);
                eventViewModel.delete(event);
                Toast.makeText(EditEventActivity.this, "Event deleted", Toast.LENGTH_SHORT).show();
            }
            // Navigate back to EventActivity
            Intent returnIntent = new Intent(EditEventActivity.this, EventActivity.class);
            returnIntent.putExtra("user id", userId);
            startActivity(returnIntent);
            finish();
        });
    }
}
