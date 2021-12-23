package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.Controller;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.Main;
import java.net.URL;
import java.util.ResourceBundle;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.PlotType;
import static fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.PlotType.*;

public class GameBoard implements Initializable {
    ObservableList<String> ChoiceMake = FXCollections.observableArrayList("Habitation", "usine");
    Map m = new Map(26, 14, false);

    @FXML
    private GridPane Grid;


   // @FXML
    //private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;

    @FXML
    Button b1;

    //sert a rien
  /*  @FXML
    void  Wake(MouseEvent event) {
        //vérifier ce qui peut être contruit sur cette case update la liste des contruction disponible dans le menu déroulant
        //COMMENT RECUPERER LES COORDONEES DE LA CASE
        //MARCHE PAS mais c'est pas loin, je pense
        String source2 = event.getPickResult().getIntersectedNode().getId();
        System.out.println("Id: " + source2);
        Node node=getNode(Grid,source2);
        GridPane.getColumnIndex(node);
        GridPane.getRowIndex(node);
    }
*/

//marche pas
   public void mapDisplayer(Map m,GridPane G){
        /*l1.setText("dirt");
        l2.setText("clay");
        l3.setText("grass");
        l4.setText("sand");
        l5.setText("stone");
        l6.setText("water");
        l7.setText("dryPLot");
        l8.setText("OSKOURALED");*/
        //cette ligne marche pas je sais pas pk
       //G.add(l1,0,0);
       //l1.setVisible(true);
   int w=m.getMapWidth();
   int h=m.getMapHeight();
   for(int i=0;i<w;i++) {
       for (int j=0;j<h;j++){
           PlotType type=m.getCasesTable(i,j).getType();

        switch (type) {

            case DIRT:
                //Grid.add(l1,i,j); trouver comment afficher
                break;

            case CLAY:
                //Grid.add(l2,i,j); ...
                break;

            case GRASS:
                //Grid.add(l3,i,j);
                break;

            case SAND:
                //Grid.add(l4,i,j);
                break;

            case STONE:
                //Grid.add(l5,i,j);
                break;

            case WATER:
                //Grid.add(l6,i,j);
                break;

            case DRY_PLOT:
                //Grid.add(l7,i,j);
                break;

            default://Grid.add(l8,i,j);
            }
       }
     }
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapDisplayer(m,Grid);
        //l1.setVisible(true);
        //marche
        Grid.setVisible(false);
        //marche pas
        //Grid.add(b1,0,0);
    }

   /* public Button witchSlot(Button btn){
        if(btn.equals(b1)){
            return
        }
    }*/

    /*private class MyEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt) {
            System.out.println(((Control)evt.getSource()).getId());
        }*/
/*
    public static <T extends Node> T getNode(Node root, String id) {
        final Node node = root.lookup(id);
        if (node == null) {
            throw new NullPointerException(
                    "cannot find child node fx:id for argument: " + id);
        }/*  www .  j  av a2 s . c  o  m

        return (T) node;
    }

*/


}
