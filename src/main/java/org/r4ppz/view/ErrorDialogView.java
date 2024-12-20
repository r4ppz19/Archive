package org.r4ppz.view;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorDialogView {
    private static ErrorDialogView errorDialogView;

    private ErrorDialogView() {

    }

    public static ErrorDialogView getInstancErrorAlertView() {
        if (errorDialogView == null) {
            errorDialogView = new ErrorDialogView();
        }

        return errorDialogView;
     }

    private final LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showErrorDialogView(Stage ownerStage) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/ErrorDialog.fxml"));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
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
