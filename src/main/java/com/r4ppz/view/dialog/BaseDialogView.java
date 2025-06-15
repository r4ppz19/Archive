package com.r4ppz.view.dialog;

import java.util.Objects;

import com.r4ppz.util.ImageLoader;
import javafx.scene.image.Image;
import org.checkerframework.checker.nullness.qual.NonNull;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract class BaseDialogView {
    
    // Abstract method for showing a dialog
    abstract String getFxmlPath();

    // Show the dialog with common functionality
    public void showDialog(@NonNull Stage ownerStage) throws Exception {
        String dialog = getFxmlPath();
        Stage stage = new Stage();
        
        ImageLoader imageLoader = ImageLoader.getInstance();
        Image image = imageLoader.loadImage("/com/r4ppz/image/main/1.jpg");
        
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(dialog)));

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.initModality(Modality.APPLICATION_MODAL);
        centerView(stage, ownerStage);
        stage.setResizable(false);
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
