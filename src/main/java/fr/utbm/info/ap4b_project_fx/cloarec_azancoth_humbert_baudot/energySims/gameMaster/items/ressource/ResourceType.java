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
}
