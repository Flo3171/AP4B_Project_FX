package fr.utbm.ap4b_project_fx.energySims.items.construction.building;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.land.Plot;
import fr.utbm.ap4b_project_fx.energySims.items.land.PlotType;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

public class Driller extends Building{
    Plot plot;

    public Driller(Point position, Inventory inventory, Plot plot) {
        super(ConstructionType.DRILLER, position, inventory);
        this.plot = plot;
    }

    @Override
    protected synchronized void produce() {
        double availableElectricity = 0;
        if (this.getPylonLink().getNetwork() != null){
            availableElectricity = this.getPylonLink().getNetwork().getAvailableElectricity();
        }
        if (availableElectricity + this.getBuildingParameter().getElectricityProduction() >= 0 && this.plot.getUndergroundResources() != null && this.plot.getUndergroundResources().getAmount() >= 1){
            this.getPylonLink().addElectricity(this.getBuildingParameter().getElectricityProduction());
            this.getInventory().addResource(new Resource(1, this.plot.getUndergroundResources().getType()));
            this.plot.getUndergroundResources().addResource(-1);
            if (this.plot.getType() == PlotType.WATER && this.plot.getUndergroundResources().getAmount() <= 0){
                this.plot.dry();
            }
            System.out.println(this + " Consume : " + this.getBuildingParameter().getInput().toString() + " Produce : " + this.getBuildingParameter().getOutput() + " Electricity : " + this.getBuildingParameter().getElectricityProduction());
        }
        else {
            System.out.println(this + " unable to produce ");
        }
    }
}