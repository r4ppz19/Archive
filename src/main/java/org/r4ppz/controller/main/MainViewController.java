package org.r4ppz.controller.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.r4ppz.model.FolderData;
import org.r4ppz.service.FileHandler;
import org.r4ppz.util.ListLoader;
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
    private FolderData folderData = FolderData.getInstance();

    @FXML
    private VBox leftPanelVBox;

    @FXML
    private FlowPane listButtonFilesFlowPane;

    @FXML
    public void initialize() {
        System.out.println("Controller initialized");
        loadFiles();
        populateButton();
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        newFolderDialogView.showCreateFolderDialog(ownerStage);
    }

    @FXML
    public void handleRefreshButtonAction() {
        folderData = FolderData.getInstance(); // Reinitialize to avoid duplicating entries
        folderData.getFolderName().clear();
        listButtonFilesFlowPane.getChildren().clear();
    
        loadFiles();
        populateButton();
    }
    

    private void loadFiles() {
        Path uploadsPath = Paths.get(fileHandler.getDefaultUploadsPath());

        try {
            Map<String, List<String>> folderToFileMap = ListLoader.getFolderToFileMap(uploadsPath);
            for (Map.Entry<String, List<String>> entry : folderToFileMap.entrySet()) {
                folderData.addFolderData(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            System.err.println("Error in loadFiles: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void populateButton() {
        for (String folderName : folderData.getFolderName()) {
            Button folderButton = createFolderButton(folderName);

            folderButton.setOnAction(event -> {
                leftPanelVBox.getChildren().clear();

                // Populate the second VBox with file buttons
                List<String> files = folderData.getFile(folderName);
                for (String fileName : files) {
                    Button fileButton = createFileButton(fileName);
                    listButtonFilesFlowPane.getChildren().add(fileButton);
                }
            });

            leftPanelVBox.getChildren().add(folderButton);
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

    private Button createFileButton(String fileName) {
        Button fileButton = new Button(fileName);
        fileButton.getStyleClass().add("file-button");

        Tooltip fileNameTooltip = new Tooltip(fileName);
        fileButton.setTooltip(fileNameTooltip);
        fileNameTooltip.getStyleClass().add("file-name-tooltip");

        return fileButton;
    }
}
