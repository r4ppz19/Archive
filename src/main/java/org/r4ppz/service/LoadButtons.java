package org.r4ppz.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.r4ppz.util.ImageLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class LoadButtons {
    private static FileHandler fileHandler = FileHandler.getInstance();

    private static ImageLoader imageLoader = ImageLoader.getInstance();

    public static void loadFolderButtons(VBox leftPanelVBox) {
        Path uploadsDirectory = Paths.get(fileHandler.getDefaultUploadsPath());

        if (Files.isDirectory(uploadsDirectory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsDirectory)) {
                for (Path folderPath : stream) {
                    if (Files.isDirectory(folderPath)) {
                        String folderName = folderPath.getFileName().toString();
                        Button folderButton = LoadButtons.createFolderButton(folderName);

/*                         folderButton.setOnAction(event -> {
                            listButtonFilesFlowPane.getChildren().clear();
                            populateFilesButton(folderButton);

                            Path currentFolderName = Paths.get(folderButton.getText());
                            Path uploadsPath = Paths.get(fileHandler.getDefaultUploadsPath());
                            currentFolderFullPath = uploadsPath.resolve(currentFolderName);
                            // ! CHECK POINT
                            System.out.println(currentFolderFullPath);
                        }); */

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

    private void loadFileButtons(Button folderButton, FlowPane listButtonFilesFlowPane) {
        String currentFolderName = folderButton.getText();
        Path folderPath = Paths.get(fileHandler.getDefaultUploadsPath(), currentFolderName);

        if (Files.isDirectory(folderPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                for (Path file : stream) {
                    String fileName = file.getFileName().toString();
                    Button fileButton = LoadButtons.createFileButton(fileName);
                    listButtonFilesFlowPane.getChildren().add(fileButton);
                }
            } catch (IOException e) {
                System.out.println("IO EXCEPTION: " + e.getMessage());
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }

    private static Button createFileButton(String fileName) {
        Button fileButton = new Button(fileName);
        fileButton.getStyleClass().add("file-button");

        Tooltip fileNameTooltip = new Tooltip(fileName);
        fileButton.setTooltip(fileNameTooltip);
        fileNameTooltip.getStyleClass().add("file-name-tooltip");

        return fileButton;
    }

    private static Button createFolderButton(String folderName) {
        Button folderButton = new Button(folderName);

        Image folderImage = imageLoader.loadImage("/org/r4ppz/image/icon/folder-icon.png");
        ImageView folderIcon = new ImageView(folderImage);
        folderIcon.setFitHeight(22);
        folderIcon.setFitWidth(22);
        folderButton.setGraphic(folderIcon);

        folderButton.getStyleClass().add("folder-button");
        return folderButton;
    }
}
