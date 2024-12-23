package org.r4ppz.controller.main;

import java.nio.file.Path;

import org.r4ppz.service.FileHandler;
import org.r4ppz.service.LoadButtons;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.NewFolderDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        LoadButtons.loadFolderButtons(leftPanelVBox);
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
}
