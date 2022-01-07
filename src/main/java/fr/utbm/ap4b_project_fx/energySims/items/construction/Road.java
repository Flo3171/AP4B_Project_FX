package fr.utbm.ap4b_project_fx.energySims.items.construction;

import fr.utbm.ap4b_project_fx.energySims.items.construction.building.Building;
import fr.utbm.ap4b_project_fx.energySims.items.construction.connector.ElectricalNetwork;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Road extends Construction {

    private final List<Road> roadNeighbours;
    private final List<Building> buildingNeighbours;
    private final List<Point> potentialRoadNeighbours;
    private RoadNetwork network;

    public Road(Point position) {
        super(position, ConstructionType.ROAD, new Resource(2, ResourceType.COAL));
        this.roadNeighbours = new ArrayList<>();
        this.potentialRoadNeighbours = new ArrayList<>();
        this.buildingNeighbours = new ArrayList<>();
        this.addPotentialRoadNeighbours(new Point(0,3));
    }

    public void updateNeighbours(Map map){
        for (int i = 0; i < map.getMapSize().getY(); i++) {
            for (int j = 0; j < map.getMapSize().getX(); j++) {
                Construction otherConstruction = map.getCasesTable(j,i).getConstruction();
                if (otherConstruction != null && otherConstruction != this) {
                    if ((otherConstruction instanceof Road) && this.getPosition().dist(j, i) <= 4){
                        this.addRoadNeighbours((Road) otherConstruction);
                    }
                    else if ((otherConstruction instanceof Building building) && this.getPosition().dist(j, i) < 4){
                        this.addBuildingNeighbours(building);
                        //building.setPylonLink(this);

                    }
                }


            }
        }
    }

    public void addPotentialRoadNeighbours(Point newElem){
        if (!this.potentialRoadNeighbours.contains(newElem)){
            this.potentialRoadNeighbours.add(newElem);
        }
    }

    public void addRoadNeighbours(Road newElem){
        if (!this.roadNeighbours.contains(newElem)){
            this.roadNeighbours.add(newElem);
        }
    }

    public void addBuildingNeighbours(Building newElem){
        if(!this.buildingNeighbours.contains(newElem)){
            this.buildingNeighbours.add(newElem);
        }
    }

    public void setNetwork(RoadNetwork network) {
        this.network = network;
    }

    public List<Road> getRoadNeighbours() {
        return roadNeighbours;
    }

    public List<Point> getPotentialRoadNeighbours() {
        return potentialRoadNeighbours;
    }

    public List<Building> getBuildingNeighbours() {
        return buildingNeighbours;
    }

    public boolean build(Point position) {
        System.out.println(potentialRoadNeighbours.get(0));
        for (int i = 0; i<potentialRoadNeighbours.size(); i++) {
            //System.out.println("oooooooooooooooooooooooooooooooooooooooowiiiiiiiiiiiii:   " + potentialRoadNeighbours.get(i));
            if (position == potentialRoadNeighbours.get(i)) {
                System.out.println("oooooooooooooooooooooooooooooooooooooooowiiiiiiiiiiiii:   " + potentialRoadNeighbours.get(i));
                return true;
            }
        }
        if (this.roadNeighbours.size() >= 1 || (position.getX() == 0 && position.getY() == 0)) { //Cosmétique
            System.out.println("oooooooooooooooooooooooooooooooooooooooo");
            return true;
        } else {
            System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn: "+ this.getRoadNeighbours());
            return false;
        }
    }

    public boolean update() {
        return true;
    }

    public boolean destroy() {
        return true;
    }

    public RoadNetwork getNetwork() {
        return network;
    }

    @Override
    public Resource getDestructionReward() {
        return new Resource(11, ResourceType.COAL);
    }
}
