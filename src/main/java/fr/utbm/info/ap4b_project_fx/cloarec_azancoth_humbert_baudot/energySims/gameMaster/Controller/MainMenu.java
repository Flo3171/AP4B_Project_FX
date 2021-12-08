package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.lang.System;


public class MainMenu {
    int counter=0;
    //private Button btclic;

    @FXML
    private Label lab;

    @FXML
    void launch() {

        //counter++;
        //lab.setStyle("-fx-font-size: 30;");
        //lab.setText("nous travallion actuelement a lancer le jeu cela fait deja "+counter+"ans");


    }

    @FXML
    void quit() {
        System.exit(0);

    }
}
