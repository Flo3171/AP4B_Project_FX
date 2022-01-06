package fr.utbm.ap4b_project_fx.energySims.controller;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.land.PlotType;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.controller.InGameMenu;
import fr.utbm.ap4b_project_fx.energySims.controller.MainMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;

import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

public class GameBoard implements Initializable {



    boolean firstClickContruct=true;
    boolean isFirstClickDestroy=true;
    boolean contructionMode=false;
    boolean destructionMode=false;

    @FXML
    private Label wood;

    @FXML
    private GridPane Grid;

    @FXML
    private Button Construction;


    @FXML
    private Button destroy;

    @FXML

    String url = new File( "" ).getAbsolutePath();
    double maxHeight ;
    double maxWidth ;


    @FXML
    private BorderPane scene;

    @FXML
    void menu(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.M) {

            try {

                URL fxmlURL = getClass().getResource("/fr/utbm/info/ap4b_project_fx/InGameMenu.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
                Parent root = fxmlLoader.load();

                InGameMenu menu = fxmlLoader.getController();

                Stage stage = new Stage();

                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Wejgame");
                stage.setScene(new Scene(root));
                stage.setFullScreen(false);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    EventHandler<Event> eventHandler = new EventHandler<Event>() {


        @Override
        public void handle(Event e) {

            Node source = (Node)e.getSource() ;
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());

            if(contructionMode==true)
            {
                Stage stage =new Stage();
                Label label = new Label();
                FlowPane root = new FlowPane();

                ChoiceBox choiceBox = new ChoiceBox();
                choiceBox.setValue("HOUSE");

                //choiceBox.getItems().setAll(ConstructionType.values());
               // ObservableList<?> languages = FXCollections.observableArrayList("TREE", "PYLON", "PIPE", "ROAD", "HOUSE", "NUCLEAR_PLANT", "COAL_PLANT", "GAZ_PLANT", "OIL_PLANT", "WINDMILL", "SOLAR_PANEL", "DRILLER");
                choiceBox.setItems(FXCollections.observableArrayList("TREE", "PYLON", "PIPE", "ROAD", "HOUSE", "NUCLEAR_PLANT", "COAL_PLANT","GAZ_PLANT", "OIL_PLANT", "WINDMILL", "SOLAR_PANEL", "DRILLER"));
                Button button=new Button();
                button.setText("validate");
                button.setVisible(true);


                MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.WOOD));
                MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.COPPER));
                MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.COAL));
                MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.WATER));
                MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.IRON));
                MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.URANIUM));
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        String choiceValue=choiceBox.getValue().toString();
                        ConstructionType type=ConstructionType.valueOf(choiceValue);
                        System.out.printf(type.toString());
                        buildingBuilder(MainMenu.getMap(),new Point(colIndex,rowIndex),type);
                        stage.close();
                        wood.setText(MainMenu.getMap().getInventory().toString());
                    }

                });

                root.setPadding(new Insets(10));

                root.getChildren().addAll(label, choiceBox, button);
                root.setPadding(new Insets(10));
                root.setHgap(10);




                Scene scene = new Scene(root, 400, 200);

                stage.setTitle("ff");
                stage.setScene(scene);
                stage.show();

            }
            else if (destructionMode==true)
            {
                MainMenu.getMap().destroyConstruction(new Point(colIndex,rowIndex));



               Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="house");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="oil");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="nuclear");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="wind");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="coal");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="drill");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="pylon");
                Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="tree");


            }

        }

    };

    @FXML
    void ContructMod(ActionEvent event) {

        if(firstClickContruct==true && destructionMode==false)
        {
            contructionMode=true;
            firstClickContruct=false;
            Grid.setDisable(false);
        }
        else
        {
            firstClickContruct=true;
            contructionMode=false;
            Grid.setDisable(true);
        }

    }

    @FXML
    void destroyMod(ActionEvent event) {
        if(isFirstClickDestroy==true && contructionMode==false)
        {
            destructionMode=true;
            isFirstClickDestroy=false;
            Grid.setDisable(false);
        }
        else
        {
            isFirstClickDestroy=true;
            destructionMode=false;
            Grid.setDisable(true);
        }
    }



    void buildingBuilder(Map m, Point pos, ConstructionType type)
    {

        if(m.build(pos,type)==true)
        {
            m.build(pos,type);
            buildingDisplayer(type,pos);
            System.out.printf("GREAT SUCESS");
        }
        else
        {
            System.out.printf("you cant PUT THAT HERE WTF BRO");
        }
    }



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
                img.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
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
            try {
                ConstructionType treeType=m.getCasesTable(i,j).getConstruction().getConstructionType();
                if (treeType==ConstructionType.TREE)
                {
                    ImageView img9 = new ImageView(url+"\\src\\main\\resources\\images\\Tree.png");
                    img9.setId("tree");
                    img9.setFitHeight(maxHeight);
                    img9.setFitWidth(maxWidth);
                    img9.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                    Grid.add(img9,i,j);
                }
            }catch (NullPointerException e){

            }



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

   void buildingDisplayer(ConstructionType type,Point pos)
   {
       switch (type)
       {
           case HOUSE:
              /* Label l10=new Label();
               l10.setText("HOUSE");
               Grid.add(l10,pos.getX(),pos.getY());*/

               ImageView img1 = new ImageView(url+"\\src\\main\\resources\\images\\house.png");
               img1.setId("house");
               img1.setFitHeight(maxHeight);
               img1.setFitWidth(maxWidth);
               img1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img1,pos.getX(),pos.getY());

               break;

           case PYLON:
               ImageView img2 = new ImageView(url+"\\src\\main\\resources\\images\\pylon.png");
               img2.setId("pylon");
               img2.setFitHeight(maxHeight);
               img2.setFitWidth(maxWidth);
               img2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img2,pos.getX(),pos.getY());
               break;

           case DRILLER:
               ImageView img3 = new ImageView(url+"\\src\\main\\resources\\images\\drill.png");
               img3.setId("drill");
               img3.setFitHeight(maxHeight);
               img3.setFitWidth(maxWidth);
               img3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img3,pos.getX(),pos.getY());
               break;

           case SOLAR_PANEL:

           case OIL_PLANT:
               ImageView img5 = new ImageView(url+"\\src\\main\\resources\\images\\oil.png");
               img5.setId("oil");
               img5.setFitHeight(maxHeight);
               img5.setFitWidth(maxWidth);
               img5.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img5,pos.getX(),pos.getY());

               break;
           case NUCLEAR_PLANT:
               ImageView img6 = new ImageView(url+"\\src\\main\\resources\\images\\nuclear.png");
               img6.setId("nuclear");
               img6.setFitHeight(maxHeight);
               img6.setFitWidth(maxWidth);
               img6.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img6,pos.getX(),pos.getY());
               break;

           case WINDMILL:
               ImageView img7 = new ImageView(url+"\\src\\main\\resources\\images\\wind.png");
               img7.setId("wind");
               img7.setFitHeight(maxHeight);
               img7.setFitWidth(maxWidth);
               img7.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img7,pos.getX(),pos.getY());
               break;

           case GAZ_PLANT:

           case PIPE:

           case COAL_PLANT:
               ImageView img10 = new ImageView(url+"\\src\\main\\resources\\images\\coal.png");
               img10.setId("CP");
               img10.setFitHeight(maxHeight);
               img10.setFitWidth(maxWidth);
               img10.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img10,pos.getX(),pos.getY());

               break;
           case ROAD:

           case TREE:

           default:
               Label l11=new Label();
               l11.setText("FORFAIT");
               Grid.add(l11,pos.getX(),pos.getY());
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
        Grid.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);


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
