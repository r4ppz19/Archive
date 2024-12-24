package org.r4ppz.controller.main;

import java.util.concurrent.TimeUnit;

import org.r4ppz.controller.service.LoadButtons;
import org.r4ppz.controller.service.RefreshUI;
import org.r4ppz.service.FileHandler;
import org.r4ppz.service.RefresherService;
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
    private RefresherService refresherService;

    @FXML
    private VBox leftPanelVBox;

    @FXML
    private FlowPane listButtonFilesFlowPane;

    @FXML
    public void initialize() {
        System.out.println("Controller initialized");
        RefreshUI.vBoxRefresher(leftPanelVBox);
        
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
        RefreshUI.vBoxRefresher(leftPanelVBox);
        RefreshUI.flowPaneRefresher(listButtonFilesFlowPane);
    }
}
