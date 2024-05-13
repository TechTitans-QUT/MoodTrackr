package com.example.moodtrackr.model;
/**
 * A model class representing a user with a first name, last name, email, and password.
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    /**
     * Constructs a new User with the specified first name, last name, email, and password.
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param email The email of the user
     * @param password The password of the user
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}