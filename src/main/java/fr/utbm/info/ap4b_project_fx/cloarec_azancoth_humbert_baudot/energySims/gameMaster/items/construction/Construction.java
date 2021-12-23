package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;

public abstract class Construction {

    private final Point position;
    private ConstructionType constructionType;
    private Resource constructionCost;

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

    public Resource getDestructionReward(){
        return new Resource(this.constructionCost.getAmount()/2, this.constructionCost.getType());
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



    public abstract boolean build(Point position);

    public abstract boolean update();

    public abstract boolean destroy();

}
