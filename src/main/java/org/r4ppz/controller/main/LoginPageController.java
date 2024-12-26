package org.r4ppz.controller.main;

import org.r4ppz.model.UserModel;
import org.r4ppz.model.ValidateCredentials;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.ErrorDialogView;
import org.r4ppz.view.dialog.SuccessDialogView;
import org.r4ppz.view.main.HomePageView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {
    private UserModel userModel = new UserModel();

    private final ValidateCredentials validateCredentials = ValidateCredentials.getInstance(userModel);
    private final ErrorDialogView errorDialogView = ErrorDialogView.getInstance();
    private final SuccessDialogView signUpDialogView = SuccessDialogView.getInstance();
    private final HomePageView mainView = HomePageView.getInstance();

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
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (validateCredentials.isValidSignInCredentials(username, password)) {
            Stage currentStage = StageGetter.getCurrentStage(actionEvent);
            currentStage.close();
            mainView.showMainView();
        } else {
            errorDialogView.showDialog(ownerStage);
        }
    }

    @FXML
    public void handleSignUpAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (validateCredentials.isValidSignUpCredentials(username, password)) {
            validateCredentials.setCredentials(username, password);

            signUpDialogView.showSuccessDialog(ownerStage);
            passwordTextField.clear();
            usernameTextField.clear();
        } else {
            errorDialogView.showErrorDialog(ownerStage);
        }
    }
}
