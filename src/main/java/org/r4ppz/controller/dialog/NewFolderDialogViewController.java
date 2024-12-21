package org.r4ppz.controller.dialog;

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
        validateButtonAction(actionEvent);
    }

    @FXML
    public void folderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        validateButtonAction(actionEvent);
    }

    private void validateButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()) .getScene().getWindow();
        String folderName = folderNameTextField.getText();

        if (folderName != null  && !folderName.isEmpty()) {
            handleFile.createFolder(folderName);
            mainViewController.refresh();

            createButton.getScene().getWindow().hide();
        } else {
            errorDialogView.showErrorDialogView(ownerStage);
        }
    }
}
