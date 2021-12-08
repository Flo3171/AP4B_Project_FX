package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector.Pylon;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;


import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

public class Building extends Construction {

    private final ConstructionType type;
    private Pylon pylonLink;

    private static final BuildingParameterMap parameterMap = new BuildingParameterMap();

    public Building(ConstructionType type, Point position) {
        super(position, type);
        this.type = type;
        BuildingParameter buildingParameter = parameterMap.getParameter(type);
    }

    @Override
    public void build(Point position) {

    }

    @Override
    public void update() {

    }

    @Override
    public Resource destroy() {
        return null;
    }

    public void setPylonLink(Pylon pylonLink) {
        this.pylonLink = pylonLink;
    }
}
