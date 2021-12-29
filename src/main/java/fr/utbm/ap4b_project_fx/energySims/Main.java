package fr.utbm.ap4b_project_fx.energySims;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;


public class Main {

    public static void main(String[] args) {
        Map m = new Map(new Point(26, 14), false);

        m.getInventory().addResource(new Resource(100, ResourceType.WOOD));
        m.getInventory().addResource(new Resource(100, ResourceType.COPPER));
        m.getInventory().addResource(new Resource(100, ResourceType.COAL));


        m.build(new Point(0,0), ConstructionType.PYLON);
        m.build(new Point(1, 0), ConstructionType.HOUSE);
        m.build(new Point(4,0), ConstructionType.PYLON);
        m.build(new Point(0,4), ConstructionType.PYLON);
        m.build(new Point(4,4), ConstructionType.PYLON);
        m.build(new Point(2,2), ConstructionType.PYLON);
        m.build(new Point(1,2), ConstructionType.WINDMILL);

        m.build(new Point(7, 4), ConstructionType.ROAD);
        /*System.out.println(m);

        m.destroyConstruction(new Point(7,4));

        System.out.println(m);*/

        m.saveInFile("save/gameSave");

        Map p = new Map("save/gameSave");
        System.out.println(p);



    }
}

