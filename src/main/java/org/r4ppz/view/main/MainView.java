package org.r4ppz.view.main;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

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

    private final LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showMainView() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/main/Main.fxml"));
        stage.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/icon/white-circle-icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Archive");
        stage.show();
    }
}
