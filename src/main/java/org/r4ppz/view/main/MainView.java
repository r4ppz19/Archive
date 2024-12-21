package org.r4ppz.view.main;

import java.util.Objects;

import org.r4ppz.util.ImageLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {
    private static MainView mainView;

    private MainView() {

    }

    public static MainView getInstancMainView() {
        if (mainView == null) {
            mainView = new MainView();
        }

        return mainView;
    }

    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showMainView() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/r4ppz/view/main/Main.fxml"))));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Archive");
        stage.show();
    }
}
