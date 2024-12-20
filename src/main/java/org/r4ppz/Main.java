package org.r4ppz;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();
    private final LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/main/Login.fxml"));
        mainStage.setTitle("Archive");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

}
