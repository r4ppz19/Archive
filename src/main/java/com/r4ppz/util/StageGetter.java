package com.r4ppz.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class StageGetter {

    public static Stage getCurrentStage(@NotNull ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
