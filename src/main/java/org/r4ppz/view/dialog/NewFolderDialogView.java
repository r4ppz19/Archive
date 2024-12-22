package org.r4ppz.view.dialog;

import java.util.Objects;

import org.r4ppz.controller.dialog.NewFolderDialogViewController;
import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFolderDialogView extends BaseDialogView {
    private static NewFolderDialogView instance;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    private NewFolderDialogView() {}

    public static NewFolderDialogView getInstance() {
        if (instance == null) {
            instance = new NewFolderDialogView();
        }
        return instance;
    }

    public void showNewFolderDialog(Stage ownerStage, MainViewController mainViewController) throws Exception {
        showDialog(ownerStage, "/org/r4ppz/view/dialog/NewFolderDialog.fxml", mainViewController);
    }

    public void showDialog(Stage ownerStage, String fxmlPath, MainViewController mainViewController) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // set secondary controller (MainViweController)
        NewFolderDialogViewController mainController = loader.getController();
        mainController.setSecondaryController(mainViewController);

        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        centerView(stage, ownerStage);
        stage.initOwner(ownerStage);
        stage.showAndWait();
    }
}