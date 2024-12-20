package org.r4ppz.util;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoadFXML {

    private static LoadFXML loadFXML;
    
    private LoadFXML() {
    }

    public static LoadFXML getInstanceFxmlLoader() {
        if (loadFXML == null) {
            loadFXML = new LoadFXML();
        }

        return loadFXML;
    }

    public Parent fxmlLoader(String fxmlPath) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
    }
}
