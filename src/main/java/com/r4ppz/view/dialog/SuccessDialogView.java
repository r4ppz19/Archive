package com.r4ppz.view.dialog;

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

    @Override
    protected String getFxmlPath() {
        return "/com/r4ppz/view/dialog/SuccessDialog.fxml";
    }

    public void showSuccessDialog(Stage ownerStage) throws Exception {
        showDialog(ownerStage);
    }
}
