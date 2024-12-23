package org.r4ppz.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler {
    private static final String DEFAULT_UPLOADS_PATH = "src/main/resources/org/r4ppz/uploads/";
    private static FileHandler instance;

    private final SuccessDialogView successDialogView = SuccessDialogView.getInstance();

    private FileHandler() {
    }

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    public String getDefaultUploadsPath() {
        return DEFAULT_UPLOADS_PATH;
    }

    public void copyFileToProject(Path sourceFile, String destinationPath) throws IOException {
        Path destinationDir = Paths.get(destinationPath);
        Path destinationFile = destinationDir.resolve(sourceFile.getFileName());

        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
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

    public void uploadFile(ActionEvent actionEvent, String uploadsFilePath) throws Exception {
        Path selectedFile = fileChooser(getCurrentStage(actionEvent));

        if (selectedFile != null) {
            copyFileToProject(selectedFile, uploadsFilePath);
            successDialogView.showSuccessDialog(getCurrentStage(actionEvent));
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    private Path fileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile != null ? selectedFile.toPath() : null;
    }

    private Stage getCurrentStage(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
