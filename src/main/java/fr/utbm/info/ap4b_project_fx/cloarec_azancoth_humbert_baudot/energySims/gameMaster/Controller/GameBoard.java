package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.Controller;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.Main;
import java.net.URL;
import java.util.ResourceBundle;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.PlotType;
import static fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.PlotType.*;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;

public class GameBoard implements Initializable {
    ObservableList<String> ChoiceMake = FXCollections.observableArrayList("Habitation", "usine");



    @FXML
    private GridPane Grid = new GridPane();



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
   public void mapDisplayer(GridPane G){
        //cette ligne marche pas je sais pas pk
       //G.add(l1,0,0);
       Point size=new Point("0,0");
       size.setX(26);
       size.setY(14);
       Map m = new Map(size, false);
   int w= size.getX();
   int h = size.getY();
   for(int i=0;i<w;i++) {
       for (int j=0;j<h;j++){
           PlotType type=m.getCasesTable(i,j).getType();

        switch (type) {

            case DIRT:
                ImageView img = new ImageView("I:\\Travail\\AP4B\\AP4B_Project_FX\\src\\main\\resources\\images\\dirt.JPG");
                //img.fitHeightProperty().bind(Grid.maxHeightProperty());
                //img.fitWidthProperty().bind(Grid.maxWidthProperty());
                img.setFitHeight(60);
                img.setFitWidth(60);
                Grid.add(img,i,j);

                Label l1=new Label();
                l1.setText("dirt");
                Grid.add(l1,i,j);
                break;

            case CLAY:

                ImageView img2 = new ImageView("I:\\Travail\\AP4B\\AP4B_Project_FX\\src\\main\\resources\\images\\clay.JPG");
                img2.setFitHeight(60);
                img2.setFitWidth(60);
                Grid.add(img2,i,j);




                Label l2=new Label();
                l2.setText("clay");
                Grid.add(l2,i,j);
                break;

            case GRASS:

                ImageView img3 = new ImageView("I:\\Travail\\AP4B\\AP4B_Project_FX\\src\\main\\resources\\images\\Grass.JPG");
                img3.setFitHeight(60);
                img3.setFitWidth(60);
                Grid.add(img3,i,j);



                Label l3=new Label();
                l3.setText("grass");
                Grid.add(l3,i,j);
                break;

            case SAND:
                ImageView img4 = new ImageView("I:\\Travail\\AP4B\\AP4B_Project_FX\\src\\main\\resources\\images\\sand.jpg");
                img4.setFitHeight(60);
                img4.setFitWidth(60);
                Grid.add(img4,i,j);



                Label l4=new Label();
                l4.setText("sand");
                Grid.add(l4,i,j);
                break;

            case STONE:
                ImageView img5 = new ImageView("I:\\Travail\\AP4B\\AP4B_Project_FX\\src\\main\\resources\\images\\stone.jpg");
                img5.setFitHeight(60);
                img5.setFitWidth(60);
                Grid.add(img5,i,j);



                Label l5=new Label();
                l5.setText("stone");
                Grid.add(l5,i,j);
                break;

            case WATER:

                ImageView img6 = new ImageView("I:\\Travail\\AP4B\\AP4B_Project_FX\\src\\main\\resources\\images\\water.jpg");
                img6.setFitHeight(60);
                img6.setFitWidth(60);
                Grid.add(img6,i,j);



                Label l6=new Label();
                l6.setText("water");
                Grid.add(l6,i,j);
                break;

            case DRY_PLOT:
                Label l7=new Label();
                l7.setText("DRY_plot");
                Grid.add(l7,i,j);
                break;

            default:
                Label l8=new Label();
                l8.setText("OSKOURALED");
                Grid.add(l8,i,j);;
            }
       }
     }
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapDisplayer(Grid);
        //l1.setVisible(true);
        //marche
        //Grid.setVisible(false);
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
