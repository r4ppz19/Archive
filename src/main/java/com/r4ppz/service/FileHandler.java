package com.r4ppz.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.r4ppz.util.StageGetter;
import com.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    private void copyFileToProject(@NotNull Path sourceFile, @NotNull Path destinationPath) throws IOException {
        Path destinationFile = destinationPath.resolve(sourceFile.getFileName());

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }

        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public void createFolder(String folderName, String destinationPath) throws IOException {
        Path newFolderPath = Paths.get(destinationPath, folderName);

        if (Files.notExists(newFolderPath)) {
            Files.createDirectories(newFolderPath);
            System.out.println("Folder created successfully: " + folderName);
        } else {
            System.out.println("Folder already exists: " + folderName);
        }
    }

    public void uploadFile(ActionEvent actionEvent, Path uploadsFilePath) throws Exception {
        Path selectedFile = fileChooser(StageGetter.getCurrentStage(actionEvent));

        if (selectedFile != null) {
            copyFileToProject(selectedFile, uploadsFilePath);
            successDialogView.showSuccessDialog(StageGetter.getCurrentStage(actionEvent));
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    @Nullable
    private Path fileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile != null ? selectedFile.toPath() : null;
    }
}
