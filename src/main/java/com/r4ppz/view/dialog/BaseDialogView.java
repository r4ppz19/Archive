package com.r4ppz.view.dialog;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.r4ppz.controller.stage.CustomDialogStageController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

abstract class BaseDialogView {
    // Abstract method for showing a dialog
    abstract String getFxmlPath();

    // Show the dialog with common functionality
    public void showDialog(Stage ownerStage) throws Exception {
        String customDialogStage = "/com/r4ppz/view/stage/CustomDialogStage.fxml";
        Stage stage = new Stage();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader customLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(customDialogStage)));
        Parent customRoot = customLoader.load();

        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(getFxmlPath()));
        Parent contentRoot = contentLoader.load();

        CustomDialogStageController customDialogStageController = customLoader.getController();
        customDialogStageController.setContent(contentRoot);

        Scene scene = new Scene(customRoot);
        scene.setFill(null);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        centerView(stage, ownerStage);
        stage.initOwner(ownerStage);
        stage.showAndWait();
    }

    // Center the dialog relative to the owner stage
    public void centerView(@NonNull Stage stage, Stage ownerStage) {
        stage.setOnShown(event -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - stage.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - stage.getHeight()) / 2;
            stage.setX(x);
            stage.setY(y);
        });
    }
}
