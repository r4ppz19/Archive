package org.r4ppz.controller.main;

import org.r4ppz.service.FileHandler;
import org.r4ppz.service.LoadButtons;
import org.r4ppz.service.RefreshUI;
import org.r4ppz.util.StageGetter;
import org.r4ppz.view.dialog.CreateFolderDialogView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController {

    private final CreateFolderDialogView newFolderDialogView = CreateFolderDialogView.getInstance();
    private final FileHandler fileHandler = FileHandler.getInstance();

    @FXML
    private VBox leftPanelVBox;

    @FXML
    private FlowPane listButtonFilesFlowPane;

    @FXML
    public void initialize() {
        System.out.println("Controller initialized");

        leftPanelVBox.getChildren().clear();
        LoadButtons.loadFolderButtons(leftPanelVBox);
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) throws Exception {
        fileHandler.uploadFile(actionEvent, LoadButtons.getCurrentFolderFullPath());
    }

    @FXML
    private void handleAddFolderButtonAction(ActionEvent actionEvent) throws Exception {
        Stage ownerStage = StageGetter.getCurrentStage(actionEvent);
        newFolderDialogView.showCreateFolderDialog(ownerStage);
    }

    @FXML
    public void handleRefreshAction() {
        RefreshUI.vBoxRefresher(leftPanelVBox);
        RefreshUI.flowPaneRefresher(listButtonFilesFlowPane);
    }
}
