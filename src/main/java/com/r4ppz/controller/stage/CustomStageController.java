package com.r4ppz.controller.stage;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomStageController {

    private double xOffSet = 0;
    private double yOffSet = 0;

    Stage stage;

    @FXML
    private StackPane root;

    @FXML
    private HBox titleBar;

    @FXML
    private void closeActionButton() {
        stage.close();
    }

    @FXML
    private void initialize() {

    }

    public void setStage(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        this.stage = stage;

        // Record offset when mouse is pressed
        titleBar.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });

        // Drag the stage when mouse is dragged
        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });

    }

    public void setContent(Parent content) {
        root.getChildren().add(content);
        StackPane.setAlignment(content, Pos.CENTER);
    }
}
