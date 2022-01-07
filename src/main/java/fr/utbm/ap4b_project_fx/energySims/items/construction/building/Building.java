package fr.utbm.ap4b_project_fx.energySims.items.construction.building;


import fr.utbm.ap4b_project_fx.energySims.items.construction.connector.Pylon;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.Construction;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;

public class Building extends Construction implements Runnable {

    private final ConstructionType type;



    private Pylon pylonLink;
    protected volatile boolean running = true;
    private final BuildingParameter buildingParameter;

    private final Inventory inventory;
    private static final BuildingParameterMap parameterMap = new BuildingParameterMap();

    public Building(ConstructionType type, Point position, Inventory inventory) {
        super(position, type);
        this.type = type;
        this.buildingParameter = parameterMap.getParameter(type);
        this.setConstructionCost(buildingParameter.getBuildingCost());
        this.setPollution(this.buildingParameter.getPollution());
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (this.running){
            produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    protected double getAvailableElectricity(){
        double availableElectricity = 0;
        if (this.getPylonLink() != null){
            if (this.getPylonLink().getNetwork() != null){
                availableElectricity = this.getPylonLink().getNetwork().getAvailableElectricity();
            }
        }
        return availableElectricity;
    }

    protected synchronized void produce(){

        if (this.getAvailableElectricity() + this.buildingParameter.getElectricityProduction() >= 0 && this.inventory.useResource(this.buildingParameter.getInput())){
            this.pylonLink.addElectricity(this.buildingParameter.getElectricityProduction());
            this.inventory.addResource(this.buildingParameter.getOutput());
            System.out.println(this + " Consume : " + this.buildingParameter.getInput().toString() + " Produce : " + this.buildingParameter.getOutput() + " Electricity : " + this.buildingParameter.getElectricityProduction());
        }
        else {
            System.out.println(this + " unable to produce ");
        }
    }

    public ConstructionType getType() {
        return type;
    }

    public Pylon getPylonLink() {
        return pylonLink;
    }

    public BuildingParameter getBuildingParameter() {
        return buildingParameter;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void stop(){
        this.running = false;
    }

    public void setPylonLink(Pylon pylonLink) {
        this.pylonLink = pylonLink;
    }
}
