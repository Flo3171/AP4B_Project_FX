package fr.utbm.ap4b_project_fx.energySims.items.construction;


import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;

public abstract class Construction{

    private final Point position;
    private ConstructionType constructionType;
    private Resource constructionCost;
    private double pollution;

    public Construction(Point position, ConstructionType constructionType){
        this.position = position;
        this.constructionType = constructionType;

    }

    public Construction(Point position, ConstructionType constructionType, Resource constructionCost) {
        this.position = position;
        this.constructionType = constructionType;
        this.constructionCost = constructionCost;

    }

    protected void setConstructionCost(Resource constructionCost) {
        this.constructionCost = constructionCost;
    }

    public Resource getConstructionCost() {
        return constructionCost;
    }

    public double getPollution(){
        return this.pollution;
    }

    public void setPollution(double pollution) {
        this.pollution = pollution;
    }

    public Resource getDestructionReward(){
        return new Resource(this.constructionCost.getAmount(), this.constructionCost.getType());
    }

    @Override
    public String toString() {
        return constructionType.toString();
    }

    public Point getPosition() {
        return position;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

}
