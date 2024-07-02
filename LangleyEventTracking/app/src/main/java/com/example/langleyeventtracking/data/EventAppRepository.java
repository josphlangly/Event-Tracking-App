package com.example.langleyeventtracking.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.langleyeventtracking.data.dao.EventDao;
import com.example.langleyeventtracking.data.dao.UserDao;
import com.example.langleyeventtracking.data.models.Event;
import com.example.langleyeventtracking.data.models.User;

import java.util.List;

/**
 * EventAppRepository
 * <p>
 * The EventAppRepository class provides a clean API for data access to the rest of the application.
 * It includes:
 * - Methods for interacting with event and user data.
 * - Abstracted database operations to keep the UI code clean.
 * <p>
 * This class is used to manage data operations and provides a way to access the DAOs.
 *
 * @author Joseph Langley
 */
public class EventAppRepository {

    private final UserDao userDao; // Data Access Object for user operations
    private final EventDao eventDao; // Data Access Object for event operations

    /**
     * Constructor for EventAppRepository.
     *
     * @param application The application context.
     */
    public EventAppRepository(Application application) {
        EventAppDatabase db = EventAppDatabase.getInstance(application); // Get the database instance
        userDao = db.userDao();
        eventDao = db.eventDao();
    }

    /**
     * Gets events for a specific user.
     *
     * @param userId The ID of the user.
     * @return A LiveData list of events for the specified user.
     */
    public LiveData<List<Event>> getEventsForUser(int userId) {
        return eventDao.getEventsForUser(userId);
    }

    /**
     * Inserts a new event.
     *
     * @param event The event to insert.
     */
    public void insertEvent(Event event) {
        new Thread(() -> eventDao.insertEvent(event)).start();
    }

    /**
     * Updates an existing event.
     *
     * @param event The event to update.
     */
    public void updateEvent(Event event) {
        new Thread(() -> eventDao.updateEvent(event)).start();
    }

    /**
     * Deletes an event.
     *
     * @param event The event to delete.
     */
    public void deleteEvent(Event event) {
        new Thread(() -> eventDao.deleteEvent(event)).start();
    }

    /**
     * Inserts a new user.
     *
     * @param user The user to insert.
     */
    public void insertUser(User user) {
        new Thread(() -> userDao.insertUser(user)).start();
    }

    /**
     * Gets a user by email.
     *
     * @param email The email of the user.
     * @return A LiveData object containing the user with the specified email.
     */
    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    /**
     * Gets a user by email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A LiveData object containing the user with the specified email and password.
     */
    public LiveData<User> getUser(String email, String password) {
        return userDao.getUser(email, password);
    }
}
