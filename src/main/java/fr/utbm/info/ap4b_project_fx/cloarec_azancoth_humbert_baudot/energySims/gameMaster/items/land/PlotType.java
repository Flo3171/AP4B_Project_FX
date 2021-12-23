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
}
