package org.r4ppz.model;

public class ValidateCredentials {
    private static ValidateCredentials validateCredentials;
    private final UserModel userModel;

    // Dependency
    private ValidateCredentials(UserModel userModel) {
        this.userModel = userModel;
    }

    public static ValidateCredentials getInstance(UserModel userModel) {
        if (validateCredentials == null) {
            validateCredentials = new ValidateCredentials(userModel);
        }
        return validateCredentials;
    }

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
