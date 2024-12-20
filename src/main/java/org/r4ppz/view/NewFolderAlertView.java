package org.r4ppz.view;

import org.r4ppz.util.FxmlLoader;
import org.r4ppz.util.ImageLoader;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFolderAlertView {
    private static NewFolderAlertView newFolderAlertView;

    private NewFolderAlertView() {

    }

    public static NewFolderAlertView getInstancErrorAlertView() {
        if (newFolderAlertView == null) {
            newFolderAlertView = new NewFolderAlertView();
        }

        return newFolderAlertView;
     }

    private final FxmlLoader fxmlLoader = FxmlLoader.getInstanceFxmlLoader();
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showNewFolderAlert() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.fxmlLoader("/org/r4ppz/view/ErrorAlert.fxml"));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
