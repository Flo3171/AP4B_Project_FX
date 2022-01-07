package fr.utbm.ap4b_project_fx.energySims.items.construction;


import fr.utbm.ap4b_project_fx.energySims.items.construction.building.Building;
import fr.utbm.ap4b_project_fx.energySims.items.construction.Road;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class RoadNetwork {
    private final List<Road> roadList;
    private final List<Building> buildingList;
    //private final List<Point> potentialNeighboursList;

    public RoadNetwork(){
        this.roadList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        //this.potentialNeighboursList = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("RoadNetwork : " + "\n\tRoad :");
        for (Road road :
                this.roadList) {
            value.append(road.toString()).append(road.getPosition().toString()).append(",  ");
        }
        value.append("\n\tBuilding :");
        for (Building building :
                this.buildingList) {
            value.append(building.toString()).append(building.getPosition().toString()).append(", ");
        }
        return value.toString();
    }

    public static List<RoadNetwork> updateNetwork(Map map){
        List<RoadNetwork> networkList = new ArrayList<>();

        for (int i = 0; i < map.getMapSize().getY(); i++) {
            for (int j = 0; j < map.getMapSize().getX(); j++) {
                Construction currentConstruction = map.getCasesTable(j,i).getConstruction();
                if (currentConstruction instanceof Road currentRoad){
                    if (!isInNetwork(currentRoad, networkList)){
                        RoadNetwork network = new RoadNetwork();
                        network.addRoad(currentRoad);
                        network.addRoadNeighbours(currentRoad);
                        networkList.add(network);
                    }
                }
            }
        }
        System.out.println("NETWORK LIST: " + networkList);
        return networkList;
    }

    public static List<Point> addPotentialNeighbours(Map map, List<RoadNetwork> networkList){
        List<Point> potentialNeighboursList = new ArrayList<>();

        for (int i = 0; i < map.getMapSize().getY(); i++) {
            for (int j = 0; j < map.getMapSize().getX(); j++) {
                Construction currentConstruction = map.getCasesTable(j,i).getConstruction();
                if (currentConstruction instanceof Road currentRoad){
                    potentialNeighboursList.add(new Point(currentRoad.getPosition().getX() + 1, currentConstruction.getPosition().getY()));
                    currentRoad.addPotentialRoadNeighbours(new Point(currentRoad.getPosition().getX() + 1, currentConstruction.getPosition().getY()));
                    potentialNeighboursList.add(new Point(currentRoad.getPosition().getX(), currentConstruction.getPosition().getY() + 1));
                    currentRoad.addPotentialRoadNeighbours(new Point(currentRoad.getPosition().getX(), currentConstruction.getPosition().getY() + 1));
                    potentialNeighboursList.add(new Point(currentRoad.getPosition().getX() - 1, currentConstruction.getPosition().getY()));
                    currentRoad.addPotentialRoadNeighbours(new Point(currentRoad.getPosition().getX() - 1, currentConstruction.getPosition().getY()));
                    potentialNeighboursList.add(new Point(currentRoad.getPosition().getX(), currentConstruction.getPosition().getY() - 1));
                    currentRoad.addPotentialRoadNeighbours(new Point(currentRoad.getPosition().getX(), currentConstruction.getPosition().getY() + 1));
                    System.out.println(currentRoad.getPotentialRoadNeighbours());
                }
            }
        }
        //System.out.println(potentialNeighboursList);
        return potentialNeighboursList;
    }

    private void addRoad(Road road) {
        if (!this.roadList.contains(road)) {
            this.roadList.add(road);
            road.setNetwork(this);
            for (Building building :
                    road.getBuildingNeighbours()) {
                this.addBuilding(building);
            }
        }

    }

    private void addBuilding(Building building){
        if (!this.buildingList.contains(building)) {
            this.buildingList.add(building);
        }
    }

    private static boolean isInNetwork(Road road, List<RoadNetwork> networkList){
        boolean inNetwork = false;
        for (RoadNetwork network :
                networkList) {
            if (network.roadList.contains(road)) {
                inNetwork = true;
                break;
            }
        }
        return inNetwork;
    }

    private boolean isInNetwork(Road road){
        return this.roadList.contains(road);
    }

    private void addRoadNeighbours(Road road){
        for (Road neighbour :
                road.getRoadNeighbours()) {
            if (!isInNetwork(neighbour)){
                this.addRoad(neighbour);
                this.addRoadNeighbours(neighbour);
            }
        }
    }

    private void addPotentialRoadNeighbours(Road road, Point point){
        road.addPotentialRoadNeighbours(point);
    }
}
