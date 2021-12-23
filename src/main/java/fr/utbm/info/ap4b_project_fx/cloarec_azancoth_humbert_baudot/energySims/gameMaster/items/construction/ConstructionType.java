package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction;


public enum ConstructionType {
    TREE,
    PYLON,
    PIPE,
    ROAD,
    HOUSE,
    NUCLEAR_PLANT,
    COAL_PLANT,
    GAZ_PLANT,
    OIL_PLANT,
    WINDMILL,
    SOLAR_PANEL;

    @Override
    public String toString() {
        switch (this){
            case TREE -> {return "Tr";}
            case PYLON -> {return "Py";}
            case PIPE -> {return  "Pi";}
            case ROAD -> {return "Ro";}
            case HOUSE -> {return "Ho";}
            case NUCLEAR_PLANT -> {return "NP";}
            case COAL_PLANT -> {return "CP";}
            case GAZ_PLANT -> {return "GP";}
            case OIL_PLANT -> {return "OP";}
            case WINDMILL -> {return "Wi";}
            case SOLAR_PANEL -> {return "SP";}

        }
        return "";

    }

    public static ConstructionType getConstructionType(String name){
        if (name.equals(TREE.toString())){
            return TREE;
        } else if (name.equals(PYLON.toString())) {
            return PYLON;
        } else if (name.equals(PIPE.toString())) {
            return PIPE;
        } else if (name.equals(ROAD.toString())) {
            return ROAD;
        } else if (name.equals(HOUSE.toString())) {
            return HOUSE;
        } else if (name.equals(NUCLEAR_PLANT.toString())) {
            return NUCLEAR_PLANT;
        } else if (name.equals(COAL_PLANT.toString())) {
            return COAL_PLANT;
        } else if (name.equals(GAZ_PLANT.toString())) {
            return GAZ_PLANT;
        } else if (name.equals(OIL_PLANT.toString())) {
            return OIL_PLANT;
        } else if (name.equals(WINDMILL.toString())) {
            return WINDMILL;
        } else if (name.equals(SOLAR_PANEL.toString())) {
            return SOLAR_PANEL;
        } else {
            return null;
        }
    }
}


