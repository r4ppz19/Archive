package org.r4ppz.controller.main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.r4ppz.util.FileHandler;
import org.r4ppz.util.ImageLoader;
import org.r4ppz.view.dialog.NewFolderDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController {
    private Path currentFolderFullPath = null;

    private final NewFolderDialogView newFolderDialogView = NewFolderDialogView.getInstance();
    private final FileHandler fileHandler = FileHandler.getInstance();
    private final ImageLoader imageLoader = ImageLoader.getInstance();

    @FXML
    private VBox leftPanelVBox;
    public VBox getLeftPanelVBox() {
        return leftPanelVBox;
    }
    @FXML
    private FlowPane listButtonFilesFlowPane;

    @FXML
    public void initialize() {
        if (leftPanelVBox != null) {
            refreshVbox(leftPanelVBox);
        } else {
            System.out.println("leftPanelVBox is null");
        }
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
        fileHandler.uploadFile(actionEvent, currentFolderFullPath);
        refreshVbox(leftPanelVBox);
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = getCurrentStage(actionEvent);
        newFolderDialogView.showNewFolderDialog(ownerStage, this);
    }

    // VBOX REFRESHER
    public void refreshVbox(Pane container) {
        if (container == null) {
            System.out.println("Container is null!");
            return;
        }
        container.getChildren().clear();
        populateFolderButtons();
    }
    
    private void populateFolderButtons() {
        Path uploadsDirectory = Paths.get(fileHandler.getDefaultUploadsPath());

        if (Files.isDirectory(uploadsDirectory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsDirectory)) {
                for (Path folderPath : stream) {
                    if (Files.isDirectory(folderPath)) {
                        Button folderButton = createFolderButton(folderPath);

                        folderButton.setOnAction(event -> {
                            listButtonFilesFlowPane.getChildren().clear();
                            populateFilesButton(folderButton);
                            
                            Path currentFolderName = Paths.get(folderButton.getText());
                            Path uploadsPath = Paths.get(fileHandler.getDefaultUploadsPath());
                            currentFolderFullPath = uploadsPath.resolve(currentFolderName);
                            // ! CHECK POINT
                            System.out.println(currentFolderFullPath);
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

    private Button createFolderButton(Path folderPath) {
        String folderName = folderPath.getFileName().toString();
        Button folderButton = new Button(folderName);

        Image folderImage = imageLoader.loadImage("/org/r4ppz/image/icon/folder-icon.png");
        ImageView folderIcon = new ImageView(folderImage);
        folderIcon.setFitHeight(22);
        folderIcon.setFitWidth(22);
        folderButton.setGraphic(folderIcon);

        folderButton.getStyleClass().add("folder-button");
        return folderButton;
    }

    private void populateFilesButton(Button folderButton) {
        String currentFolderName = folderButton.getText();
        Path folderPath = Paths.get(fileHandler.getDefaultUploadsPath(), currentFolderName);

        if (Files.isDirectory(folderPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                for (Path file : stream) {
                    String fileName = file.getFileName().toString();
                    Button fileButton = createFileButton(fileName);
                    listButtonFilesFlowPane.getChildren().add(fileButton);
                }
            } catch (IOException e) {
                System.out.println("IO EXCEPTION: " + e.getMessage());
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }

    private Button createFileButton(String fileName) {
        Button fileButton = new Button(fileName);
        fileButton.getStyleClass().add("file-button");

        Tooltip fileNameTooltip = new Tooltip(fileName);
        fileButton.setTooltip(fileNameTooltip);
        fileNameTooltip.getStyleClass().add("file-name-tooltip");

        return fileButton;
    }

    private Stage getCurrentStage(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
