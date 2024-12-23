package org.r4ppz.controller.dialog;

import java.util.List;
import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.service.FileHandler;
import org.r4ppz.service.ValidateFolderName;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.ErrorDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewFolderDialogViewController {
    
    @FXML
    private TextField folderNameTextField;

    @FXML
    private Button createButton;

    @FXML
    public void handleCreateButtonAction(ActionEvent actionEvent) throws Exception {
        String folderName = createButton.getText().trim();
        ValidateFolderName.validateFolderName(actionEvent, folderName);
    }

    @FXML
    public void handleFolderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        String folderName = createButton.getText().trim();
        ValidateFolderName.validateFolderName(actionEvent, folderName);
    }
}
