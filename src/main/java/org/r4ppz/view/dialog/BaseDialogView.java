package org.r4ppz.view.dialog;

import org.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BaseDialogView {
    private ImageLoader imageLoader = ImageLoader.getInstance();


    public void showDialog(Stage ownerStage, FXMLLoader loader) throws Exception {
        Stage stage = new Stage();
        try {
            Scene scene = new Scene(loader.load());
            stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            centerView(stage, ownerStage);
            stage.initOwner(ownerStage);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace(); // Or log this error for better visibility
            throw new Exception("Error loading dialog: " +  e);
        }
    }

    public void centerView(Stage stage, Stage ownerStage) {
        // Center the dialog within the main windowa
        stage.setOnShown(event -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - stage.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - stage.getHeight()) / 2;
            stage.setX(x);
            stage.setY(y);
        });

    }
}
