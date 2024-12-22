package org.r4ppz.view.dialog;

import javafx.fxml.FXMLLoader;
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

    public void showErrorDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage, "/org/r4ppz/view/dialog/ErrorDialog.fxml");
    }
}
