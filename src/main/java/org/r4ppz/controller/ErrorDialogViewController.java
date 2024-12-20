package org.r4ppz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ErrorDialogViewController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        // Get the stage and close it
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
