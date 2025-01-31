package com.r4ppz.service;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.r4ppz.model.DefaultUser;
import com.r4ppz.model.UserModel;

public class ValidateCredentials {
    private static ValidateCredentials validateCredentials;
    private final UserModel userModel;

    // Dependency
    private ValidateCredentials(UserModel userModel) {
        this.userModel = userModel;
    }

    // Singleton
    public static ValidateCredentials getInstance(UserModel userModel) {
        if (validateCredentials == null) {
            validateCredentials = new ValidateCredentials(userModel);
        }
        return validateCredentials;
    }

    DefaultUser defaultUser = DefaultUser.getInstance();
    
    /**
     * Validates the sign-in credentials by comparing the provided username and password
     * with the stored user credentials.
     *
     * @param username the username to be validated, must not be null
     * @param password the password to be validated
     * @return true if the provided username and password match either the userModel or defaultUser credentials, false otherwise
     */
    public boolean isValidSignInCredentials(@NonNull String username, String password) {
        return (username.equals(userModel.getUsername()) && password.equals(userModel.getPassword()))
                || (username.equals(defaultUser.getUsername()) && password.equals(defaultUser.getPassword()));
    }

    /**
     * Validates the sign-up credentials by checking if the username and password are not null or empty.
     *
     * @param username the username to be validated
     * @param password the password to be validated
     * @return true if both username and password are not null and not empty, false otherwise
     */
    public boolean isValidSignUpCredentials(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    /**
     * Sets the credentials for the user.
     *
     * @param username the username to be set
     * @param password the password to be set
     */
    public void setCredentials(String username, String password) {
        userModel.setUsername(username);
        userModel.setPassword(password);
    }
}
