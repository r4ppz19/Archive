package org.r4ppz.view.dialog;

import javafx.stage.Stage;

public class SuccessDialogView extends BaseDialogView {
    private static SuccessDialogView successDialogView;

    private SuccessDialogView() {}

    public static SuccessDialogView getInstance() {
        if (successDialogView == null) {
            successDialogView = new SuccessDialogView();
        }

        return successDialogView;
    }

    public void showSuccessDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage, "/org/r4ppz/view/dialog/SuccessDialog.fxml");
    }
}
