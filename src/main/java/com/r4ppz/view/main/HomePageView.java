package com.r4ppz.view.main;

import java.util.Objects;

import com.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageView {
    private static HomePageView mainView;

    private HomePageView() {

    }

    public static HomePageView getInstance() {
        if (mainView == null) {
            mainView = new HomePageView();
        }

        return mainView;
    }

    private final ImageLoader imageLoader = ImageLoader.getInstance();

    public void showMainView() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/r4ppz/view/main/HomePage.fxml"))));
        stage.getIcons().add(imageLoader.loadImage("/com/r4ppz/image/main/1.jpg"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Archive");
        stage.show();
    }
}
