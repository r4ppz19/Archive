package org.r4ppz.controller;

import java.io.File;
import java.util.Objects;

import org.r4ppz.util.HandleFile;
import org.r4ppz.util.ImageLoader;
import org.r4ppz.view.SuccessAlertView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewV2Controller {

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
    public void uploadButtonAction(ActionEvent actionEvent) throws Exception{
        HandleFile handleFile = HandleFile.getInstanceHandleFile();
        File selectedFile = handleFile.fileChooser((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            handleFile.copyFileToProject(selectedFile, "src/main/resources/com/r4ppz/uploads");

            SuccessAlertView successAlertView = SuccessAlertView.getInstanSuccessAlertView();
            successAlertView.showSuccessAlertView();

            initialize();
        } else {
            System.out.println("File selection cancelled.");

        }
    }

    @FXML
    private void addFolderButton(ActionEvent actionEvent) {
        TextField newFolderTextField = new TextField();
        newFolderTextField.getStyleClass().add("new-folder-text-field");
        newFolderTextField.setPromptText("New folder name...");
        textFieldVboxContainer.getChildren().add(newFolderTextField);
        
        
        newFolderTextField.setOnAction(_ -> {
            String newFolderName = newFolderTextField.getText();
            createFolder(newFolderName);
            textFieldVboxContainer.getChildren().clear();
            refresh();
        });
    }

    private void refresh() {
        leftPanelVbox.getChildren().clear();
        // loadFilesToButton();
        loadFolderToButtons();
    }

    private void loadFilesToButton() {
        File directory = new File("src/main/resources/org/r4ppz/uploads/");
        if (directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                String fileName = file.getName().replace(".pdf", "");
                Button folderContainerButton = new Button(file.getName());
                
                ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();
                Image folderImage = imageLoader.loadImage("/org/r4ppz/image/folder-icon.png");
                
                ImageView folderIcon = new ImageView(folderImage);
                folderIcon.setFitHeight(22);
                folderIcon.setFitWidth(22);
                folderContainerButton.setGraphic(folderIcon);
                folderContainerButton.getStyleClass().add("content-folder-button");

                // Add tooltip
                Tooltip fileNametooltip = new Tooltip(fileName);
                folderContainerButton.setTooltip(fileNametooltip);
                fileNametooltip.getStyleClass().add("file-name-tooltip");

                leftPanelVbox.getChildren().add(folderContainerButton);
            }
        } else {
            System.out.println("Directory not found: " + directory.getAbsolutePath());
        }
    }

    private void loadFolderToButtons() {
        File mainFolder = new File("src/main/resources/org/r4ppz/uploads/");

        if (mainFolder.isDirectory()) {
            File[] filesInside = mainFolder.listFiles();
            if (filesInside != null) {
                for (File files : filesInside) {
                    if (files.isDirectory()) {
                        String folderName = files.getName();
                        Button folderButton = new Button(folderName);

                        ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();
                        Image folderImage = imageLoader.loadImage("/org/r4ppz/image/folder-icon.png");
                        
                        ImageView folderIcon = new ImageView(folderImage);
                        folderIcon.setFitHeight(22);
                        folderIcon.setFitWidth(22);
                        folderButton.setGraphic(folderIcon);
                        folderButton.getStyleClass().add("content-folder-button");

                        leftPanelVbox.getChildren().add(folderButton);
                    }
                }
            } else {
                System.out.println("Files inside is null");
            }
        } else {
            System.out.println("Main Folder is null");
        }
    }

    private void createFolder(String folderName) {
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
