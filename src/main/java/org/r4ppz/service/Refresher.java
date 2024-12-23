package org.r4ppz.service;

import javafx.scene.layout.VBox;

public class Refresher {
    public static void vBoxRefresher(VBox container) {
        container.getChildren().clear();
        LoadButtons.loadFolderButtons(container);
    }
}
