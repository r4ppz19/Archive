package org.r4ppz.service;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class Refresher {
    public static void vBoxRefresher(VBox container) {
        if (container != null) {
            container.getChildren().clear();
            LoadButtons.loadFolderButtons(container);    
        } else {
            System.out.println("VBox is null");
        }
    }

    public static void flowPaneRefresher(FlowPane container ) {
        if (container != null) {
            container.getChildren().clear();

            String currentFolderName = LoadButtons.getCurrentFolderFullPath().getFileName().toString();
            System.out.println(currentFolderName);
            LoadButtons.loadFileButtons(currentFolderName, container);
        } else {
            System.out.println("FlowPane is null");
        }
    }
}
