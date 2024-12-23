package org.r4ppz.controller.dialog;

import javafx.stage.Stage;
import javafx.scene.Node;

import org.r4ppz.util.StageGetter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SuccessDialogViewController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        Stage stage = StageGetter.getCurrentStage(actionEvent);
        stage.close();
        
    }
}
