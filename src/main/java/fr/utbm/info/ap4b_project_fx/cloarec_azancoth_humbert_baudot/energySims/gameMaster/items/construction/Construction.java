package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;

public abstract class Construction {

    private final Point position;
    private ConstructionType constructionType;

    public Construction(Point position, ConstructionType constructionType){
        this.position = position;
        this.constructionType = constructionType;
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

    public abstract void build(Point position);

    public abstract void update();

    public abstract Resource destroy();

}
