package com.example.langleyeventtracking.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * User Entity
 * <p>
 * The User class represents a user in the database. It includes:
 * - A primary key ID.
 * - The user's name.
 * - The user's birthday.
 * - The user's email.
 * - The user's password.
 * <p>
 * This class is used by Room to create and manage the users table.
 *
 * @author Joseph Langley
 */
@Entity(tableName = "users")
public class User {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id; // Primary key for the user

    @ColumnInfo(name = "name")
    private String name; // User's name

    @ColumnInfo(name = "birthday")
    private String birthday; // User's birthday

    @ColumnInfo(name = "phone")
    private String phone; // User's phone number

    @ColumnInfo(name = "email")
    private String email; // User's email

    @ColumnInfo(name = "password")
    private String password; // User's password

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id The user ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's name.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name The user's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's birthday.
     *
     * @return The user's birthday.
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Sets the user's birthday.
     *
     * @param birthday The user's birthday to set.
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the user's email.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email The user's email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
