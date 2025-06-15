package com.r4ppz;

import com.r4ppz.util.ImageLoader;
import javafx.scene.image.Image;
import org.checkerframework.checker.nullness.qual.NonNull;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NonNull Stage mainStage) throws Exception {
        ImageLoader imageLoader = ImageLoader.getInstance();

        Image image = imageLoader.loadImage("/com/r4ppz/image/main/1.jpg");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/r4ppz/view/main/LoginPage.fxml"));
        Parent customRoot = loader.load();

        Scene scene = new Scene(customRoot);
        mainStage.getIcons().add(image);
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
