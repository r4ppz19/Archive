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

    public void showNewFolderDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage);
    }
}
