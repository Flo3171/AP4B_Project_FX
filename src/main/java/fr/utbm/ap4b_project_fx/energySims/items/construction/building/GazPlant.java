package fr.utbm.ap4b_project_fx.energySims.items.construction.building;


import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;

public class GazPlant extends Building{

    public GazPlant(Point position, Inventory inventory) {
        super(ConstructionType.GAZ_PLANT, position, inventory);

    }


}
