package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;


import java.util.ArrayList;
import java.util.List;

public abstract class Building extends Construction {
    private float pollution;
    private float electricityProduction;
    private List<Resource> input;
    private List<Resource> output;

    public Building(float pollution, float electricityProduction, Point position){
        super(position);
        this.pollution = pollution;
        this.electricityProduction = electricityProduction;
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
    }

    protected float getPollution() {
        return pollution;
    }

    protected void setPollution(float pollution) {
        this.pollution = pollution;
    }

    protected float getElectricityProduction() {
        return electricityProduction;
    }

    protected void setElectricityProduction(float electricityProduction) {
        this.electricityProduction = electricityProduction;
    }

    protected List<Resource> getInput() {
        return input;
    }

    protected void setInput(List<Resource> input) {
        this.input = input;
    }

    protected List<Resource> getOutput() {
        return output;
    }

    protected void setOutput(List<Resource> output) {
        this.output = output;
    }
}
