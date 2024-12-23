package org.r4ppz.service;

import org.r4ppz.model.DefaultUser;
import org.r4ppz.model.UserModel;

public class ValidateCredentials {
    UserModel userModel = new UserModel();
    DefaultUser defaultUser = DefaultUser.getInstanc();
    
    public boolean isValidSignInCredentials(String username, String password) {
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
