package com.r4ppz.controller.stage;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class CustomDialogStageController {
    @FXML
    private StackPane root;

    public void setContent(Parent content) {
        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.CENTER);
    }
}
