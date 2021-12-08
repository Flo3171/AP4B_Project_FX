package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingParameter {

    private final Resource buildingCost;
    private final float pollution;
    private final float electricityProduction;
    private final Resource input;
    private final Resource output;
    private final int inhabitantAmount;
    private final int workerAmount;

    public BuildingParameter(Resource buildingCost, float pollution, float electricityProduction, Resource input, Resource output, int inhabitantAmount, int workerAmount) {
        this.buildingCost = buildingCost;
        this.pollution = pollution;
        this.electricityProduction = electricityProduction;
        this.input = input;
        this.output = output;
        this.inhabitantAmount = inhabitantAmount;
        this.workerAmount = workerAmount;
    }

    public Resource getBuildingCost() {
        return buildingCost;
    }

    public float getPollution() {
        return pollution;
    }

    public float getElectricityProduction() {
        return electricityProduction;
    }

    public Resource getInput() {
        return input;
    }

    public Resource getOutput() {
        return output;
    }

    public int getInhabitantAmount() {
        return inhabitantAmount;
    }

    public int getWorkerAmount() {
        return workerAmount;
    }
}
