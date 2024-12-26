package org.r4ppz.controller.dialog;

import org.r4ppz.service.FileHandler;
import org.r4ppz.service.ValidateFolderName;
import org.r4ppz.view.dialog.ErrorDialogView;
import org.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateFolderDialogController {

    private SuccessDialogView successDialogView = SuccessDialogView.getInstance();
    private ErrorDialogView errorDialogView = ErrorDialogView.getInstance();
    private FileHandler fileHandler = FileHandler.getInstance(successDialogView);
    private ValidateFolderName validateFolderName = ValidateFolderName.getInstance(fileHandler, errorDialogView);
    
    @FXML
    private TextField folderNameTextField;

    @FXML
    private Button createButton;

    @FXML
    public void handleCreateButtonAction(ActionEvent actionEvent) throws Exception {
        String folderName = folderNameTextField.getText().trim();
        validateFolderName.validateFolderName(actionEvent, folderName);
    }

    @FXML
    public void handleFolderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        String folderName = folderNameTextField.getText().trim();
        validateFolderName.validateFolderName(actionEvent, folderName);
    }
}
