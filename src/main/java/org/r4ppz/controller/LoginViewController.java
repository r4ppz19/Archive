package org.r4ppz.controller;

import org.r4ppz.model.DefaultUser;
import org.r4ppz.model.UserModel;
import org.r4ppz.view.ErrorAlertView;
import org.r4ppz.view.MainView;
import org.r4ppz.view.SuccessAlertView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {
    private final ErrorAlertView errorAlertView = ErrorAlertView.getInstancErrorAlertView();
    private final SuccessAlertView signUpAlertView = SuccessAlertView.getInstanSuccessAlertView();
    private final DefaultUser defaultUser = DefaultUser.getInstancDefaultUser();

    private final UserModel userModel = new UserModel();

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    public void passwordTextFieldAction(ActionEvent actionEvent) throws Exception {
        signInAction(actionEvent);
    }

    @FXML
    public void usernameTextFieldAction(ActionEvent actionEvent) throws Exception {
        signInAction(actionEvent);
    }

    @FXML
    public void signInAction(ActionEvent actionEvent) throws Exception {

        if (usernameTextField.getText().equals(userModel.getUsername())
                && passwordTextField.getText().equals(userModel.getPassword())
                || usernameTextField.getText().equals(defaultUser.getUsername())
                        && passwordTextField.getText().equals(defaultUser.getPassword())) {

            // Get the state and close it
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            // Show the main view
            MainView mainView = MainView.getInstancMainView();
            mainView.showMainViewV2();
        } else {
            errorAlertView.showErrorAlertView();
        }

    }

    @FXML
    public void signUpAction(ActionEvent actionEvent) throws Exception {
        if (usernameTextField.getText() != null && !usernameTextField.getText().isEmpty()
                && passwordTextField.getText() != null && !passwordTextField.getText().isEmpty()) {

            userModel.setUsername(usernameTextField.getText());
            userModel.setPassword(passwordTextField.getText());
            signUpAlertView.showSuccessAlertView();

            usernameTextField.clear();
            passwordTextField.clear();
        } else {
            errorAlertView.showErrorAlertView();
        }

    }

}
