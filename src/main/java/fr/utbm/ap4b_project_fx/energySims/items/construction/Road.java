package fr.utbm.ap4b_project_fx.energySims.items.construction;

import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

public class Road extends Construction {

    public Road(Point position) {
        super(position, ConstructionType.ROAD, new Resource(2, ResourceType.COAL));
    }
}
