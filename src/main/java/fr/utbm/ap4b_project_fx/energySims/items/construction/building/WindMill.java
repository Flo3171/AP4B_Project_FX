package fr.utbm.ap4b_project_fx.energySims.items.construction.building;


import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;

public class WindMill extends Building{
    public WindMill(Point position) {
        super(ConstructionType.WINDMILL, position);
    }


}
