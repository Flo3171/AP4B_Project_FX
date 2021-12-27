package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;


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

