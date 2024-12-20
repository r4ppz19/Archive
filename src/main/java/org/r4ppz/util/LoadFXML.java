package org.r4ppz.util;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoadFXML {

    private static LoadFXML fxmlLoader;
    
    private LoadFXML() {
    }

    public static LoadFXML getInstanceFxmlLoader() {
        if (fxmlLoader == null) {
            fxmlLoader = new LoadFXML();
        }

        return fxmlLoader;
    }

    public Parent fxmlLoader(String fxmlPath) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
    }
}
