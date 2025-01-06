package com.r4ppz;

import java.util.Objects;

import com.r4ppz.util.ImageLoader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    private final ImageLoader imageLoader = ImageLoader.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage mainStage) throws Exception {
        mainStage.getIcons().add(imageLoader.loadImage("/com/r4ppz/image/icon/white-circle-icon.png"));
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/r4ppz/view/main/LoginPage.fxml"))));
        mainStage.setTitle("Archive");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

}
