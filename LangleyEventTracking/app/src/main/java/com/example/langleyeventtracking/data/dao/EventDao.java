package com.example.langleyeventtracking.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.langleyeventtracking.data.models.Event;

import java.util.List;

/**
 * Event Data Access Object (DAO)
 * <p>
 * The EventDao interface provides methods for interacting with the events table in the database.
 * It includes:
 * - Inserting new events.
 * - Retrieving all events.
 * - Retrieving events for a specific user.
 * - Deleting events.
 * - Updating events.
 * <p>
 * This interface is used by Room to generate the necessary code for database operations.
 *
 * @author Joseph Langley
 */
@Dao
public interface EventDao {

    /**
     * Inserts a new event into the database.
     *
     * @param event The event to insert.
     */
    @Insert
    void insertEvent(Event event);

    /**
     * Retrieves all events from the database, ordered by date in ascending order.
     *
     * @return A LiveData list of all events.
     */
    @Query("SELECT * FROM events ORDER BY date ASC")
    LiveData<List<Event>> getAllEvents();

    /**
     * Retrieves events for a specific user, ordered by date in ascending order.
     *
     * @param userId The ID of the user.
     * @return A LiveData list of events for the specified user.
     */
    @Query("SELECT * FROM events WHERE `user id` = :userId ORDER BY date ASC")
    LiveData<List<Event>> getEventsForUser(int userId);

    /**
     * Deletes an event from the database.
     *
     * @param event The event to delete.
     */
    @Delete
    void deleteEvent(Event event);

    /**
     * Updates an existing event in the database.
     *
     * @param event The event to update.
     */
    @Update
    void updateEvent(Event event);
}
