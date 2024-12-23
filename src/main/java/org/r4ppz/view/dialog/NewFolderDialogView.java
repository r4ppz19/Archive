package org.r4ppz.view.dialog;

import java.util.Objects;

import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.util.ImageLoader;
import org.r4ppz.controller.dialog.NewFolderDialogViewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFolderDialogView extends BaseDialogView {
    private ImageLoader imageLoader = ImageLoader.getInstance();

    private static NewFolderDialogView instance;

    private NewFolderDialogView() {}

    public static NewFolderDialogView getInstance() {
        if (instance == null) {
            instance = new NewFolderDialogView();
        }
        return instance;
    }

    @Override
    protected String getFxmlPath() {
        return "/org/r4ppz/view/dialog/NewFolderDialog.fxml";
    }

    public void showNewFolderDialog(Stage ownerStage, MainViewController mainViewController) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(getFxmlPath())));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        centerView(stage, ownerStage);
        stage.initOwner(ownerStage);
        stage.showAndWait();
    }
}
