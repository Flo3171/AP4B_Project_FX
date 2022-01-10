package fr.utbm.ap4b_project_fx.energySims.controller;

import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.land.Plot;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System;
import java.net.URL;
import java.nio.file.Files;

/**
 * The class InGameMenu is the class controller of the InGameMenu.fxml file
 * this interface is displayed when the "m" key is pressed from the GameBoard window
 * it allows the user to quit,save or save and quit an ongoing game
 * @see GameBoard
 * @see MainMenu
 * @author Célian Humbert
 * @version 1.0
 */

public class InGameMenu {

    String url = new File( "" ).getAbsolutePath();

    /**
     * The button to quit
     */
    @FXML
    private Button Quit;

    /**
     * The button to save and quit
     */
    @FXML
    private Button SandQ;

    /**
     * The button to save
     */
    @FXML
    private Button Save;

    /**
     * The String to registre the path to the selected file
     */
    @FXML
    private String Path;


    /**
     * Action to do when the SansQ button is pressed
     */
    @FXML
    void saveAndQuit(ActionEvent event) throws IOException {
        save();
        quit();
    }

    /**
     * Action to do when the save button is pressed
     * open a file browser and let the user choose where he wishes to
     * save his file
     */
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


    /**
     * Action to do when the save button is pressed
     * close the GameBoard window and display the main menu window without saving the current game
     */
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

