package org.r4ppz.view;

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

    public void showMainViewV2() throws Exception {
        Stage mainView = new Stage();
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/MainViewV2.fxml"));
        mainView.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        mainView.setScene(scene);
        mainView.setResizable(false);
        mainView.setTitle("Archive");
        mainView.show();
    }
}
