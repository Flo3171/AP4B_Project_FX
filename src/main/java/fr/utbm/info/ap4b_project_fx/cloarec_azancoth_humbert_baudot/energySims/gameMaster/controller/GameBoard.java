package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.controller;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;

import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.PlotType;
import javafx.stage.Screen;

public class GameBoard implements Initializable {
    ObservableList<String> ChoiceMake = FXCollections.observableArrayList("Habitation", "usine");



    @FXML
    private GridPane Grid;



    @FXML

    String url = new File( "" ).getAbsolutePath();
    double maxHeight ;
    double maxWidth ;

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            Node source = (Node)e.getSource() ;
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
        }
    };



   void mapDisplayer(Map m){

       Point g =m.getMapSize();
       int w=g.getX();
       int h = g.getY();
   for(int i=0;i<w;i++) {
       for (int j=0;j<h;j++){
           PlotType type=m.getCasesTable(i,j).getType();

        switch (type) {

            case DIRT:
                ImageView img = new ImageView(url+"\\src\\main\\resources\\images\\dirt.JPG");
                img.setFitHeight(maxHeight);
                img.setFitWidth(maxWidth);
                img.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img,i,j);

                Label l1=new Label();
                l1.setText("dirt");
                Grid.add(l1,i,j);
                break;

            case CLAY:

                ImageView img2 = new ImageView(url+"\\src\\main\\resources\\images\\clay.JPG");
                img2.setFitHeight(maxHeight);
                img2.setFitWidth(maxWidth);
                img2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img2,i,j);




                Label l2=new Label();
                l2.setText("clay");
                Grid.add(l2,i,j);
                break;

            case GRASS:

                ImageView img3 = new ImageView(url+"\\src\\main\\resources\\images\\Grass.jpg");
                img3.setFitHeight(maxHeight);
                img3.setFitWidth(maxWidth);
                img3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img3,i,j);



                Label l3=new Label();
                l3.setText("grass");
                Grid.add(l3,i,j);
                break;

            case SAND:
                ImageView img4 = new ImageView(url+"\\src\\main\\resources\\images\\sand.jpg");
                img4.setFitHeight(maxHeight);
                img4.setFitWidth(maxWidth);
                img4.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img4,i,j);



                Label l4=new Label();
                l4.setText("sand");
                Grid.add(l4,i,j);
                break;

            case STONE:
                ImageView img5 = new ImageView(url+"\\src\\main\\resources\\images\\stone.jpg");
                img5.setFitHeight(maxHeight);
                img5.setFitWidth(maxWidth);
                img5.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img5,i,j);



                Label l5=new Label();
                l5.setText("stone");
                Grid.add(l5,i,j);
                break;

            case WATER:

                ImageView img6 = new ImageView(url+"\\src\\main\\resources\\images\\water.jpg");
                img6.setFitHeight(maxHeight);
                img6.setFitWidth(maxWidth);
                img6.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
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

    @FXML
   void getGridSize(){
       Rectangle2D screenBounds = Screen.getPrimary().getBounds();
       Grid.setMaxHeight(screenBounds.getHeight()-90);
       Grid.setMaxWidth(screenBounds.getWidth()-60);
        maxHeight = ((Grid.getMaxHeight())/14);
        maxWidth = ((Grid.getMaxWidth())/26);
   }
    /*
   @FXML
   void test(){
       //e.getEventType();
       //System.out.println(e.getSource());
       System.out.println(Grid.onMouseClickedProperty());
   }*/




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getGridSize();

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
