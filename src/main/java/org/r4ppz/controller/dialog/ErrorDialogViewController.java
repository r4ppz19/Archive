package org.r4ppz.controller.dialog;

import org.r4ppz.util.StageGetter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ErrorDialogViewController {

    @FXML
    public void okButtonAction(ActionEvent actionEvent) {
        // Get the stage and close it
        Stage stage = StageGetter.getCurrentStage(actionEvent);
        stage.close();
    }
}
