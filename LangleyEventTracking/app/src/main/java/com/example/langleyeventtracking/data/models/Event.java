package com.example.langleyeventtracking.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Event Entity
 * <p>
 * The Event class represents an event in the database. It includes:
 * - An auto-generated primary key ID.
 * - A title for the event.
 * - A date for the event.
 * - A description of the event.
 * - The user ID associated with the event.
 * <p>
 * This class is used by Room to create and manage the events table.
 *
 * @author Joseph Langley
 */
@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id; // Primary key for the event

    @ColumnInfo(name = "title")
    private String title; // Title of the event

    @ColumnInfo(name = "date")
    private String date; // Date of the event

    @ColumnInfo(name = "description")
    private String description; // Description of the event

    @ColumnInfo(name = "user id")
    private int userId; // User ID associated with the event

    /**
     * Gets the event ID.
     *
     * @return The event ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the event ID.
     *
     * @param id The event ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the event.
     *
     * @return The event title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     *
     * @param title The event title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date of the event.
     *
     * @return The event date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date The event date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the description of the event.
     *
     * @return The event description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     *
     * @param description The event description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the user ID associated with the event.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the event.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                userId == event.userId &&
                Objects.equals(title, event.title) &&
                Objects.equals(date, event.date) &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, description, userId);
    }
}
