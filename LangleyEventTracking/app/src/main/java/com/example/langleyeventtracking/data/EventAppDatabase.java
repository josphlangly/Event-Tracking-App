package com.example.langleyeventtracking.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.langleyeventtracking.data.dao.EventDao;
import com.example.langleyeventtracking.data.dao.UserDao;
import com.example.langleyeventtracking.data.models.Event;
import com.example.langleyeventtracking.data.models.User;

/**
 * EventAppDatabase
 * <p>
 * The EventAppDatabase class is the main database for the application. It includes:
 * - Singleton pattern to ensure only one instance of the database is created.
 * - Abstract methods to get the DAO interfaces.
 * <p>
 * This class is used by Room to create and manage the database.
 *
 * @author Joseph Langley
 */
@Database(entities = {User.class, Event.class}, version = 1, exportSchema = false)
public abstract class EventAppDatabase extends RoomDatabase {

    private static EventAppDatabase instance; // Singleton instance of the database

    /**
     * Gets the singleton instance of the EventAppDatabase.
     *
     * @param context The application context.
     * @return The singleton instance of the EventAppDatabase.
     */
    public static synchronized EventAppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            EventAppDatabase.class, "event_app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    /**
     * Gets the UserDao.
     *
     * @return The UserDao.
     */
    public abstract UserDao userDao();

    /**
     * Gets the EventDao.
     *
     * @return The EventDao.
     */
    public abstract EventDao eventDao();
}
