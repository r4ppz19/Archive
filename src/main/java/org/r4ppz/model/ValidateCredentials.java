package org.r4ppz.model;

public class ValidateCredentials {
    private UserModel userModel;

    public ValidateCredentials(UserModel userModel) {
        this.userModel = userModel;
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
