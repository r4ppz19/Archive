package com.r4ppz.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import com.r4ppz.util.StageGetter;
import com.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler {
    private static final String DEFAULT_UPLOADS_PATH = "src/main/resources/com/r4ppz/uploads/";
    private static FileHandler instance;

    private final SuccessDialogView successDialogView;

    // Dependency
    private FileHandler(SuccessDialogView successDialogView) {
        this.successDialogView = successDialogView;
    }

    // Singleton
    public static FileHandler getInstance(SuccessDialogView successDialogView) {
        if (instance == null) {
            instance = new FileHandler(successDialogView);
        }
        return instance;
    }

    public String getDefaultUploadsPath() {
        return DEFAULT_UPLOADS_PATH;
    }

    /**
     * Copies a file from the source path to the destination path within the project.
     *
     * @param sourceFile the path of the source file to be copied
     * @param destinationPath the path of the destination directory where the file will be copied
     * @throws IOException if an I/O error occurs
     */
    private void copyFileToProject(@NonNull Path sourceFile, @NonNull Path destinationPath) throws IOException {
        Path destinationFile = destinationPath.resolve(sourceFile.getFileName());

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }

        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Creates a folder at the specified destination path.
     *
     * @param folderName the name of the folder to be created
     * @param destinationPath the path where the folder should be created
     * @throws IOException if an I/O error occurs or the folder cannot be created
     */
    public void createFolder(String folderName, String destinationPath) throws IOException {
        Path newFolderPath = Paths.get(destinationPath, folderName);

        if (Files.notExists(newFolderPath)) {
            Files.createDirectories(newFolderPath);
            System.out.println("Folder created successfully: " + folderName);
        } else {
            System.out.println("Folder already exists: " + folderName);
        }
    }

    /**
     * Handles the file upload process triggered by an action event.
     * 
     * @param actionEvent The action event that triggered the file upload.
     * @param uploadsFilePath The path where the uploaded file should be stored.
     * @throws Exception If an error occurs during the file upload process.
     */
    public void uploadFile(ActionEvent actionEvent, Path uploadsFilePath) throws Exception {
        Path selectedFile = fileChooser(StageGetter.getCurrentStage(actionEvent));

        if (selectedFile != null) {
            copyFileToProject(selectedFile, uploadsFilePath);
            successDialogView.showSuccessDialog(StageGetter.getCurrentStage(actionEvent));
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    /**
     * Opens a file chooser dialog to allow the user to select a file.
     *
     * @param stage the stage on which the file chooser dialog should be displayed
     * @return the path of the selected file, or null if no file was selected
     */
    @Nullable
    private Path fileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile != null ? selectedFile.toPath() : null;
    }
}
