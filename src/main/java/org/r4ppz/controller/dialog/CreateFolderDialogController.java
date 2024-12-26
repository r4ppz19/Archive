package org.r4ppz.controller.dialog;

import org.r4ppz.service.ValidateFolderName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateFolderDialogController {
    
    @FXML
    private TextField folderNameTextField;

    @FXML
    private Button createButton;

    @FXML
    public void handleCreateButtonAction(ActionEvent actionEvent) throws Exception {
        String folderName = folderNameTextField.getText().trim();
        ValidateFolderName.validateFolderName(actionEvent, folderName);
    }

    @FXML
    public void handleFolderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        String folderName = folderNameTextField.getText().trim();
        ValidateFolderName.validateFolderName(actionEvent, folderName);
    }
}
