package com.example.langleyeventtracking.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.langleyeventtracking.data.models.Event;
import com.example.langleyeventtracking.data.EventAppRepository;

import java.util.List;

/**
 * Event ViewModel
 * <p>
 * The EventViewModel class provides data to the UI and acts as a communication center between the Repository and the UI.
 * It includes:
 * - Methods for interacting with event data.
 * - A MutableLiveData object for managing SMS notifications.
 * <p>
 * This class is used to manage UI-related data in a lifecycle-conscious way.
 *
 * @author Joseph Langley
 */
public class EventViewModel extends AndroidViewModel {

    private final EventAppRepository repository; // Repository for data operations
    private final MutableLiveData<Boolean> smsNotificationsEnabled = new MutableLiveData<>(); // LiveData for SMS notifications

    /**
     * Constructor for EventViewModel.
     *
     * @param application The application context.
     */
    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventAppRepository(application);
    }

    /**
     * Gets the list of events for a specific user.
     *
     * @param userId The ID of the user.
     * @return A LiveData object containing the list of events.
     */
    public LiveData<List<Event>> getEventsForUser(int userId) {
        return repository.getEventsForUser(userId);
    }

    /**
     * Inserts a new event.
     *
     * @param event The event to insert.
     */
    public void insert(Event event) {
        repository.insertEvent(event);
    }

    /**
     * Updates an existing event.
     *
     * @param event The event to update.
     */
    public void update(Event event) {
        repository.updateEvent(event);
    }

    /**
     * Deletes an event.
     *
     * @param event The event to delete.
     */
    public void delete(Event event) {
        repository.deleteEvent(event);
    }

    /**
     * Sets the SMS notifications enabled status.
     *
     * @param enabled The new status.
     */
    public void setSmsNotificationsEnabled(boolean enabled) {
        smsNotificationsEnabled.setValue(enabled);
        // Save the preference in shared preferences
        SharedPreferences preferences = getApplication().getSharedPreferences("com.example.langleyeventtracking", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("sms_notifications", enabled).apply();
    }
}
