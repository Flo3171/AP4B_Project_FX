package fr.utbm.ap4b_project_fx.energySims.items.construction.building;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

public class EntertainmentPark extends Building{
    public EntertainmentPark(Point position, Inventory inventory) {
        super(ConstructionType.ENTERTAINMENT_PARK, position, inventory);
    }
}
