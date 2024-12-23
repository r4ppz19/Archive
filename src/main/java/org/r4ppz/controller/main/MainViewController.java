package org.r4ppz.controller.main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.r4ppz.service.CreateButton;
import org.r4ppz.service.FileHandler;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.NewFolderDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController {
    private Path currentFolderFullPath = null;

    private final NewFolderDialogView newFolderDialogView = NewFolderDialogView.getInstance();
    private final FileHandler fileHandler = FileHandler.getInstance();

    @FXML
    private VBox leftPanelVBox;

    @FXML
    private FlowPane listButtonFilesFlowPane;

    @FXML
    public void initialize() {
        leftPanelVBox.getChildren().clear();
        populateFolderButtons();
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
        fileHandler.uploadFile(actionEvent, currentFolderFullPath);
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        newFolderDialogView.showNewFolderDialog(ownerStage);
    }
    
    private void populateFolderButtons() {
        Path uploadsDirectory = Paths.get(fileHandler.getDefaultUploadsPath());

        if (Files.isDirectory(uploadsDirectory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsDirectory)) {
                for (Path folderPath : stream) {
                    if (Files.isDirectory(folderPath)) {
                        String folderName = folderPath.getFileName().toString();
                        Button folderButton = CreateButton.createFolderButton(folderName);

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

    private void populateFilesButton(Button folderButton) {
        String currentFolderName = folderButton.getText();
        Path folderPath = Paths.get(fileHandler.getDefaultUploadsPath(), currentFolderName);

        if (Files.isDirectory(folderPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                for (Path file : stream) {
                    String fileName = file.getFileName().toString();
                    Button fileButton = CreateButton.createFileButton(fileName);
                    listButtonFilesFlowPane.getChildren().add(fileButton);
                }
            } catch (IOException e) {
                System.out.println("IO EXCEPTION: " + e.getMessage());
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }
}
