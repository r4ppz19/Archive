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

    /**
     * Validates the provided folder name and creates the folder if it is valid.
     * If the folder name is invalid, an error dialog is shown.
     *
     * @param actionEvent the action event that triggered the validation
     * @param folderName the name of the folder to be validated and potentially created
     * @throws Exception if an error occurs during folder creation
     */
    public void validateFolderName(ActionEvent actionEvent, String folderName) throws Exception {

        // Validate folder name and create the folder if valid
        if (isFolderNameValid(folderName)) {
            fileHandler.createFolder(folderName, fileHandler.getDefaultUploadsPath());
        } else {
            showErrorDialog(actionEvent);
        }
    }

    /**
     * Validates the given folder name.
     *
     * @param folderName the name of the folder to validate
     * @return {@code true} if the folder name is not null, not empty, and does not contain any invalid characters;
     *         {@code false} otherwise
     */
    private boolean isFolderNameValid(String folderName) {
        return folderName != null && !folderName.isEmpty() && folderName.chars().noneMatch(c -> INVALID_CHARACTERS.contains((char) c));
    }

    /**
     * Displays an error dialog.
     *
     * @param actionEvent the action event that triggered the error dialog
     * @throws Exception if an error occurs while showing the error dialog
     */
    private void showErrorDialog(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        errorDialogView.showErrorDialog(ownerStage);
    }
}
