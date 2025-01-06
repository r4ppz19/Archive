package com.r4ppz.view.dialog;

import java.util.Objects;

import com.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

abstract class BaseDialogView {
    private final ImageLoader imageLoader = ImageLoader.getInstance();

    // Abstract method for showing a dialog
    abstract String getFxmlPath();

    // Show the dialog with common functionality
    public void showDialog(Stage ownerStage) throws Exception {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(getFxmlPath())));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.getIcons().add(imageLoader.loadImage("/com/r4ppz/image/icon/white-circle-icon.png"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            centerView(stage, ownerStage);
            stage.initOwner(ownerStage);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error loading dialog: " + e);
        }
    }

    // Center the dialog relative to the owner stage
    public void centerView(@NotNull Stage stage, Stage ownerStage) {
        stage.setOnShown(event -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - stage.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - stage.getHeight()) / 2;
            stage.setX(x);
            stage.setY(y);
        });
    }
}
