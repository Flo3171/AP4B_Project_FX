package fr.utbm.ap4b_project_fx.energySims.items.construction;


public enum ConstructionType {
    TREE,
    PYLON,
    ROAD,
    HOUSE,
    FARM,
    ENTERTAINMENT_PARK,
    NUCLEAR_PLANT,
    COAL_PLANT,
    WINDMILL,
    SOLAR_PANEL,
    DRILLER;

    @Override
    public String toString() {
        switch (this){
            case TREE -> {return "Tr";}
            case PYLON -> {return "Py";}
            case ROAD -> {return "Ro";}
            case HOUSE -> {return "Ho";}
            case FARM -> {return "Fa";}
            case ENTERTAINMENT_PARK -> {return "EP";}
            case NUCLEAR_PLANT -> {return "NP";}
            case COAL_PLANT -> {return "CP";}
            case WINDMILL -> {return "Wi";}
            case SOLAR_PANEL -> {return "SP";}
            case DRILLER -> {return "Dr";}

        }
        return "";

    }

    public static ConstructionType getConstructionType(String name){
        if (name.equals(TREE.toString())){
            return TREE;
        } else if (name.equals(PYLON.toString())) {
            return PYLON;
        } else if (name.equals(ROAD.toString())) {
            return ROAD;
        } else if (name.equals(HOUSE.toString())) {
            return HOUSE;
        } else if (name.equals(FARM.toString())) {
            return FARM;
        } else if (name.equals(ENTERTAINMENT_PARK.toString())){
            return ENTERTAINMENT_PARK;
        } else if (name.equals(NUCLEAR_PLANT.toString())) {
            return NUCLEAR_PLANT;
        } else if (name.equals(COAL_PLANT.toString())) {
            return COAL_PLANT;
        } else if (name.equals(WINDMILL.toString())) {
            return WINDMILL;
        } else if (name.equals(SOLAR_PANEL.toString())) {
            return SOLAR_PANEL;
        } else if (name.equals(DRILLER.toString())){
            return DRILLER;
        } else {
            return null;
        }
    }
}


