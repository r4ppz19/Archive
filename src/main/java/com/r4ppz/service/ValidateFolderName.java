package com.r4ppz.service;

import java.util.List;

import com.r4ppz.util.StageGetter;
import com.r4ppz.view.dialog.ErrorDialogView;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ValidateFolderName {
    private final List<Character> INVALID_CHARACTERS = List.of('*', '?', '<', '>', '|', ':', '"', '\\', '/');
    
    private static ValidateFolderName instance;

    private final FileHandler fileHandler;
    private final ErrorDialogView errorDialogView;

    // Dependencies
    private ValidateFolderName(FileHandler fileHandler, ErrorDialogView errorDialogView) {
        this.fileHandler = fileHandler;
        this.errorDialogView = errorDialogView;
    }

    // Singleton
    public static ValidateFolderName getInstance(FileHandler fileHandler, ErrorDialogView errorDialogView) {
        if (instance == null) {
            instance = new ValidateFolderName(fileHandler, errorDialogView);
        }
        return instance;
    }

    public void validateFolderName(ActionEvent actionEvent, String folderName) throws Exception {

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

    private void showErrorDialog(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        errorDialogView.showErrorDialog(ownerStage);
    }
}
