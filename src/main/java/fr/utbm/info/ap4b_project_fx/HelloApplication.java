package fr.utbm.info.ap4b_project_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GameBoard.fxml"));

       /* GridPane pane = new GridPane();
        ImageView img0 = new ImageView("F:\\Image photo\\hélicoptere_de_combat\\ff.jpg");
        ImageView img1 = new ImageView("F:\\Image photo\\keepgoing\\b.jpg");

        img0.fitWidthProperty().bind(pane.minWidthProperty());
        img1.fitWidthProperty().bind(pane.minWidthProperty());
        pane.setVisible(true);
        pane.add(img1,0,0);
        pane.add(img0,1,0);

        */
        Scene scene = new Scene(fxmlLoader.load());
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}