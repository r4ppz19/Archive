package org.r4ppz.test;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainViewTest extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        showMainViewV2(arg0);
    }

    public void showMainViewV2(Stage stage) throws Exception {
        LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();
        ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/MainView.fxml"));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Archive");
        stage.show();
    }
}
