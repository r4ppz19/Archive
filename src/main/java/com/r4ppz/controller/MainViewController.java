package com.r4ppz.controller;

import com.r4ppz.model.UserModel;
import com.r4ppz.view.SignUpAlert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainViewController {
    private UserModel userModel = new UserModel();
    private SignUpAlert signUpAlert = new SignUpAlert();

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;

    @FXML
    public void signInAction(ActionEvent actionEvent) {
        System.out.println(userModel.getUsername());
        System.out.println(userModel.getPassword());
    }

    @FXML
    public void signUpAction(ActionEvent actionEvent) throws Exception {
        userModel.setUsername(usernameTextField.getText());
        userModel.setPassword(passwordTextField.getText());
        signUpAlert.showSignUpView();
    }
}