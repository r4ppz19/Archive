package org.r4ppz.view;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorAlertView {
    private static ErrorAlertView errorAlertView;

    private ErrorAlertView() {

    }

    public static ErrorAlertView getInstancErrorAlertView() {
        if (errorAlertView == null) {
            errorAlertView = new ErrorAlertView();
        }

        return errorAlertView;
     }

    private final LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showErrorAlertView(Stage ownerStage) throws Exception {
        Stage errorAlert = new Stage();
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/ErrorAlert.fxml"));
        errorAlert.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        errorAlert.setScene(scene);
        errorAlert.setResizable(false);
        errorAlert.initModality(Modality.APPLICATION_MODAL);

        errorAlert.initOwner(ownerStage);

        // Center the dialog within the main window
        errorAlert.setOnShown(event -> {
            double x = ownerStage.getX() + (ownerStage.getWidth() - errorAlert.getWidth()) / 2;
            double y = ownerStage.getY() + (ownerStage.getHeight() - errorAlert.getHeight()) / 2;
            errorAlert.setX(x);
            errorAlert.setY(y);
        });

        errorAlert.showAndWait();
    }
}
