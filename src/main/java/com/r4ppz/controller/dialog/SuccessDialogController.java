package com.r4ppz.controller.dialog;

import javafx.stage.Stage;

import com.r4ppz.util.StageGetter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SuccessDialogController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        Stage stage = StageGetter.getCurrentStage(actionEvent);
        stage.hide();
        
    }
}
