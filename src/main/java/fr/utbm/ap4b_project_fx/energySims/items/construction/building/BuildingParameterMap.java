package fr.utbm.ap4b_project_fx.energySims.items.construction.building;

import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class BuildingParameterMap {

    private final Map<ConstructionType, BuildingParameter> parameter;

    public BuildingParameterMap() {
        this.parameter = new HashMap<>();



        this.parameter.put(ConstructionType.HOUSE, new BuildingParameter(
                new Resource(10, ResourceType.WOOD),
                2,
                -10,
                new Resource(2, ResourceType.WATER),
                new Resource(5, ResourceType.SATISFACTION),
                4,
                0
        ));

        this.parameter.put(ConstructionType.NUCLEAR_PLANT, new BuildingParameter(
                new Resource(200, ResourceType.COPPER),
                2,
                100,
                new Resource(5, ResourceType.URANIUM),
                new Resource(0, ResourceType.NONE),
                0,
                25
        ));

        this.parameter.put(ConstructionType.COAL_PLANT, new BuildingParameter(
                new Resource(20, ResourceType.IRON),
                10,
                20,
                new Resource(10, ResourceType.COAL),
                new Resource(0, ResourceType.NONE),
                0,
                5
        ));

        this.parameter.put(ConstructionType.GAZ_PLANT, new BuildingParameter(
                new Resource(40, ResourceType.IRON),
                8,
                30,
                new Resource(10, ResourceType.GAS),
                new Resource(0, ResourceType.NONE),
                0,
                6
        ));

        this.parameter.put(ConstructionType.OIL_PLANT, new BuildingParameter(
                new Resource(100, ResourceType.COPPER),
                20,
                60,
                new Resource(10, ResourceType.OIL),
                new Resource(0, ResourceType.NONE),
                0,
                10
        ));

        this.parameter.put(ConstructionType.WINDMILL, new BuildingParameter(
                new Resource(20, ResourceType.COPPER),
                0,
                20,
                new Resource(0, ResourceType.NONE),
                new Resource(0, ResourceType.NONE),
                0,
                2
        ));

        this.parameter.put(ConstructionType.SOLAR_PANEL, new BuildingParameter(
                new Resource(10, ResourceType.COPPER),
                0,
                10,
                new Resource(0, ResourceType.NONE),
                new Resource(0, ResourceType.NONE),
                0,
                2
        ));

    }


    public BuildingParameter getParameter(ConstructionType type){
        return this.parameter.get(type);
    }
}
