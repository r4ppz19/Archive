package org.r4ppz.util;

import javafx.scene.layout.Pane;

public class ContainerCleaner {

    public static void containerCleaner(Pane container) {
        if (container == null) {
            System.out.println("Container is null!");
            return;
        }
        container.getChildren().clear();
    }
}
