package org.r4ppz.controller.dialog;

import java.util.Arrays;
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
    private static final List<Character> INVALID_CHARACTERS = Arrays.asList('*', '?', '<', '>', '|', ':', '"', '\\', '/');

    private final FileHandler fileHandler = FileHandler.getInstance();
    private final ErrorDialogView errorDialogView = ErrorDialogView.getInstance();

    private MainViewController mainViewController;

    public void setSecondaryController(MainViewController SecondaryController) {
        this.mainViewController = SecondaryController;
    }
    
    @FXML
    private TextField folderNameTextField;

    @FXML
    private Button createButton;

    @FXML
    public void createButtonAction(ActionEvent actionEvent) throws Exception {
        validateButton(actionEvent);
    }

    @FXML
    public void folderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        validateButton(actionEvent);
    }

    // Check if the textField have valid folder names
    private void validateButton(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = getCurrentStage(actionEvent);
        String folderName = folderNameTextField.getText();

        // Check if the textField is empty
        if (folderName != null  && !folderName.isEmpty() && !containsInvalidCharacters(folderName)) {
            fileHandler.createFolder(folderName, fileHandler.getdefaultUploadsPath());
            mainViewController.vboxRefresher(mainViewController.getLeftPanelVBox());

            createButton.getScene().getWindow().hide();
        } else {
            errorDialogView.showErrorDialog(ownerStage);
        }
    }

    private boolean containsInvalidCharacters(String folderName) {
        for (char c : folderName.toCharArray()) {
            if (INVALID_CHARACTERS.contains(c)) {
                return true;
            }
        }
        return false;
    }

    private Stage getCurrentStage(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}