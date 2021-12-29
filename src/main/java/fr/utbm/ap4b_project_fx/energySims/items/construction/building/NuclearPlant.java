package fr.utbm.ap4b_project_fx.energySims.items.construction.building;


import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;

public class NuclearPlant extends Building{

    public NuclearPlant(Point position, Inventory inventory){
        super(ConstructionType.NUCLEAR_PLANT, position, inventory);

    }
}
