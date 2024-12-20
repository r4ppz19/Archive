package org.r4ppz.controller;

import org.r4ppz.util.HandleFile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewFolderDialogViewController {
    private HandleFile handleFile = HandleFile.getInstanceHandleFile();

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
        String folderName = folderNameTextField.getText();
        handleFile.createFolder(folderName);
        mainViewController.refresh();

        createButton.getScene().getWindow().hide();
    }

    @FXML
    public void folderNameTextFieldAction(ActionEvent actionEvent) throws Exception {
        String folderName = folderNameTextField.getText();
        handleFile.createFolder(folderName);
        mainViewController.refresh();


        createButton.getScene().getWindow().hide();
    }
}
