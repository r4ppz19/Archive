package com.r4ppz.controller.dialog;

import com.r4ppz.util.StageGetter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ErrorDialogController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        // Get the stage and close it
        Stage stage = StageGetter.getCurrentStage(actionEvent);
        stage.hide();
    }
}
