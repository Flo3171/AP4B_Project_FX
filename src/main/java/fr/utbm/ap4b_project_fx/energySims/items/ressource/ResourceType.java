package fr.utbm.ap4b_project_fx.energySims.items.ressource;

public enum ResourceType {
    NONE,
    WOOD,
    COAL,
    IRON,
    COPPER,
    URANIUM,
    WATER,
    SATISFACTION;

    @Override
    public String toString() {
        switch (this){
            case COAL ->{return "Coal";}
            case IRON -> {return "Iron";}
            case COPPER -> {return "Copper";}
            case URANIUM -> {return "Uranium";}
            case WATER -> {return "Water";}
            case WOOD -> {return "WOOD";}
            case SATISFACTION -> {return "Satisfaction";}

        }
        return "NONE";
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
        } else if (name.equals(WATER.toString())){
            return WATER;
        } else if (name.equals(WOOD.toString())){
            return WOOD;
        } else if (name.equals(SATISFACTION.toString())){
            return SATISFACTION;
        }
        else {
            return NONE;
        }
    }
}
