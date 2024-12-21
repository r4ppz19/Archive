package org.r4ppz.view.dialog;

import java.util.Objects;

import org.r4ppz.controller.dialog.NewFolderDialogViewController;
import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFolderDialogView {
    private static NewFolderDialogView newFolderDialogView;
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    private NewFolderDialogView() {

    }

    public static NewFolderDialogView getInstanceErrorAlertView() {
        if (newFolderDialogView == null) {
            newFolderDialogView = new NewFolderDialogView();
        }

        return newFolderDialogView;
     }


    /**
     * Displays a modal alert for creating a new folder.
     *
     * @param mainViewV2Controller the controller for the main view
     * @throws Exception if there is an error loading the FXML or displaying the stage
     */
    public void showNewFolderDialog(MainViewController mainViewController, Stage ownerStage) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/org/r4ppz/view/dialog/NewFolderDialog.fxml")));
    
        Scene scene = new Scene(loader.load());
    
        NewFolderDialogViewController controller = loader.getController();
        controller.setMainViewV2Controller(mainViewController);
    
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
    

/*     public void showNewFolderAlert() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.fxmlLoader("/org/r4ppz/view/NewFolderAlert.fxml"));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    } */
}
