package org.r4ppz.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HandleFile {
    private static HandleFile handleFile;

    private HandleFile() {

    }

    public static HandleFile getInstanceHandleFile() {
        if (handleFile == null) {
            handleFile = new HandleFile();
        }
        return handleFile;
    }


    /**
     * Opens a file chooser dialog for the user to select a file.
     *
     * @param stage the stage on which the file chooser dialog will be displayed
     * @return the selected file, or null if no file was selected
     */
    public File fileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        return fileChooser.showOpenDialog(stage);
    }

    /**
     * Copies a file to a specified destination path within the project.
     *
     * @param sourceFile the file to be copied
     * @param destinationPath the path where the file should be copied to
     * @throws IOException if an I/O error occurs during the copying process
     */
    public void copyFileToProject(File sourceFile, String destinationPath) throws IOException {
        File destinationFile = new File(destinationPath, sourceFile.getName());
        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public void createFolder(String folderName) {
        String newFolderPath = "src/main/resources/org/r4ppz/uploads/" + folderName;
        File newFolder = new File(newFolderPath);

        if (!newFolder.exists()) {
            if (newFolder.mkdir()) {
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("New folder already exist");
        }
    }
}
