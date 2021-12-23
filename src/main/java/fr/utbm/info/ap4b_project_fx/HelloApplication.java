package fr.utbm.info.ap4b_project_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));

        BorderPane pane = new BorderPane();
        ImageView img = new ImageView("C:\\Users\\carst\\Desktop\\Drive memes\\téléchargement (3).png");

        img.fitWidthProperty().bind(stage.widthProperty());

        //fxmlLoader.load().setCenter(img);
        pane.setCenter(img);

        Scene scene = new Scene(pane);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}