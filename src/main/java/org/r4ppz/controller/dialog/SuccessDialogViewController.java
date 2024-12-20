package org.r4ppz.controller.dialog;

import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SuccessDialogViewController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        // Get the state and close it
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        
    }
}
