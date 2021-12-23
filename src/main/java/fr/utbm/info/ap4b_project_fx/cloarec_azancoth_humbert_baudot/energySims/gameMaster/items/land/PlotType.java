package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land;

public enum PlotType {
    GRASS,
    SAND,
    DIRT,
    STONE,
    CLAY,
    WATER,
    DRY_PLOT;

    @Override
    public String toString() {
        switch (this){
            case GRASS -> {return "GRASS";}
            case SAND -> {return "SAND";}
            case DIRT -> {return "DIRT";}
            case STONE -> {return "STONE";}
            case CLAY -> {return "CLAY";}
            case WATER -> {return "WATER";}
            case DRY_PLOT -> {return "DRY_PLOT";}
        }

        return "UNKNOWN";
    }

    public static PlotType getPlotType(String string){
        if(string.equals(GRASS.toString())){
            return GRASS;
        } else if (string.equals(SAND.toString())){
            return SAND;
        } else if (string.equals(DIRT.toString())){
            return DIRT;
        } else if (string.equals(STONE.toString())){
            return STONE;
        } else if (string.equals(CLAY.toString())){
            return CLAY;
        }else if (string.equals(WATER.toString())){
            return WATER;
        } else if (string.equals(DRY_PLOT.toString())){
            return DRY_PLOT;
        } else {
            return null;
        }
    }
}
