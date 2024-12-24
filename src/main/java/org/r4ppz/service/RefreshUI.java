package org.r4ppz.service;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class RefreshUI {
    public static void vBoxRefresher(VBox vBox) {
        if (vBox != null) {
            vBox.getChildren().clear();
            LoadButtons.loadFolderButtons(vBox);    
        } else {
            System.out.println("VBox is null");
        }
    }

    public static void flowPaneRefresher(FlowPane flowPane ) {
        if (flowPane != null) {
            flowPane.getChildren().clear();

            String currentFolderName = LoadButtons.getCurrentFolderFullPath().getFileName().toString();
            System.out.println(currentFolderName);
            LoadButtons.loadFileButtons(currentFolderName, flowPane);
        } else {
            System.out.println("FlowPane is null");
        }
    }


}
