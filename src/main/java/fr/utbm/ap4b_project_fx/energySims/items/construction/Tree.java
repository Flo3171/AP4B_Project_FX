package fr.utbm.ap4b_project_fx.energySims.items.construction;


import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;

public class Tree extends Construction{

    public Tree(Point position){
        super(position, ConstructionType.TREE, new Resource(1, ResourceType.WOOD));
        this.setPollution(-2);
    }

    @Override
    public Resource getDestructionReward() {
        return new Resource(11, ResourceType.WOOD);
    }
}
