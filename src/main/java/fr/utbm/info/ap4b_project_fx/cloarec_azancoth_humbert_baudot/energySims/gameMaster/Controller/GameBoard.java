package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.Controller;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoard implements Initializable {
    ObservableList<String> ChoiceMake = FXCollections.observableArrayList("Habitation", "usine");

    @FXML
    private GridPane Grid;

    @FXML
    private Button ValidationButton;

    @FXML
    void Validation(ActionEvent event) {

        ValidationButton.setVisible(false);
        choix.setVisible(false);
    }

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;

    @FXML
    void  Wake(MouseEvent event) {
        //vérifier ce qui peut être contruit sur cette case update la liste des contruction disponible dans le menu déroulant
        //COMMENT RECUPERER LES COORDONEES DE LA CASE
        //MARCHE PAS mais c'est pas loin, je pense
        String source2 = event.getPickResult().getIntersectedNode().getId();
        System.out.println("Id: " + source2);
        Node node=getNode(Grid,source2);
        GridPane.getColumnIndex(node);
        GridPane.getRowIndex(node);



        ValidationButton.setVisible(true);
        choix.setVisible(true);
    }

    @FXML
    private ChoiceBox choix;


    @FXML
    private void initialization()
    {
        ValidationButton.setVisible(false);
        choix.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choix.setValue("options");
        choix.setItems(ChoiceMake);
        ValidationButton.setVisible(false);
        choix.setVisible(false);
    }

    /*private class MyEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt) {
            System.out.println(((Control)evt.getSource()).getId());
        }*/
    public static <T extends Node> T getNode(Node root, String id) {
        final Node node = root.lookup(id);
        if (node == null) {
            throw new NullPointerException(
                    "cannot find child node fx:id for argument: " + id);
        }/*  www .  j  av a2 s . c  o  m*/

        return (T) node;
    }



}
