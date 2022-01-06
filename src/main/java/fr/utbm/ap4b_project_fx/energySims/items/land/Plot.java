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

    private PlotType type;
    private boolean buildable;
    private final Point position;
    private Construction construction;
    private Resource undergroundResources;
    private Thread buildingThread;

    public Plot(Point position, boolean debug){
        this.position = position;
        if (debug){
            this.type = PlotType.DIRT;
            this.buildable = true;
            this.construction = null;
            this.undergroundResources = null;
        }
        else {
            generate();
        }


    }

    private synchronized void generate(){
        Random r = new Random();
        int pattern = r.nextInt((10));
        if (pattern <  9){
            /*
             * available to have undergroundResources
             */
            int resources = r.nextInt(3);
            if (resources == 0){

                int resourceType = r.nextInt(20);
                double resourceAmount = r.nextInt(91) + 10;
                if (resourceType < 4){
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.IRON);
                }
                else if (resourceType < 8){
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.COPPER);
                }
                else if (resourceType < 12){
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.COAL);
                }
                else if (resourceType < 13){
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.URANIUM);
                }
                else if (resourceType < 15){
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.WATER);
                }
                else if (resourceType < 18){
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.OIL);
                }
                else {
                    this.undergroundResources = new Resource(resourceAmount, ResourceType.GAS);
                }

            }
            else{
                this.undergroundResources = null;
            }

            this.buildable = true;
            this.construction = null;

            if (pattern < 3){
                this.type = PlotType.GRASS;
                if (pattern == 0){
                    this.construction = new Tree(this.position);
                    this.buildable = false;
                }


            }
            else if (pattern < 4){
                this.type = PlotType.SAND;
            }
            else if (pattern < 6){
                this.type = PlotType.DIRT;
            }
            else if (pattern < 8){
                this.type = PlotType.STONE;
            }
            else {
                this.type = PlotType.CLAY;
            }


        }
        else{
            this.undergroundResources = new Resource(r.nextInt(64-10+1)+10, ResourceType.WATER);
            this.buildable = false;
            this.type = PlotType.WATER;
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
                case COAL_PLANT -> newConstruction = new CoalPlant(this.position, inventory);
                case GAZ_PLANT -> newConstruction = new GazPlant(this.position, inventory);
                case OIL_PLANT -> newConstruction = new OilPlant(this.position, inventory);
                case WINDMILL -> newConstruction = new WindMill(this.position, inventory);
                case SOLAR_PANEL -> newConstruction = new SolarPanel(this.position, inventory);
                case DRILLER -> newConstruction = new Driller(this.position, inventory, this);
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
