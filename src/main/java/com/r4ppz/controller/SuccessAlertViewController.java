package com.r4ppz.controller;

import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessAlertViewController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        // Get the state and close it
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        
    }
}
