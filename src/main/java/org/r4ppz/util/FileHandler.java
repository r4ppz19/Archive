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
    private SuccessDialogView successDialogView = SuccessDialogView.getInstance();

    private static FileHandler handleFile;

    private FileHandler() {

    }

    public static FileHandler getInstance() {
        if (handleFile == null) {
            handleFile = new FileHandler();
        }
        return handleFile;
    }


    /**
     * Opens a file chooser dialog for the user to select a file.
     *
     * @param stage the stage on which the file chooser dialog will be displayed
     * @return the path of the selected file, or null if no file was selected
     */
    public Path fileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile != null ? selectedFile.toPath() : null;
    }

    /**
     * Copies a file from the specified source path to the specified destination path within the project.
     *
     * @param sourceFile the path to the source file that needs to be copied
     * @param destinationPath the path to the destination directory where the file should be copied
     * @throws IOException if an I/O error occurs during the file copy operation
     */
    public void copyFileToProject(Path sourceFile, String destinationPath) throws IOException {
        Path destinationDir = Paths.get(destinationPath);
        Path destinationFile = destinationDir.resolve(sourceFile.getFileName().toString());
    
        // Ensure the destination directory exists
        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }
    
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Creates a new folder in the specified directory if it does not already exist.
     *
     * @param folderName the name of the folder to be created
     * @throws IOException if an I/O error occurs or the folder cannot be created
     */
    public void createFolder(String folderName) throws IOException {
        String newFolderPath = "src/main/resources/org/r4ppz/uploads/" + folderName;
        Path newFolder =  Paths.get(newFolderPath);

        if (!Files.exists(newFolder)) {
            Files.createDirectories(newFolder);
            System.out.println("Success");
        } else {
            System.out.println("New folder already exist");
        }
    }

    public void uploadFile(ActionEvent actionEvent, String uploadsFilePath) throws Exception {
        Path selectedFile = handleFile.fileChooser((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if (selectedFile != null) {
            handleFile.copyFileToProject(selectedFile, uploadsFilePath);

            successDialogView.showSuccessDialog(ownerStage);

        } else {
            System.out.println("File selection cancelled.");
        }
    }
}
