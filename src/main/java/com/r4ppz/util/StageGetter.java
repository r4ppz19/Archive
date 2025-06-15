package com.r4ppz.util;

import org.checkerframework.checker.nullness.qual.NonNull;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class StageGetter {

    public static Stage getCurrentStage(@NonNull ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}
