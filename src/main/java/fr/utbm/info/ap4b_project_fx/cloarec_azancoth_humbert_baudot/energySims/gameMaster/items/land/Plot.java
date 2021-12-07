package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Tree;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building.*;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector.Pylon;
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
    private Resource undergroundResources;

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
        if (this.construction != null) {
            return construction.toString() ;
        }
        else {
            return "XX";
        }

    }

    public Construction getConstruction() {
        return construction;
    }

    public void build(ConstructionType constructionType){
        switch (constructionType){
            case TREE -> this.construction = new Tree(this.position);
            case PYLON -> this.construction = new Pylon(this.position);
            case HOUSE -> this.construction = new House(this.position);
            case NUCLEAR_PLANT -> this.construction = new NuclearPlant(this.position);
            case COAL_PLANT -> this.construction = new CoalPlant(this.position);
            case GAZ_PLANT -> this.construction = new GazPlant(this.position);
            case OIL_PLANT -> this.construction = new OilPlant(this.position);
            case WINDMILL -> this.construction = new WindMill(this.position);
            case SOLAR_PANEL -> this.construction = new SolarPanel(this.position);

        }
        this.buildable = false;

    }

    void update(){

        if (this.construction != null) {
            this.construction.update();
        }
    }

    void updateNeighbour(Map map){
        if (this.construction != null && this.construction instanceof Pylon){
            ((Pylon) this.construction).updateNeighbours(map);
        }
    }

    Resource destroy(){
        Resource resource = this.construction.destroy();
        this.buildable = true;
        this.construction = null;
        return resource;
    }


}
