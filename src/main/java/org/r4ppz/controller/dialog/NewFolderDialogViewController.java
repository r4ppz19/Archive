package org.r4ppz.controller.dialog;

import java.util.Arrays;
import java.util.List;

import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.util.HandleFile;
import org.r4ppz.view.dialog.ErrorDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class NewFolderDialogViewController {
    private static final List<Character> INVALID_CHARACTERS = Arrays.asList('*', '?', '<', '>', '|', ':', '"', '\\', '/');

    private final HandleFile handleFile = HandleFile.getInstanceHandleFile();
    private final ErrorDialogView errorDialogView = ErrorDialogView.getInstancErrorAlertView();

    private MainViewController mainViewController;

    public void setMainViewV2Controller(MainViewController mainViewController) {
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

    private void validateButton(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()) .getScene().getWindow();
        String folderName = folderNameTextField.getText();

        if (folderName != null  && !folderName.isEmpty() && !containsInvalidCharacters(folderName)) {
            handleFile.createFolder(folderName);
            mainViewController.refresh();

            createButton.getScene().getWindow().hide();
        } else {
            errorDialogView.showErrorDialogView(ownerStage);
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
}
