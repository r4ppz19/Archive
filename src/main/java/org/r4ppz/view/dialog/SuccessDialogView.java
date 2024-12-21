package org.r4ppz.view.dialog;

import java.util.Objects;

import org.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SuccessDialogView {
    private static SuccessDialogView successDialogView;

    private SuccessDialogView() {

    }

    public static SuccessDialogView getInstanceSuccessAlertView() {
        if (successDialogView == null) {
            successDialogView = new SuccessDialogView();
        }

        return successDialogView;
    }

    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showSuccessDialogView(Stage ownerStage) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/r4ppz/view/dialog/SuccessDialog.fxml"))));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(ownerStage);

        // Center the dialog within the main window
        stage.setOnShown(event -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - stage.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - stage.getHeight()) / 2;
            stage.setX(x);
            stage.setY(y);
        });


        stage.showAndWait();
    }
}
