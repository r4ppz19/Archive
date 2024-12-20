package org.r4ppz.view;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SuccessAlertView {
    private static SuccessAlertView successAlertView;

    private SuccessAlertView() {

    }

    public static SuccessAlertView getInstanSuccessAlertView() {
        if (successAlertView == null) {
            successAlertView = new SuccessAlertView();
        }

        return successAlertView;
    }

    private final LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showSuccessAlertView(Stage ownerStage) throws Exception {
        Stage successAlert = new Stage();
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/SuccessAlert.fxml"));
        successAlert.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        successAlert.setScene(scene);
        successAlert.setResizable(false);
        successAlert.initModality(Modality.APPLICATION_MODAL);
        successAlert.initOwner(ownerStage);

        // Center the dialog within the main window
        successAlert.setOnShown(event -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - successAlert.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - successAlert.getHeight()) / 2;
            successAlert.setX(x);
            successAlert.setY(y);
        });


        successAlert.showAndWait();
    }
}
