package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction;

import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;

public class Road extends Construction {

    public Road(Point position) {
        super(position, ConstructionType.ROAD, new Resource(2, ResourceType.COAL));
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
