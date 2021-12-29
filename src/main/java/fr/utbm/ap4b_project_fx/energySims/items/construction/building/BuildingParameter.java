package fr.utbm.ap4b_project_fx.energySims.items.construction.building;

import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;

public class BuildingParameter {

    private final Resource buildingCost;
    private final float pollution;
    private final double electricityProduction;
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

    public double getElectricityProduction() {
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
