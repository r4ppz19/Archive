package com.r4ppz.view.dialog;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

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
    public void showDialog(@NonNull Stage ownerStage) throws Exception {
        String dialog = getFxmlPath();
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(dialog)));

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        centerView(stage, ownerStage);
        stage.setResizable(false);
        stage.initOwner(ownerStage);
        stage.showAndWait();
    }

    // Center the dialog relative to the owner stage
    public void centerView(@NonNull Stage stage, Stage ownerStage) {
        stage.setOnShown(_ -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - stage.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - stage.getHeight()) / 2;
            stage.setX(x);
            stage.setY(y);
        });
    }
}
