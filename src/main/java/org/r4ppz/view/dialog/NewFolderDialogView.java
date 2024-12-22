package org.r4ppz.view.dialog;

import org.r4ppz.controller.dialog.NewFolderDialogViewController;
import org.r4ppz.controller.main.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class NewFolderDialogView extends BaseDialogView {
    private static NewFolderDialogView instance;

    private NewFolderDialogView() {}

    public static NewFolderDialogView getInstance() {
        if (instance == null) {
            instance = new NewFolderDialogView();
        }
        return instance;
    }

    public void showNewFolderDialog(MainViewController mainViewController, Stage ownerStage) throws Exception {
        // Create FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/r4ppz/view/dialog/NewFolderDialog.fxml"));
        
        // Get the controller after showing dialog
        showDialog(ownerStage, loader);
        
        // Set main view controller reference
        NewFolderDialogViewController controller = loader.getController();
        controller.setMainViewController(mainViewController);
    }
}