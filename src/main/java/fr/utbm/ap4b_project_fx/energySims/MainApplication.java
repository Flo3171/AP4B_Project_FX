package fr.utbm.ap4b_project_fx.energySims;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fr/utbm/info/ap4b_project_fx/MainMenu.fxml"));
        /*
        GridPane pane = new GridPane();
        ImageView img0 = new ImageView("C:\\Users\\carst\\Desktop\\cours\\UV\\A21\\AP4B\\AP4B_Project_FX\\src\\main\\java\\fr\\utbm\\info\\ap4b_project_fx\\cloarec_azancoth_humbert_baudot\\energySims\\gameMaster\\Controller\\clay.JPG");

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println("Height: " + screenBounds.getHeight() + " Width: " + screenBounds.getWidth());

        pane.setVisible(true);
        pane.add(img0,1,0);
        img0.setFitWidth(screenBounds.getWidth());
        img0.setFitHeight(screenBounds.getHeight());

        Scene scene = new Scene(pane,screenBounds.getWidth(),screenBounds.getHeight());
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        */


        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        Scene scene = new Scene(fxmlLoader.load(),screenBounds.getWidth(),screenBounds.getHeight()-120);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}