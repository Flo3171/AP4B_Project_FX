package fr.utbm.ap4b_project_fx.energySims.items.construction.building;


import fr.utbm.ap4b_project_fx.energySims.items.construction.connector.Pylon;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;
import fr.utbm.ap4b_project_fx.energySims.items.construction.Construction;
import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;

public class Building extends Construction {

    private final ConstructionType type;
    private Pylon pylonLink;


    private static final BuildingParameterMap parameterMap = new BuildingParameterMap();

    public Building(ConstructionType type, Point position) {
        super(position, type);
        this.type = type;
        BuildingParameter buildingParameter = parameterMap.getParameter(type);
        this.setConstructionCost(buildingParameter.getBuildingCost());
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

    public void setPylonLink(Pylon pylonLink) {
        this.pylonLink = pylonLink;
    }
}
