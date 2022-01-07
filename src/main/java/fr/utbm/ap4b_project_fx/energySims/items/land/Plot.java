package fr.utbm.ap4b_project_fx.energySims.items.land;


import fr.utbm.ap4b_project_fx.energySims.items.construction.Construction;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.construction.Tree;
import fr.utbm.ap4b_project_fx.energySims.items.construction.building.*;
import fr.utbm.ap4b_project_fx.energySims.items.construction.connector.Pylon;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.Road;

import java.util.Random;

/**
 * class that represent a case on the game map
 *
 * @author Florian CLOAREC
 */
public class Plot {

    private PlotType type = PlotType.DRY_PLOT;
    private boolean buildable = true;
    private final Point position;
    private Construction construction = null;
    private Resource undergroundResources = null;
    private Thread buildingThread;

    public Plot(Point position, boolean debug){
        this.position = position;
        if (debug){
            this.construction = null;
            this.undergroundResources = null;
        }
        else {
            generate();
        }


    }

    private synchronized void generate(){
        Random r = new Random();
        switch (r.nextInt((20))){
            case 0,1:{
                this.type = PlotType.WATER;
                this.setUndergroundResources(new Resource(200, ResourceType.WATER));

            }
            case 2, 3, 4:{
                this.type = PlotType.GRASS;
                this.construction = new Tree(this.position);
                this.buildable = false;
            }
            case 5, 6, 7, 8, 9:{
                this.setUndergroundResources(new Resource(200, ResourceType.IRON));
                this.type = PlotType.IRON_DEPOSIT;
            }
            case 10, 11, 12, 13, 14:{
                this.setUndergroundResources(new Resource(200,  ResourceType.COAL));
                this.type = PlotType.COAL_DEPOSIT;
            }
            case 15, 16, 17:{
                this.setUndergroundResources(new Resource(200, ResourceType.COPPER));
                this.type = PlotType.COAL_DEPOSIT;
            }
            case 18, 19:{
                this.setUndergroundResources(new Resource(200, ResourceType.URANIUM));
                this.type = PlotType.URANIUM_DEPOSIT;
            }
        }
    }

    public Plot(Point position, String fileContent){
        this.position = position;
        String[] strings = fileContent.split(",");
        this.type = PlotType.getPlotType(strings[0]);
        this.buildable = Boolean.getBoolean(strings[1]);
        this.construction = null;
        build(ConstructionType.getConstructionType(strings[2]), null);
        this.undergroundResources = new Resource(strings[3]);
    }


    @Override
    public String toString() {
       String constructionString;
       if (this.construction != null){
           constructionString = this.construction.toString();
       }else {
           constructionString = "null";
       }

       String undergroundResourcesString;
       if (this.undergroundResources != null){
           undergroundResourcesString = this.undergroundResources.toString();

       }
       else undergroundResourcesString = "null:0";

       String plotTypeString;
       if (this.type != null){
           plotTypeString = this.type.toString();
       }
       else plotTypeString = "null";


        return plotTypeString + "," +
                this.buildable + "," +
                constructionString + "," +
                undergroundResourcesString;

    }

    public Construction getConstruction() {
        return construction;
    }

    public PlotType getType() {
        return type;
    }


    public void setUndergroundResources(Resource undergroundResources) {
        this.undergroundResources = undergroundResources;
    }

    public boolean build(ConstructionType constructionType, Inventory inventory){
        if(constructionType == null){
            return false;
        } else if (inventory == null || this.buildable){
            Construction newConstruction;
            switch (constructionType){
                case TREE -> newConstruction = new Tree(this.position);
                case PYLON -> newConstruction = new Pylon(this.position);
                case HOUSE -> newConstruction = new House(this.position, inventory);
                case NUCLEAR_PLANT -> newConstruction = new NuclearPlant(this.position, inventory);
                case FARM -> newConstruction = new Farm(this.position, inventory);
                case COAL_PLANT -> newConstruction = new CoalPlant(this.position, inventory);
                case WINDMILL -> newConstruction = new WindMill(this.position, inventory);
                case SOLAR_PANEL -> newConstruction = new SolarPanel(this.position, inventory);
                case DRILLER -> newConstruction = new Driller(this.position, inventory, this);
                case ENTERTAINMENT_PARK -> newConstruction = new EntertainmentPark(this.position, inventory);
                default -> newConstruction = new Road((this.position));

            }

            if (inventory == null || inventory.useResource(newConstruction.getConstructionCost())){
                this.construction = newConstruction;
                if (this.construction instanceof Building){
                    this.buildingThread = new Thread((Building) this.construction);
                    this.buildingThread.setName("Thread_" + constructionType + this.position.toString());
                    this.buildingThread.start();
                }
                this.buildable = false;
                return true;
            }
        }
        return false;

    }

    public void updateNeighbour(Map map){
        if (this.construction != null && this.construction instanceof Pylon){
            ((Pylon) this.construction).updateNeighbours(map);
        }
    }

    public boolean destroy(Inventory inventory){

        boolean result = this.construction.destroy();
        if (result){
            inventory.addResource(this.construction.getDestructionReward());
            this.buildable = true;
            this.close();
            this.construction = null;

        }

        return result;

    }

    public void close(){
        if (this.construction != null && this.construction instanceof Building){
            ((Building) this.construction).stop();
        }
    }

    public Resource getUndergroundResources() {
        return undergroundResources;
    }

    public void dry(){
        this.type = PlotType.DRY_PLOT;
    }
}
