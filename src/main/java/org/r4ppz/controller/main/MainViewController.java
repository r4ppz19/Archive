package org.r4ppz.controller.main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.r4ppz.util.HandleFile;
import org.r4ppz.util.ImageLoader;
import org.r4ppz.view.dialog.NewFolderDialogView;
import org.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController {
    private NewFolderDialogView newFolderDialogView = NewFolderDialogView.getInstancErrorAlertView();

    @FXML
    private VBox leftPanelVbox;
    @FXML
    private ImageView pdfImageView;
    @FXML
    private HBox textFieldVboxContainer;

    @FXML
    public void initialize() {
        refresh();
    }

    @FXML
    public void uploadButtonAction(ActionEvent actionEvent) throws Exception {
        HandleFile handleFile = HandleFile.getInstanceHandleFile();
        Path selectedFile = handleFile.fileChooser((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        if (selectedFile != null) {
            handleFile.copyFileToProject(selectedFile, "src/main/resources/com/r4ppz/uploads");

            SuccessDialogView successAlertView = SuccessDialogView.getInstanSuccessAlertView();
            successAlertView.showSuccessDialogView(ownerStage);

            initialize();
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    @FXML
    private void addFolderButton(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        newFolderDialogView.showNewFolderDialog(this, ownerStage);
    }

    public void refresh() {
        leftPanelVbox.getChildren().clear();
        // loadFilesToButton();
        loadFolderToButtons();
    }

    private void loadFolderToButtons() {
        Path mainFolder = Paths.get("src/main/resources/org/r4ppz/uploads/");

        if (Files.isDirectory(mainFolder)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(mainFolder)) {
                for (Path entry : stream) {
                    if (Files.isDirectory(entry)) {
                        String folderName = entry.getFileName().toString();
                        Button folderButton = new Button(folderName);

                        ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();
                        Image folderImage = imageLoader.loadImage("/org/r4ppz/image/icon/folder-icon.png");

                        ImageView folderIcon = new ImageView(folderImage);
                        folderIcon.setFitHeight(22);
                        folderIcon.setFitWidth(22);
                        folderButton.setGraphic(folderIcon);
                        folderButton.getStyleClass().add("content-folder-button");

                        leftPanelVbox.getChildren().add(folderButton);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading directory: " + e.getMessage());
            }
        } else {
            System.out.println("Main Folder is not a directory");
        }
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