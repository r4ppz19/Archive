package org.r4ppz.service;

import java.util.List;

import org.r4ppz.controller.main.MainViewController;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.ErrorDialogView;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ValidateFolderName {
    private static final List<Character> INVALID_CHARACTERS = List.of('*', '?', '<', '>', '|', ':', '"', '\\', '/');
    
    private static FileHandler fileHandler = FileHandler.getInstance();
    private static ErrorDialogView errorDialogView = ErrorDialogView.getInstance();

    private static void validateFolderName(ActionEvent actionEvent, Sting folderName) throws Exception {

        // Validate folder name and create the folder if valid
        if (isFolderNameValid(folderName)) {
            fileHandler.createFolder(folderName, fileHandler.getDefaultUploadsPath());
        } else {
            showErrorDialog(actionEvent);
        }
    }

    private boolean isFolderNameValid(String folderName) {
        return folderName != null && !folderName.isEmpty() && folderName.chars().noneMatch(c -> INVALID_CHARACTERS.contains((char) c));
    }

    private static void showErrorDialog(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        errorDialogView.showErrorDialog(ownerStage);
    }
}
