package fr.utbm.ap4b_project_fx.energySims.items.construction.connector;


import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.Construction;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.construction.building.Building;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class Pylon extends Construction {

    private final List<Pylon> pylonNeighbours;
    private final List<Building> buildingNeighbours;
    private ElectricalNetwork network;

    public Pylon(Point position){
        super(position, ConstructionType.PYLON, new Resource(2, ResourceType.WOOD));
        this.pylonNeighbours = new ArrayList<>();
        this.buildingNeighbours = new ArrayList<>();

    }

    public void updateNeighbours(Map map){
        for (int i = 0; i < map.getMapSize().getY(); i++) {
            for (int j = 0; j < map.getMapSize().getX(); j++) {
                Construction otherConstruction = map.getCasesTable(j,i).getConstruction();
                if (otherConstruction != null && otherConstruction != this) {
                    if ((otherConstruction instanceof Pylon) && this.getPosition().dist(j, i) <= 4){
                        this.addPylonNeighbours((Pylon) otherConstruction);
                    }
                    else if ((otherConstruction instanceof Building building) && this.getPosition().dist(j, i) < 2){
                        this.addBuildingNeighbours(building);
                        building.setPylonLink(this);

                    }
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

    public void setNetwork(ElectricalNetwork network) {
        this.network = network;
    }

    public List<Pylon> getConnectorNeighbours() {
        return pylonNeighbours;
    }

    public List<Building> getBuildingNeighbours() {
        return buildingNeighbours;
    }

    public boolean addElectricity(double electricityAmount){
        return this.network.addElectricity(electricityAmount);
    }

    public ElectricalNetwork getNetwork() {
        return network;
    }
}
