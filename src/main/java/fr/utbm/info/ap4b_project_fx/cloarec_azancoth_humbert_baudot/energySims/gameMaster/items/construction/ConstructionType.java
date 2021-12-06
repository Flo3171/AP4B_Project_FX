package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction;

public enum ConstructionType {
    TREE,
    PYLON,
    PIPE,
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
}


