package com.r4ppz.controller.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.r4ppz.service.FileHandler;
import com.r4ppz.util.ImageLoader;
import com.r4ppz.util.StageGetter;
import com.r4ppz.view.dialog.CreateFolderDialogView;
import com.r4ppz.view.dialog.SuccessDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageController {

    private final SuccessDialogView successDialogView = SuccessDialogView.getInstance();
    private final CreateFolderDialogView newFolderDialogView = CreateFolderDialogView.getInstance();
    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private final FileHandler fileHandler = FileHandler.getInstance(successDialogView);

    @FXML
    private VBox folderBox;

    @FXML
    private VBox fileBox;

    @FXML
    public void initialize() {
        System.out.println("Controller initialized");
        loadFolder();
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
        Path defaultPath = Path.of(fileHandler.getDefaultUploadsPath());
        fileHandler.uploadFile(actionEvent, defaultPath);
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        newFolderDialogView.showCreateFolderDialog(ownerStage);
    }


    private void loadFolder() {
        try (Stream<Path> paths = Files.list(Paths.get(fileHandler.getDefaultUploadsPath()))) {
            paths.filter(Files::isDirectory).forEach(folder -> {
                Button folderButton = createFolderButton(folder.getFileName().toString());

                // Folder button action
                folderButton.setOnAction(event -> loadFiles(folder, fileBox));
                folderBox.getChildren().add(folderButton);
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadFiles(Path folder, @NonNull VBox fileBox) {
        fileBox.getChildren().clear(); // Clear previous files

        try (Stream<Path> files = Files.list(folder)) {
            files.filter(Files::isRegularFile).forEach(file -> {
                Button fileButton = createFileButton(file.getFileName().toString());
                fileBox.getChildren().add(fileButton);
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @NonNull
    private Button createFolderButton(String folderName) {
        Button folderButton = new Button(folderName);

        Image folderImage = imageLoader.loadImage("/com/r4ppz/image/icon/folder-icon.png");
        ImageView folderIcon = new ImageView(folderImage);
        folderIcon.setFitHeight(22);
        folderIcon.setFitWidth(22);
        folderButton.setGraphic(folderIcon);

        folderButton.getStyleClass().add("folder-button");
        return folderButton;
    }

    @NonNull
    private Button createFileButton(String fileName) {
        Button fileButton = new Button(fileName);
        fileButton.getStyleClass().add("file-button");

        Tooltip fileNameTooltip = new Tooltip(fileName);
        fileButton.setTooltip(fileNameTooltip);
        fileNameTooltip.getStyleClass().add("file-name-tooltip");

        return fileButton;
    }
}
