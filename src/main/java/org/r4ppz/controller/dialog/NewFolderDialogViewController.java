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

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
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

    /**
     * Validates the folder name entered by the user and creates a new folder if the name is valid.
     * If the folder name is invalid, an error dialog is displayed.
     *
     * @param actionEvent the action event triggered by the button click
     * @throws Exception if an error occurs during folder creation
     */
    private void validateButton(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()) .getScene().getWindow();
        String folderName = folderNameTextField.getText();

        if (folderName != null  && !folderName.isEmpty() && !containsInvalidCharacters(folderName)) {
            fileHandler.createFolder(folderName);
            mainViewController.vboxRefresher(mainViewController.getLeftPanelVBox());

            createButton.getScene().getWindow().hide();
        } else {
            errorDialogView.showErrorDialog(ownerStage);
        }
    }

    /**
     * Checks if the given folder name contains any invalid characters.
     *
     * @param folderName the name of the folder to check
     * @return true if the folder name contains any invalid characters, false otherwise
     */
    private boolean containsInvalidCharacters(String folderName) {
        for (char c : folderName.toCharArray()) {
            if (INVALID_CHARACTERS.contains(c)) {
                return true;
            }
        }
        return false;
    }
}
