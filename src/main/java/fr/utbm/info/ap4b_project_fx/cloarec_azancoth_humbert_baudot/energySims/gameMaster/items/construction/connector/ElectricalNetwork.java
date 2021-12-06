package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building.Building;

import java.util.ArrayList;
import java.util.List;

public class ElectricalNetwork {
    private List<Pylon> pylonList;
    private List<Building> buildingList;

    private double availableElectricity;

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
}
