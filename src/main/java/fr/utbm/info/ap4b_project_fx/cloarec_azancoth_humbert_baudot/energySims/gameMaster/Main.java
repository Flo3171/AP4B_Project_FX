package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land.Map;




public class Main {

    public static void main(String[] args) {
        Map m = new Map(8, 5, true);

        m.build(new Point(0,0), ConstructionType.PYLON);
        m.build(new Point(1, 0), ConstructionType.HOUSE);
        m.build(new Point(4,0), ConstructionType.PYLON);
        m.build(new Point(0,4), ConstructionType.PYLON);
        m.build(new Point(4,4), ConstructionType.PYLON);
        m.build(new Point(2,2), ConstructionType.PYLON);
        m.build(new Point(1,2), ConstructionType.WINDMILL);


        System.out.println(m);


    }
}
