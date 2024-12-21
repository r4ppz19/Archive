package org.r4ppz.controller.main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.r4ppz.util.FileHandler;
import org.r4ppz.util.ImageLoader;
import org.r4ppz.view.dialog.NewFolderDialogView;
import org.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController {
    private final NewFolderDialogView newFolderDialogView = NewFolderDialogView.getInstanceErrorAlertView();
    private final SuccessDialogView successAlertView = SuccessDialogView.getInstanceSuccessAlertView();

    @FXML
    private VBox leftPanelVBox;

    @FXML
    public void initialize() {
        refresh();
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
        FileHandler handleFile = FileHandler.getInstanceHandleFile();
        Path selectedFile = handleFile.fileChooser((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if (selectedFile != null) {
            handleFile.copyFileToProject(selectedFile, "src/main/resources/com/r4ppz/uploads");

            successAlertView.showSuccessDialogView(ownerStage);

            initialize();
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        newFolderDialogView.showNewFolderDialog(this, ownerStage);
    }

    public void refresh() {
        leftPanelVBox.getChildren().clear();
        // loadFilesToButton();
        populateFolderButtons();
    }

    /**
     * Loads folders from a specified directory and creates buttons for each folder.
     * The buttons are styled and added to the left panel VBox.
     * 
     * The method checks if the specified directory exists and is a directory.
     * If it is, it iterates through the directory entries and creates a button
     * for each subdirectory. The buttons are styled with the "content-folder-button"
     * CSS class and added to the left panel VBox.
     * 
     * If an IOException occurs while reading the directory, an error message is printed
     * to the console.
     */
    private void populateFolderButtons() {
        Path uploadsDirectory = Paths.get("src/main/resources/org/r4ppz/uploads/");

        if (Files.isDirectory(uploadsDirectory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsDirectory)) {
                for (Path folderPath : stream) {
                    if (Files.isDirectory(folderPath)) {
                        Button folderButton = createFolderButton(folderPath);
                        folderButton.getStyleClass().add("content-folder-button");

                        leftPanelVBox.getChildren().add(folderButton);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading directory: " + e.getMessage());
            }
        } else {
            System.out.println("Main Folder is not a directory");
        }
    }

    /**
     * Creates a button representing a folder with an icon.
     *
     * @param entry the path of the folder
     * @return a Button object with the folder name as its text and a folder icon as its graphic
     */
    private static Button createFolderButton(Path entry) {
        String folderName = entry.getFileName().toString();
        Button folderButton = new Button(folderName);

        ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();
        Image folderImage = imageLoader.loadImage("/org/r4ppz/image/icon/folder-icon.png");

        ImageView folderIcon = new ImageView(folderImage);
        folderIcon.setFitHeight(22);
        folderIcon.setFitWidth(22);
        folderButton.setGraphic(folderIcon);
        return folderButton;
    }

    /*
     * private void loadFilesToButton() {
     * File directory = new File("src/main/resources/org/r4ppz/uploads/");
     * if (directory.isDirectory()) {
     * for (File file : Objects.requireNonNull(directory.listFiles())) {
     * String fileName = file.getName().replace(".pdf", "");
     * Button folderContainerButton = new Button(file.getName());
     * 
     * ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();
     * Image folderImage =
     * imageLoader.loadImage("/org/r4ppz/image/folder-icon.png");
     * 
     * ImageView folderIcon = new ImageView(folderImage);
     * folderIcon.setFitHeight(22);
     * folderIcon.setFitWidth(22);
     * folderContainerButton.setGraphic(folderIcon);
     * folderContainerButton.getStyleClass().add("content-folder-button");
     * 
     * // Add tooltip
     * Tooltip fileNametooltip = new Tooltip(fileName);
     * folderContainerButton.setTooltip(fileNametooltip);
     * fileNametooltip.getStyleClass().add("file-name-tooltip");
     * 
     * leftPanelVbox.getChildren().add(folderContainerButton);
     * }
     * } else {
     * System.out.println("Directory not found: " + directory.getAbsolutePath());
     * }
     * }
     */

}