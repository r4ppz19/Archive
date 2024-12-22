package org.r4ppz.view.dialog;

import javafx.fxml.FXMLLoader;
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
        FXMLLoader loader = FXMLLoader.load(getClass().getResource("/org/r4ppz/view/dialog/SuccessDialog.fxml"));
        showDialog(ownerStage, loader);
    }
}
