package fr.utbm.ap4b_project_fx.energySims.items.construction;

import fr.utbm.ap4b_project_fx.energySims.items.construction.building.Building;
import fr.utbm.ap4b_project_fx.energySims.items.construction.connector.ElectricalNetwork;
import fr.utbm.ap4b_project_fx.energySims.items.land.Map;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Road extends Construction {

    public Road(Point position) {
        super(position, ConstructionType.ROAD, new Resource(2, ResourceType.COAL));
        this.setPollution(0);
    }
}
