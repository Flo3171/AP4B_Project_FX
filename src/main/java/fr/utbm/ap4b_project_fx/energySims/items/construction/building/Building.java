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
    private BuildingParameter buildingParameter;

    private Inventory inventory;
    private static final BuildingParameterMap parameterMap = new BuildingParameterMap();

    public Building(ConstructionType type, Point position, Inventory inventory) {
        super(position, type);
        this.type = type;
        this.buildingParameter = parameterMap.getParameter(type);
        this.setConstructionCost(buildingParameter.getBuildingCost());
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (this.running){
            if (this.pylonLink.addElectricity(this.buildingParameter.getElectricityProduction()) && this.inventory.useResource(this.buildingParameter.getInput())){
                this.inventory.addResource(this.buildingParameter.getOutput());
                System.out.println(this.toString() + " Consume : " + this.buildingParameter.getInput().toString() + " Produce : " + this.buildingParameter.getOutput() + " Electricity : " + this.buildingParameter.getElectricityProduction());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop(){
        this.running = false;
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

    public void setPylonLink(Pylon pylonLink) {
        this.pylonLink = pylonLink;
    }
}
