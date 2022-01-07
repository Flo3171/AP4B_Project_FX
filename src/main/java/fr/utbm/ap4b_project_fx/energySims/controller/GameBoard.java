package fr.utbm.ap4b_project_fx.energySims.controller;


import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.land.Plot;
import fr.utbm.ap4b_project_fx.energySims.items.land.PlotType;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

import javafx.collections.FXCollections;
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

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class GameBoard implements Initializable {



    boolean firstClickContruct=true;
    boolean isFirstClickDestroy=true;
    boolean contructionMode=false;
    boolean destructionMode=false;

    private boolean CoalConstuct;
    private boolean DrillerConstuct;
    private boolean FarmConstuct;
    private boolean HouseConstuct;
    private boolean NuclearConstuct;
    private boolean ParkConstuct;
    private boolean PylonConstuct;
    private boolean RoadConstuct;
    private boolean SolarConstuct;
    private boolean TreeConstuct;
    private boolean WindConstuct;

    @FXML
    private Button BCoal;

    @FXML
    private Button BDriller;

    @FXML
    private Button BFarm;

    @FXML
    private Button BHouse;

    @FXML
    private Button BNuclear;

    @FXML
    private Button BPark;

    @FXML
    private Button BPylon;

    @FXML
    private Button BRoad;

    @FXML
    private Button BSolar;

    @FXML
    private Button BTree;

    @FXML
    private Button BWind;

    @FXML
    private Label LCoal;

    @FXML
    private Label LCopper;

    @FXML
    private Label LError;

    @FXML
    private Label LFood;

    @FXML
    private Label LIron;

    @FXML
    private Label LNone;

    @FXML
    private Label LSatisfaction;

    @FXML
    private Label LUranium;

    @FXML
    private Label LWater;

    @FXML
    private Label LWood;

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
    void menu() throws IOException {

        URL fxmlURL = getClass().getResource("/fr/utbm/info/ap4b_project_fx/InGameMenu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        Parent root = fxmlLoader.load();


        Stage stage = new Stage();

        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wejgame");
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.showAndWait();

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
                /*
                Stage stage =new Stage();
                Label label = new Label();
                FlowPane root = new FlowPane();

                ChoiceBox choiceBox = new ChoiceBox();
                choiceBox.setValue("HOUSE");

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
                */
                ConstructionType type = typeConstruct();
                if (type != null){
                    MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.WOOD));
                    MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.COPPER));
                    MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.COAL));
                    MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.WATER));
                    MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.IRON));
                    MainMenu.getMap().getInventory().addResource(new Resource(10000, ResourceType.URANIUM));

                    buildingBuilder(MainMenu.getMap(),new Point(colIndex,rowIndex),type);

                }

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
               Grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node)==colIndex && node.getId()=="Route");


            }
            updateInfo();

        }

    };

    void updateInfo(){
        Resource res[] = MainMenu.getMap().getInventory().getResources();
        LCoal.setText(res[2].getType()  + " : " + res[2].getAmount());
        LCopper.setText(res[6].getType()  + " : " + res[6].getAmount());
        LFood.setText(res[5].getType()  + " : " + res[5].getAmount());
        LIron.setText(res[3].getType()  + " : " + res[3].getAmount());
        LNone.setText(res[0].getType()  + " : " + res[0].getAmount());
        LSatisfaction.setText(res[8].getType()  + " : " + res[8].getAmount());
        LUranium.setText(res[7].getType()  + " : " + res[7].getAmount());
        LWater.setText(res[4].getType()  + " : " + res[4].getAmount());
        LWood.setText(res[1].getType()  + " : " + res[1].getAmount());
    }
    @FXML
    void whatConstruct(ActionEvent event){
        final Node source = (Node) event.getSource();
        String id = source.getId();

        if(id.equals("BCoal") && !CoalConstuct) {
            CoalConstuct = true;
            BCoal.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            CoalConstuct = false;
            BCoal.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BDriller") && !DrillerConstuct) {
            DrillerConstuct = true;
            BDriller.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            DrillerConstuct = false;
            BDriller.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BFarm") && !FarmConstuct) {
            FarmConstuct = true;
            BFarm.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            FarmConstuct = false;
            BFarm.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BHouse") && !HouseConstuct) {
            HouseConstuct = true;
            BHouse.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            HouseConstuct = false;
            BHouse.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BNuclear") && !NuclearConstuct) {
            NuclearConstuct = true;
            BNuclear.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            NuclearConstuct = false;
            BNuclear.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BPark") && !ParkConstuct) {
            ParkConstuct = true;
            BPark.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            ParkConstuct = false;
            BPark.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BPylon") && !PylonConstuct) {
            PylonConstuct = true;
            BPylon.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            PylonConstuct = false;
            BPylon.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BRoad") && !RoadConstuct) {
            RoadConstuct = true;
            BRoad.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            RoadConstuct = false;
            BRoad.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BSolar") && !SolarConstuct) {
            SolarConstuct = true;
            BSolar.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            SolarConstuct = false;
            BSolar.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BTree") && !TreeConstuct) {
            TreeConstuct = true;
            BTree.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            TreeConstuct = false;
            BTree.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        if(id.equals("BWind") && !WindConstuct) {
            WindConstuct = true;
            BWind.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }else {
            WindConstuct = false;
            BWind.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
    }

    @FXML
    void ContructMod(ActionEvent event) {

        if(firstClickContruct==true && destructionMode==false)
        {
            contructionMode=true;
            firstClickContruct=false;
            Grid.setDisable(false);
            Construction.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
            BCoal.setDisable(false);
            BDriller.setDisable(false);
            BFarm.setDisable(false);
            BHouse.setDisable(false);
            BNuclear.setDisable(false);
            BPark.setDisable(false);
            BPylon.setDisable(false);
            BRoad.setDisable(false);
            BSolar.setDisable(false);
            BTree.setDisable(false);
            BWind.setDisable(false);
        }
        else
        {
            firstClickContruct=true;
            contructionMode=false;
            Grid.setDisable(true);
            Construction.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
            BCoal.setDisable(true);
            BDriller.setDisable(true);
            BFarm.setDisable(true);
            BHouse.setDisable(true);
            BNuclear.setDisable(true);
            BPark.setDisable(true);
            BPylon.setDisable(true);
            BRoad.setDisable(true);
            BSolar.setDisable(true);
            BTree.setDisable(true);
            BWind.setDisable(true);
        }

    }

    @FXML
    void destroyMod(ActionEvent event) {
        if(isFirstClickDestroy==true && contructionMode==false)
        {
            destructionMode=true;
            isFirstClickDestroy=false;
            Grid.setDisable(false);
            destroy.setStyle("-fx-background-color: #2bef4f ; -fx-border-color: black; -fx-border-radius: 4;");
        }
        else
        {
            isFirstClickDestroy=true;
            destructionMode=false;
            Grid.setDisable(true);
            destroy.setStyle("-fx-background-color: #cefffd ; -fx-border-color: black; -fx-border-radius: 4;");
        }
    }

    void buildingBuilder(Map m, Point pos, ConstructionType type) {

        if(m.build(pos,type)==true)
        {
            m.build(pos,type);
            buildingDisplayer(type,pos);
            LError.setText("");
        }
        else
        {
            LError.setText("impossible à placer");
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

            case IRON_DEPOSIT:
                ImageView img = new ImageView(url+"\\src\\main\\resources\\images\\iron.JPG");
                img.setFitHeight(maxHeight);
                img.setFitWidth(maxWidth);
                img.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                img.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
                Grid.add(img,i,j);
                try {
                    buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}

                break;

            case COAL_DEPOSIT:

                ImageView img2 = new ImageView(url+"\\src\\main\\resources\\images\\coal_s.png");
                img2.setFitHeight(maxHeight);
                img2.setFitWidth(maxWidth);
                img2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img2,i,j);
                try {
                buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}



                break;

            case GRASS:

                ImageView img3 = new ImageView(url+"\\src\\main\\resources\\images\\Grass.jpg");
                img3.setFitHeight(maxHeight);
                img3.setFitWidth(maxWidth);
                img3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img3,i,j);
                try {
                    buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}


                break;

            case COPPER_DEPOSIT:
                ImageView img4 = new ImageView(url+"\\src\\main\\resources\\images\\copper.jpg");
                img4.setFitHeight(maxHeight);
                img4.setFitWidth(maxWidth);
                img4.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img4,i,j);
                try {
                    buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}

                break;


            case URANIUM_DEPOSIT:
                ImageView img5 = new ImageView(url+"\\src\\main\\resources\\images\\uranium.png");
                img5.setFitHeight(maxHeight);
                img5.setFitWidth(maxWidth);
                img5.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img5,i,j);
                try {
                    buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}

                break;

            case WATER:

                ImageView img6 = new ImageView(url+"\\src\\main\\resources\\images\\water.jpg");
                img6.setFitHeight(maxHeight);
                img6.setFitWidth(maxWidth);
                img6.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img6,i,j);
                try {
                    buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}

                break;

            case DRY_PLOT:
                ImageView img7 = new ImageView(url+"\\src\\main\\resources\\images\\sand.jpg");
                img7.setFitHeight(maxHeight);
                img7.setFitWidth(maxWidth);
                img7.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                Grid.add(img7,i,j);
                try {
                    buildingDisplayer(m.getCasesTable(i,j).getConstruction().getConstructionType(),new Point(i,j));
                }catch (NullPointerException e){}
                break;

            default:
                Label l8=new Label();
                l8.setText("ERROR");
                Grid.add(l8,i,j);;
            }
       }
     }
   }

    ConstructionType typeConstruct(){

        if(CoalConstuct) {
            return ConstructionType.COAL_PLANT;
        }else if(DrillerConstuct) {
            return ConstructionType.DRILLER;
        }else if(FarmConstuct) {
            return ConstructionType.FARM;
        }else if(HouseConstuct) {
            return ConstructionType.HOUSE;
        }else if(NuclearConstuct) {
            return ConstructionType.NUCLEAR_PLANT;
        }else if(ParkConstuct) {
            return ConstructionType.ENTERTAINMENT_PARK;
        }else if(PylonConstuct) {
            return ConstructionType.PYLON;
        }else if(RoadConstuct) {
            return ConstructionType.ROAD;
        }else if(SolarConstuct) {
            return ConstructionType.SOLAR_PANEL;
        }else if(TreeConstuct) {
            return ConstructionType.TREE;
        }else if(WindConstuct) {
            return ConstructionType.WINDMILL;
        }else
            return null;
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
               ImageView img4 = new ImageView(url+"\\src\\main\\resources\\images\\solar.png");
               img4.setId("nuclear");
               img4.setFitHeight(maxHeight);
               img4.setFitWidth(maxWidth);
               img4.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img4,pos.getX(),pos.getY());
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


           case COAL_PLANT:
               ImageView img10 = new ImageView(url+"\\src\\main\\resources\\images\\coal.png");
               img10.setId("CP");
               img10.setFitHeight(maxHeight);
               img10.setFitWidth(maxWidth);
               img10.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img10,pos.getX(),pos.getY());
               break;

           case ROAD:
               ImageView img8 = new ImageView(url+"\\src\\main\\resources\\images\\Route.png");
               img8.setId("Route");
               img8.setFitHeight(maxHeight);
               img8.setFitWidth(maxWidth);
               img8.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img8,pos.getX(),pos.getY());
               break;

           case TREE:
               ImageView img9 = new ImageView(url+"\\src\\main\\resources\\images\\Tree.png");
               img9.setId("tree");
               img9.setFitHeight(maxHeight);
               img9.setFitWidth(maxWidth);
               img9.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img9,pos.getX(),pos.getY());
               break;

           case FARM:
               ImageView img11 = new ImageView(url+"\\src\\main\\resources\\images\\Farm.png");
               img11.setId("tree");
               img11.setFitHeight(maxHeight);
               img11.setFitWidth(maxWidth);
               img11.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img11,pos.getX(),pos.getY());
               break;

           case ENTERTAINMENT_PARK:
               Random rand = new Random();
               ImageView img12;
               if(rand.nextInt(2)==0){
                   img12 = new ImageView(url+"\\src\\main\\resources\\images\\Park1.png");
               }else {
                   img12 = new ImageView(url+"\\src\\main\\resources\\images\\Park2.png");
               }
               img12.setId("tree");
               img12.setFitHeight(maxHeight);
               img12.setFitWidth(maxWidth);
               img12.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
               Grid.add(img12,pos.getX(),pos.getY());
               break;

           default:

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
