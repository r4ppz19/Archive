package org.r4ppz.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    public Path fileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile != null ? selectedFile.toPath() : null;
    }

    public void copyFileToProject(Path sourceFile, String destinationPath) throws IOException {
        Path destinationDir  = Paths.get(destinationPath);
        Path destinationFile = destinationDir.resolve(destinationPath);
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

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
}
