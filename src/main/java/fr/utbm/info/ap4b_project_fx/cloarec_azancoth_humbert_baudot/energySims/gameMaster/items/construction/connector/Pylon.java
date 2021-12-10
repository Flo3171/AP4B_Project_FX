package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building.Building;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class Pylon extends Construction {

    private List<Pylon> pylonNeighbours;
    private List<Building> buildingNeighbours;

    public Pylon(Point position){
        super(position, ConstructionType.PYLON, new Resource(2, ResourceType.COPPER));
        this.pylonNeighbours = new ArrayList<>();
        this.buildingNeighbours = new ArrayList<>();

    }

    public void updateNeighbours(Map map){
        for (int i = 0; i < map.getMapHeight(); i++) {
            for (int j = 0; j < map.getMapWidth(); j++) {
                Construction otherConstruction = map.getCasesTable(j,i).getConstruction();
                if (otherConstruction == null || otherConstruction == this ){}
                else if ((otherConstruction instanceof Pylon) && this.getPosition().dist(j, i) <= 4){
                    this.addPylonNeighbours((Pylon) otherConstruction);
                }
                else if ((otherConstruction instanceof Building building) && this.getPosition().dist(j, i) < 2){
                    this.addBuildingNeighbours(building);
                    building.setPylonLink(this);

                }


            }
        }
    }


    public void addPylonNeighbours(Pylon newElem){
        if (!this.pylonNeighbours.contains(newElem)){
            this.pylonNeighbours.add(newElem);
        }
    }

    public void addBuildingNeighbours(Building newElem){
        if(!this.buildingNeighbours.contains(newElem)){
            this.buildingNeighbours.add(newElem);
        }
    }

    public List<Pylon> getConnectorNeighbours() {
        return pylonNeighbours;
    }

    public List<Building> getBuildingNeighbours() {
        return buildingNeighbours;
    }

    @Override
    public boolean build(Point position) {
        return true;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean destroy() {
        return true;
    }
}
