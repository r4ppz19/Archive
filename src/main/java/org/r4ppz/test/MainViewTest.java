package org.r4ppz.test;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainViewTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/r4ppz/view/main/Main.fxml"))));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Testing");
        primaryStage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }
    
}
