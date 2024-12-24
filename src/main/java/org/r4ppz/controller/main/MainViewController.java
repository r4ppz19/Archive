package org.r4ppz.controller.main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.r4ppz.controller.service.LoadButtons;
import org.r4ppz.controller.service.RefreshUI;
import org.r4ppz.model.FoldersAndFiles;
import org.r4ppz.service.FileHandler;
import org.r4ppz.service.RefresherService;
import org.r4ppz.util.GetFilesToList;
import org.r4ppz.util.ImageLoader;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.CreateFolderDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController {

    private final CreateFolderDialogView newFolderDialogView = CreateFolderDialogView.getInstance();
    private final FileHandler fileHandler = FileHandler.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private RefresherService refresherService;

    @FXML
    private VBox leftPanelVBox;

    @FXML
    private FlowPane listButtonFilesFlowPane;

    @FXML
    public void initialize() {
        System.out.println("Controller initialized");
        leftPanelVBox.getChildren().clear();
        populateFolderButtons(leftPanelVBox);
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
        fileHandler.uploadFile(actionEvent, LoadButtons.getCurrentFolderFullPath());
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        newFolderDialogView.showCreateFolderDialog(ownerStage);
        refresherService.startRefreshing(this::handleRefreshAction, 0, 5, TimeUnit.SECONDS);
    }

    @FXML
    public void handleRefreshAction() {
        leftPanelVBox.getChildren().clear();
        populateFolderButtons(leftPanelVBox);
    }

    public void populateFolderButtons(VBox leftPanelVBox) {
        Path pathToLoad = Paths.get(fileHandler.getDefaultUploadsPath());

        if (Files.isDirectory(pathToLoad)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(pathToLoad)) {
                for (Path folderPath : stream) {
                    if (Files.isDirectory(folderPath)) {
                        String folderName = folderPath.getFileName().toString();
                        Button folderButton = createFolderButton(folderName);

                        folderButton.setOnAction(event -> {
                            Path uploadsPath = Paths.get(fileHandler.getDefaultUploadsPath());
                            Path fullCurrentFolderPath = uploadsPath.resolve(folderName);

                            FoldersAndFiles.addFolderData(folderName, GetFilesToList.getFilesTolist(fullCurrentFolderPath));
                            System.out.println(GetFilesToList.getFilesTolist(fullCurrentFolderPath));

                            populateFileButton(folderName);
                        });

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

    private void populateFileButton(String folderName) {
        listButtonFilesFlowPane.getChildren().clear();

        List<String> files = FoldersAndFiles.getFiles(folderName);
        for (String fileName : files) {
            Button fileButton = createFileButton(fileName);
            listButtonFilesFlowPane.getChildren().add(fileButton);
        }
    }

    private Button createFolderButton(String folderName) {
        Button folderButton = new Button(folderName);

        Image folderImage = imageLoader.loadImage("/org/r4ppz/image/icon/folder-icon.png");
        ImageView folderIcon = new ImageView(folderImage);
        folderIcon.setFitHeight(22);
        folderIcon.setFitWidth(22);
        folderButton.setGraphic(folderIcon);

        folderButton.getStyleClass().add("folder-button");
        return folderButton;
    }

    private static Button createFileButton(String fileName) {
        Button fileButton = new Button(fileName);
        fileButton.getStyleClass().add("file-button");

        Tooltip fileNameTooltip = new Tooltip(fileName);
        fileButton.setTooltip(fileNameTooltip);
        fileNameTooltip.getStyleClass().add("file-name-tooltip");

        return fileButton;
    }
}
