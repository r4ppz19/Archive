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
    
    public boolean isValidSignInCredentials(@NonNull String username, String password) {
        return (username.equals(userModel.getUsername()) && password.equals(userModel.getPassword()))
                || (username.equals(defaultUser.getUsername()) && password.equals(defaultUser.getPassword()));
    }

    public boolean isValidSignUpCredentials(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    public void setCredentials(String username, String password) {
        userModel.setUsername(username);
        userModel.setPassword(password);
    }
}
