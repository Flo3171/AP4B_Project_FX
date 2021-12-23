package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building.Building;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;

import java.util.ArrayList;
import java.util.List;

public class ElectricalNetwork {
    private final List<Pylon> pylonList;
    private final List<Building> buildingList;

    private final double availableElectricity;

    public ElectricalNetwork(){
        this.pylonList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        this.availableElectricity = 0;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("ElectricalNetwork : availableElectricity = " + this.availableElectricity + "\n\tPylon :");
        for (Pylon pylon :
                this.pylonList) {
            value.append(pylon.toString()).append(pylon.getPosition().toString()).append(",  ");
        }
        value.append("\n\tBuilding :");
        for (Building building :
                this.buildingList) {
            value.append(building.toString()).append(building.getPosition().toString()).append(", ");
        }
        return value.toString();
    }

    public static List<ElectricalNetwork> updateNetwork(Map map){
        List<ElectricalNetwork> networkList = new ArrayList<>();

        for (int i = 0; i < map.getMapSize().getY(); i++) {
            for (int j = 0; j < map.getMapSize().getX(); j++) {
                Construction currentConstruction = map.getCasesTable(j,i).getConstruction();
                if (currentConstruction instanceof Pylon currentPylon){
                    if (!isInNetwork(currentPylon, networkList)){
                        ElectricalNetwork network = new ElectricalNetwork();
                        network.addPylon(currentPylon);
                        network.addPylonNeighbours(currentPylon);
                        networkList.add(network);
                    }
                }
            }
        }
        return networkList;
    }

    private void addPylon(Pylon pylon) {
        if (!this.pylonList.contains(pylon)) {
            this.pylonList.add(pylon);
            for (Building building :
                    pylon.getBuildingNeighbours()) {
                this.addBuilding(building);
            }
        }

    }

    private void addBuilding(Building building){
        if (!this.buildingList.contains(building)) {
            this.buildingList.add(building);
        }
    }

    private static boolean isInNetwork(Pylon pylon, List<ElectricalNetwork> networkList){
        boolean inNetwork = false;
        for (ElectricalNetwork network :
                networkList) {
            if (network.pylonList.contains(pylon)) {
                inNetwork = true;
                break;
            }
        }
        return inNetwork;
    }

    private boolean isInNetwork(Pylon pylon){
        return this.pylonList.contains(pylon);
    }

    private void addPylonNeighbours(Pylon pylon){
        for (Pylon neighbour :
                pylon.getConnectorNeighbours()) {
            if (!isInNetwork(neighbour)){
                this.addPylon(neighbour);
                this.addPylonNeighbours(neighbour);
            }
        }
    }
}
