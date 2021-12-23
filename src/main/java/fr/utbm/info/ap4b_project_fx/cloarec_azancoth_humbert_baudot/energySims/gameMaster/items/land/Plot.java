package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Road;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Tree;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building.*;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector.Pylon;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Inventory;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;

import java.util.Random;

/**
 * class that represent a case on the game map
 *
 * @author Florian CLOAREC
 */
public class Plot {

    private final PlotType type;
    private boolean buildable;
    private final Point position;
    private Construction construction;
    private final Resource undergroundResources;

    public Plot(Point position, boolean debug){

        if (debug){
            this.type = PlotType.DIRT;
            this.buildable = true;
            this.position = position;
            this.construction = null;
            this.undergroundResources = null;
        }
        else {
            this.position = position;
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
                    this.construction = new Tree(this.position);
                    this.buildable = false;

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


    }

    @Override
    public String toString() {
       String constructionString;
       if (this.construction != null){
           constructionString = this.construction.toString();
       }else {
           constructionString = "";
       }

       String undergroundResourcesString;
       if (this.undergroundResources != null){
           undergroundResourcesString = this.undergroundResources.toString();

       }
       else undergroundResourcesString = "";
        String value = this.type.toString() + "," +
                this.buildable + "," +
                constructionString + "," +
                undergroundResourcesString;



       return value;

    }

    public Construction getConstruction() {
        return construction;
    }

    public PlotType getType() {
        return type;
    }



    public boolean build(ConstructionType constructionType, Inventory inventory){
        if (this.buildable){
            Construction newConstruction;
            switch (constructionType){
                case TREE -> newConstruction = new Tree(this.position);
                case PYLON -> newConstruction = new Pylon(this.position);
                case HOUSE -> newConstruction = new House(this.position);
                case NUCLEAR_PLANT -> newConstruction = new NuclearPlant(this.position);
                case COAL_PLANT -> newConstruction = new CoalPlant(this.position);
                case GAZ_PLANT -> newConstruction = new GazPlant(this.position);
                case OIL_PLANT -> newConstruction = new OilPlant(this.position);
                case WINDMILL -> newConstruction = new WindMill(this.position);
                case SOLAR_PANEL -> newConstruction = new SolarPanel(this.position);
                default -> newConstruction = new Road((this.position));

            }
            if (inventory.useResource(newConstruction.getConstructionCost())){
                this.construction = newConstruction;
                this.buildable = false;
                return true;
            }
        }
        return false;

    }

    public void update(){

        if (this.construction != null) {
            this.construction.update();
        }
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
            this.construction = null;

        }
        return result;

    }


}
