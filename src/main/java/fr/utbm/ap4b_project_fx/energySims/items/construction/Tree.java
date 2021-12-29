package fr.utbm.ap4b_project_fx.energySims.items.construction;


import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;

public class Tree extends Construction{

    public Tree(Point position){
        super(position, ConstructionType.TREE, new Resource(2, ResourceType.WOOD));
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
}
