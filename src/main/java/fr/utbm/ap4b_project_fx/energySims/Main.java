package fr.utbm.ap4b_project_fx.energySims;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;


public class Main {

    public static void main(String[] args){
        Map m = new Map(new Point(5, 5), false);

        /*System.out.println(m.build(new Point(0, 0), ConstructionType.COAL_PLANT));*/

        /*m.build(new Point(0,0), ConstructionType.PYLON);
        m.build(new Point(1, 0), ConstructionType.HOUSE);
        m.build(new Point(1,1), ConstructionType.WINDMILL);
        m.build(new Point(0, 1), ConstructionType.DRILLER);

        m.getCasesTable(0, 1).setUndergroundResources(new Resource( 100, ResourceType.COAL));*/

        System.out.println(m);



        for (Resource r:
                m.getInventory().getResources()) {
            System.out.println(r.getType() + ": " + r.getAmount());
        }

        /*m.destroyConstruction(new Point(7,4));

        System.out.println(m);*/

        /*m.saveInFile("save/gameSave");

        Map p = new Map("save/gameSave");
        System.out.println(p);*/

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m);

        m.close();

    }
}

