package org.r4ppz.controller.main;

import org.r4ppz.model.DefaultUser;
import org.r4ppz.model.UserModel;
import org.r4ppz.util.StageGetter;
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
    public void handleUsernameTextFieldAction(ActionEvent actionEvent) throws Exception{
        handleSignInAction(actionEvent);
    }

    @FXML
    public void handlePasswordTextFieldAction(ActionEvent actionEvent) throws Exception {
        handleSignInAction(actionEvent);
    }

    @FXML
    public void handleSignInAction(ActionEvent actionEvent) throws Exception {
        if (isValidCredentials()) {
            closeCurrentStage(actionEvent);
            mainView.showMainView();
        } else {
            showErrorDialog(actionEvent);
        }
    }

    @FXML
    public void handleSignUpAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (isValidSignUpCredentials(username, password)) {
            userModel.setUsername(username);
            userModel.setPassword(password);

            signUpDialogView.showSuccessDialog(ownerStage);
            clearInputFields();
        } else {
            errorDialogView.showErrorDialog(ownerStage);
        }
    }

    private boolean isValidCredentials() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        return (username.equals(userModel.getUsername()) && password.equals(userModel.getPassword()))
                || (username.equals(defaultUser.getUsername()) && password.equals(defaultUser.getPassword()));
    }

    private boolean isValidSignUpCredentials(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    private void clearInputFields() {
        usernameTextField.clear();
        passwordTextField.clear();
    }

    private void showErrorDialog(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        errorDialogView.showErrorDialog(ownerStage);
    }

    private void closeCurrentStage(ActionEvent actionEvent) {
        StageGetter.getCurrentStage(actionEvent).close();
    }
}
