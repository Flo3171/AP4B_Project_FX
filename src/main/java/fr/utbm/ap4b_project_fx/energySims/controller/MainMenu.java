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

/**
 * The class MainMenu is the class controller of the MainMenu.fxml file
 * it displays the main menu to the user and allows him to launch the game
 *
 * @see Map
 * @see GameBoard
 * @author Célian Humbert
 * @version 1.0
 */

public class MainMenu {

    String url = new File( "" ).getAbsolutePath();
private static Map m=new Map(new Point(26, 14),false);

    /**
     * The button to load an existing game
     */
    @FXML
    private Button loadGame;


    /**
     * The button to launch a new game
     */
    @FXML
    private Button newGame;

    /**
     * The String to registre the path to the selected file
     */
    @FXML
    private String Path;



    /**
     * Action to do when the newGame button is pressed
     * load the GameBoard.fxlm file and display the mpa generated
     * by the map class
     * @see Map
     */
    @FXML
    void launch() {
        Point size=new Point("0,0");
        size.setX(26);
        size.setY(14);
        try {

            //Map m = new Map(size, false);
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
            Stage st = (Stage) newGame.getScene().getWindow();
            st.close();
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
    }
    }
    /**
     * Action to do when the loadGame button is pressed
     * display a file chooser to the user to let him choose a previously
     * saved game file
     * load the GameBoard.fxlm file and display the map generated
     * by the map class using the saved file
     * @see Map
     */

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


    /**
     *Used to return the map in the GameBoard calss
     *
     * @see GameBoard
     */
public static Map getMap(){
        return m;
}

    @FXML
    void quit() {
        System.exit(0);

    }
}
