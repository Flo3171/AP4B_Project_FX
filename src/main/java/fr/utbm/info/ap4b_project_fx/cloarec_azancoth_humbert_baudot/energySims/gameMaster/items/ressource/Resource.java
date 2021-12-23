package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource;

public class Resource {
    private double amount;
    private final ResourceType type;

    public Resource(double amount, ResourceType type){
        this.amount = amount;
        this.type = type;
    }

    public double getAmount(){
        return this.amount;
    }

    @Override
    public String toString() {
        return this.type.toString() + this.amount;
    }

    public ResourceType getType(){
        return this.type;
    }

    public boolean addResource(double diff){
        if (this.amount + diff >= 0){
            this.amount = this.amount + diff;
            return true;
        }
        else {
            return false;
        }

    }
}
