package fr.utbm.ap4b_project_fx.energySims.items.construction.building;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

public class Farm extends Building{
    public Farm( Point position, Inventory inventory) {
        super(ConstructionType.FARM, position, inventory);
    }
}
