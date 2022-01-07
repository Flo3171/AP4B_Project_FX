package fr.utbm.ap4b_project_fx.energySims.items.land;

public enum PlotType {
    GRASS,
    IRON_DEPOSIT,
    COAL_DEPOSIT,
    COPPER_DEPOSIT,
    URANIUM_DEPOSIT,
    WATER,
    DRY_PLOT;

    @Override
    public String toString() {
        switch (this){
            case GRASS -> {return "GRASS";}
            case IRON_DEPOSIT -> {return "IRON_DEPOSIT";}
            case COAL_DEPOSIT -> {return "COAL_DEPOSIT";}
            case COPPER_DEPOSIT -> {return "COPPER_DEPOSIT";}
            case URANIUM_DEPOSIT -> {return "URANIUM_DEPOSIT";}
            case WATER -> {return "WATER";}
            case DRY_PLOT -> {return "DRY_PLOT";}
        }

        return "UNKNOWN";
    }

    public static PlotType getPlotType(String string){
        if(string.equals(GRASS.toString())){
            return GRASS;
        } else if (string.equals(IRON_DEPOSIT.toString())){
            return IRON_DEPOSIT;
        } else if (string.equals(COAL_DEPOSIT.toString())){
            return COAL_DEPOSIT;
        } else if (string.equals(COPPER_DEPOSIT.toString())){
            return COPPER_DEPOSIT;
        } else if (string.equals(URANIUM_DEPOSIT.toString())){
            return URANIUM_DEPOSIT;
        }else if (string.equals(WATER.toString())){
            return WATER;
        } else if (string.equals(DRY_PLOT.toString())){
            return DRY_PLOT;
        } else {
            return null;
        }
    }
}
