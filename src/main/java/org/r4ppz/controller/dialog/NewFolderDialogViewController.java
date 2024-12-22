package org.r4ppz.controller.dialog;

import java.util.List;
import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.util.FileHandler;
import org.r4ppz.view.dialog.ErrorDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class NewFolderDialogViewController {

    private static final List<Character> INVALID_CHARACTERS = List.of('*', '?', '<', '>', '|', ':', '"', '\\', '/');

    private final FileHandler fileHandler = FileHandler.getInstance();
    private final ErrorDialogView errorDialogView = ErrorDialogView.getInstance();

    private MainViewController mainViewController;

    public void setSecondaryController(MainViewController secondaryController) {
        this.mainViewController = secondaryController;
    }

    @FXML
    private TextField folderNameTextField;

    @FXML
    private Button createButton;

    @FXML
    public void handleCreateButtonAction(ActionEvent actionEvent) throws Exception {
        validateFolderName(actionEvent);
    }

    @FXML
    public void handleFolderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        validateFolderName(actionEvent);
    }

    private void validateFolderName(ActionEvent actionEvent) throws Exception {
        String folderName = folderNameTextField.getText().trim(); // Trim any leading or trailing whitespace

        // Validate folder name and create the folder if valid
        if (isFolderNameValid(folderName)) {
            fileHandler.createFolder(folderName, fileHandler.getDefaultUploadsPath());
            mainViewController.refreshVbox(mainViewController.getLeftPanelVBox());
            closeDialog(actionEvent);
        } else {
            showErrorDialog(actionEvent);
        }
    }

    private boolean isFolderNameValid(String folderName) {
        return folderName != null && !folderName.isEmpty() && folderName.chars().noneMatch(c -> INVALID_CHARACTERS.contains((char) c));
    }

    private void closeDialog(ActionEvent actionEvent) {
        getCurrentStage(actionEvent).close();
    }

    private void showErrorDialog(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = getCurrentStage(actionEvent);
        errorDialogView.showErrorDialog(ownerStage);
    }

    private Stage getCurrentStage(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
