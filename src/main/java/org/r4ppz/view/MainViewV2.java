package org.r4ppz.view;

import org.r4ppz.util.LoadFXML;
import org.r4ppz.util.ImageLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainViewV2 {
    private static MainViewV2 mainViewV2;

    private MainViewV2() {

    }

    public static MainViewV2 getInstancMainView() {
        if (mainViewV2 == null) {
            mainViewV2 = new MainViewV2();
        }

        return mainViewV2;
    }

    private final LoadFXML loadFXML = LoadFXML.getInstanceFxmlLoader();
    private final ImageLoader imageLoader = ImageLoader.getInstanceImageLoader();

    public void showMainViewV2() throws Exception {
        Stage mainViewV2 = new Stage();
        Scene scene = new Scene(loadFXML.fxmlLoader("/org/r4ppz/view/MainViewV2.fxml"));
        mainViewV2.getIcons().add(imageLoader.loadImage("/org/r4ppz/image/white-circle-icon.png"));
        mainViewV2.setScene(scene);
        mainViewV2.setResizable(false);
        mainViewV2.setTitle("Archive");
        mainViewV2.show();
    }
}
