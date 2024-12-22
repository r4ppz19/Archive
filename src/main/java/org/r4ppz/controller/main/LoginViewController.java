package org.r4ppz.controller.main;

import org.r4ppz.model.DefaultUser;
import org.r4ppz.model.UserModel;
import org.r4ppz.view.dialog.ErrorDialogView;
import org.r4ppz.view.dialog.SuccessDialogView;
import org.r4ppz.view.main.MainView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {
    private final ErrorDialogView errorDialogView = ErrorDialogView.getInstance();
    private final SuccessDialogView signUpDialogView = SuccessDialogView.getInstance();
    private final DefaultUser defaultUser = DefaultUser.getInstancDefaultUser();
    private final MainView mainView = MainView.getInstanceMainView();


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
            Stage currentStage = getCurrentStage(actionEvent);
            currentStage.close();

            // Show the main view
            mainView.showMainView();
        } else {
            Stage ownerStage = getCurrentStage(actionEvent);
            errorDialogView.showErrorDialog(ownerStage);
        }

    }

    @FXML
    public void signUpAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = getCurrentStage(actionEvent);

        if (usernameTextField.getText() != null && !usernameTextField.getText().isEmpty()
                && passwordTextField.getText() != null && !passwordTextField.getText().isEmpty()) {

            userModel.setUsername(usernameTextField.getText());
            userModel.setPassword(passwordTextField.getText());

            signUpDialogView.showSuccessDialog(ownerStage);

            usernameTextField.clear();
            passwordTextField.clear();
        } else {
            errorDialogView.showErrorDialog(ownerStage);
        }

    }

    public Stage getCurrentStage(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        return currentStage;
    }

}
