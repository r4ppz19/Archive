package com.r4ppz.controller.dialog;

import com.r4ppz.service.FileHandler;
import com.r4ppz.service.ValidateFolderName;
import com.r4ppz.view.dialog.ErrorDialogView;
import com.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateFolderDialogController {

    private final SuccessDialogView successDialogView = SuccessDialogView.getInstance();
    private final ErrorDialogView errorDialogView = ErrorDialogView.getInstance();
    private final FileHandler fileHandler = FileHandler.getInstance(successDialogView);
    private final ValidateFolderName validateFolderName = ValidateFolderName.getInstance(fileHandler, errorDialogView);
    
    @FXML
    private TextField folderNameTextField;

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