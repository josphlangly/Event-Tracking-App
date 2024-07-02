package com.example.langleyeventtracking.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.langleyeventtracking.R;
import com.example.langleyeventtracking.adapter.EventAdapter;
import com.example.langleyeventtracking.data.models.Event;
import com.example.langleyeventtracking.fragments.SmsNotifyFragment;
import com.example.langleyeventtracking.viewmodels.EventViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Event Activity
 * <p>
 * The EventActivity class handles the display and management of events. It includes:
 * - Displaying a welcome message with the user's first name.
 * - Displaying a list of events for the logged-in user.
 * - Adding, editing, and deleting events.
 * - Handling first-time login logic, including showing the SMS notification prompt and creating a birthday event.
 * <p>
 * This class serves as the main screen for users after logging in.
 *
 * @author Joseph
 */
public class EventActivity extends AppCompatActivity {

    private final EventAdapter eventAdapter = new EventAdapter(this); // Adapter for the RecyclerView
    private EventViewModel eventViewModel; // ViewModel for event operations
    private int userId; // ID of the logged-in user
    private String firstName; // First name of the logged-in user
    private String birthday; // Birthday of the logged-in user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity); // Set the layout for the activity

        // RecyclerView for displaying events
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        // Button for adding new events
        Button addEventButton = findViewById(R.id.add_event_button);

        // Set layout manager and adapter for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventAdapter);

        // Initialize the ViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        // Get user details from the intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("user id", -1);
        firstName = intent.getStringExtra("first name");
        birthday = intent.getStringExtra("birthday");

        // Set welcome message as title
        setTitle("Welcome " + firstName + "!");

        // Observe events for the user
        eventViewModel.getEventsForUser(userId).observe(this, eventAdapter::submitList);

        // Set click listener for the add event button
        addEventButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(EventActivity.this, EditEventActivity.class);
            intent1.putExtra("user id", userId);
            startActivity(intent1);
        });

        // Set item click listener for the event adapter
        eventAdapter.setOnItemClickListener(event -> {
            Intent i = new Intent(EventActivity.this, EditEventActivity.class);
            i.putExtra("id", event.getId());
            i.putExtra("title", event.getTitle());
            i.putExtra("date", event.getDate());
            i.putExtra("description", event.getDescription());
            i.putExtra("user id", event.getUserId());
            startActivity(i);
        });

        // Check if this is the user's first login
        checkFirstLogin();
    }

    /**
     * Checks if this is the user's first login and performs necessary actions.
     * Shows the SMS notification prompt and creates a birthday event if it's the first login.
     */
    private void checkFirstLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.langleyeventtracking", MODE_PRIVATE);
        boolean isFirstLogin = preferences.getBoolean("isFirstLogin_" + userId, true);

        if (isFirstLogin) {
            // Show SMS notification prompt
            SmsNotifyFragment dialogFragment = new SmsNotifyFragment();
            dialogFragment.show(getSupportFragmentManager(), "SmsPromptDialogFragment");

            // Create a birthday event
            Event birthdayEvent = new Event();
            birthdayEvent.setTitle(firstName + "'s Birthday");
            birthdayEvent.setDate(createBirthday(birthday));
            birthdayEvent.setDescription("Celebrate Myself!");
            birthdayEvent.setUserId(userId);
            eventViewModel.insert(birthdayEvent);

            // Update shared preferences
            preferences.edit().putBoolean("isFirstLogin_" + userId, false).apply();
        }
    }

    /**
     * Creates a birthday event for user if it's their first login.
     * Checks for birthday already having passed or not for current year.
     */
    private String createBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
        Calendar birthdayCalendar = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        try {
            // Parse the birthday string
            birthdayCalendar.setTime(sdf.parse(birthday));

            // Set the birthday year to this year
            birthdayCalendar.set(Calendar.YEAR, today.get(Calendar.YEAR));

            // If the birthday has already passed this year, set it for next year
            if (birthdayCalendar.before(today)) {
                birthdayCalendar.add(Calendar.YEAR, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sdf.format(birthdayCalendar.getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
