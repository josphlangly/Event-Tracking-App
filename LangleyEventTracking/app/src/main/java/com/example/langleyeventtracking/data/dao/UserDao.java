package com.example.langleyeventtracking.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.langleyeventtracking.data.models.User;

import java.util.List;

/**
 * User Data Access Object (DAO)
 * <p>
 * The UserDao interface provides methods for interacting with the users table in the database.
 * It includes:
 * - Inserting new users.
 * - Retrieving users by email and password.
 * - Retrieving all users.
 * <p>
 * This interface is used by Room to generate the necessary code for database operations.
 *
 * @author Joseph Langley
 */
@Dao
public interface UserDao {

    /**
     * Inserts a new user into the database.
     *
     * @param user The user to insert.
     */
    @Insert
    void insertUser(User user);

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user.
     * @return A LiveData object containing the user with the specified email.
     */
    @Query("SELECT * FROM users WHERE email = :email")
    LiveData<User> getUserByEmail(String email);

    /**
     * Retrieves a user by email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A LiveData object containing the user with the specified email and password.
     */
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    LiveData<User> getUser(String email, String password);

    /**
     * Retrieves all users from the database, ordered by ID in descending order.
     *
     * @return A LiveData list of all users.
     */
    @Query("SELECT * FROM users ORDER BY id DESC")
    LiveData<List<User>> getAllUsers();
}
