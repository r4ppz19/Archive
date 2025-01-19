package com.r4ppz.view.dialog;

import javafx.stage.Stage;

public class CreateFolderDialogView extends BaseDialogView {
    private static CreateFolderDialogView instance;

    private CreateFolderDialogView() {}

    public static CreateFolderDialogView getInstance() {
        if (instance == null) {
            instance = new CreateFolderDialogView();
        }
        return instance;
    }

    @Override
    protected String getFxmlPath() {
        return "/com/r4ppz/view/dialog/CreateFolderDialog.fxml";
    }

    public void showCreateFolderDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage);
    }
}
