package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource;

public enum ResourceType {
    COAL,
    IRON,
    COPPER,
    URANIUM,
    OIL,
    WATER,
    GAS,
    WOOD,
    EUROS,
    SATISFACTION;

    @Override
    public String toString() {
        switch (this){
            case COAL ->{return "Coal";}
            case IRON -> {return "Iron";}
            case COPPER -> {return "Copper";}
            case URANIUM -> {return "Uranium";}
            case OIL -> {return "Oil";}
            case WATER -> {return "Water";}
            case GAS -> {return "Gas";}
            case WOOD -> {return "Wood";}
            case EUROS -> {return "Euros";}
            case SATISFACTION -> {return "Satisfaction";}

        }
        return "";
    }

    public static ResourceType getResourceType(String name){
        if (name.equals(COAL.toString())){
            return COAL;
        } else if (name.equals(IRON.toString())){
            return IRON;
        } else if (name.equals(COPPER.toString())){
            return COPPER;
        } else if (name.equals(URANIUM.toString())){
            return URANIUM;
        } else if (name.equals(OIL.toString())){
            return OIL;
        } else if (name.equals(WATER.toString())){
            return WATER;
        } else if (name.equals(GAS.toString())){
            return GAS;
        } else if (name.equals(WOOD.toString())){
            return WOOD;
        } else if (name.equals(EUROS.toString())){
            return EUROS;
        } else if (name.equals(SATISFACTION.toString())){
            return SATISFACTION;
        }
        else {
            return null;
        }
    }
}
