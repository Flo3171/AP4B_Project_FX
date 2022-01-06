package fr.utbm.ap4b_project_fx.energySims.controller;

import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.Main;

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


public class InGameMenu {

    String url = new File( "" ).getAbsolutePath();

    @FXML
    private Button Quit;

    @FXML
    private Button SandQ;

    @FXML
    private Button Save;



    @FXML
    private String Path;

    @FXML
    void saveAndQuit(ActionEvent event) throws IOException {
        save();
        quit();
    }


    @FXML
    public void save() throws IOException {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload File Path");
            File file = fileChooser.showOpenDialog(Save.getScene().getWindow());
            //stage.getScene().getWindow()
            if (file != null) {

                Path = file.getPath();
                System.out.println(Path);
            } else {
                System.out.println("error"); // or something else
            }

            URL fxmlURL = getClass().getResource("/fr/utbm/info/ap4b_project_fx/GameBoard.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Parent root = fxmlLoader.load();

            GameBoard Board = fxmlLoader.getController();
            MainMenu.getMap().saveInFile(Path);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void quit() throws IOException {
        try {

            URL fxmlURL = getClass().getResource("/fr/utbm/info/ap4b_project_fx/MainMenu.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Parent root = fxmlLoader.load();

            MainMenu menu = fxmlLoader.getController();

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




}

