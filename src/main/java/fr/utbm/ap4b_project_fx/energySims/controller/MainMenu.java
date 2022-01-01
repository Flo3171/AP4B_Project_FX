package fr.utbm.ap4b_project_fx.energySims.controller;

import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.net.URL;


public class MainMenu {

    String url = new File( "" ).getAbsolutePath();

    @FXML
    private Button loadGame;

    @FXML
    private Button newGame;
    
    @FXML
    private String Path;

    @FXML
    void launch() {
        Point size=new Point("0,0");
        size.setX(26);
        size.setY(14);
        try {

            Map m = new Map(size, false);
            URL fxmlURL = getClass().getResource("/fr/utbm/info/ap4b_project_fx/GameBoard.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Parent root = fxmlLoader.load();

            GameBoard Board = fxmlLoader.getController();
            Board.mapDisplayer(m);

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Wejgame");
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
    }
    }

    @FXML
    public void loads(ActionEvent event) throws IOException {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload File Path");
            File file = fileChooser.showOpenDialog(loadGame.getScene().getWindow());
            //stage.getScene().getWindow()
            if (file != null) {

                Path = file.getPath();
                System.out.println(Path);
            } else {
                System.out.println("error"); // or something else
            }
            Map m = new Map(Path);

            URL fxmlURL = getClass().getResource("/fr/utbm/info/ap4b_project_fx/GameBoard.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Parent root = fxmlLoader.load();

            GameBoard Board = fxmlLoader.getController();
            Board.mapDisplayer(m);

            Stage stage = new Stage();

            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Wejgame");
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void quit() {
        System.exit(0);

    }
}
