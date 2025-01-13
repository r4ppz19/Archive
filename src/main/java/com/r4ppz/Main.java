package com.r4ppz;

import com.r4ppz.controller.stage.CustomStageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage mainStage) throws Exception {
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader customLoader = new FXMLLoader(getClass().getResource("/com/r4ppz/view/stage/CustomStage.fxml"));
        Parent customRoot = customLoader.load();

        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/r4ppz/view/main/LoginPage.fxml"));
        HBox contentRoot = contentLoader.load();

        CustomStageController customStageController = customLoader.getController();
        customStageController.setStage(mainStage);
        customStageController.setContent(contentRoot);

        Scene scene = new Scene(customRoot);
        scene.setFill(null);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
