package org.r4ppz.view.dialog;

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

    @Override
    protected String getFxmlPath() {
        return "/org/r4ppz/view/dialog/NewFolderDialog.fxml";
    }

    public void showNewFolderDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage);
    }
}
