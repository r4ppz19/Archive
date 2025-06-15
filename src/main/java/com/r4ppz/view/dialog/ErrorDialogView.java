package com.r4ppz.view.dialog;

import javafx.stage.Stage;

public class ErrorDialogView extends BaseDialogView {
    private static ErrorDialogView errorDialogView;

    private ErrorDialogView() {}

    public static ErrorDialogView getInstance() {
        if (errorDialogView == null) {
            errorDialogView = new ErrorDialogView();
        }
        return errorDialogView;
    }

    @Override
    protected String getFxmlPath() {
        return "/com/r4ppz/view/dialog/ErrorDialog.fxml";
    }

    public void showErrorDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage);
    }
}
