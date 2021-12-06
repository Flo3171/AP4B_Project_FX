package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;

public class House extends Building{

    private final int inhabitant;

    public House(Point position){
        super(2, 1, position);
        this.setConstructionType(ConstructionType.HOUSE);
        this.inhabitant = 4;
        this.getInput().add(new Resource(2, ResourceType.WATER));
        this.getOutput().add(new Resource(10, ResourceType.EUROS));
        this.getOutput().add(new Resource(2,  ResourceType.SATISFACTION));
    }



    @Override
    public void build(Point position) {

    }

    @Override
    public void update() {

    }

    @Override
    public Resource destroy() {
        return null;
    }
}
