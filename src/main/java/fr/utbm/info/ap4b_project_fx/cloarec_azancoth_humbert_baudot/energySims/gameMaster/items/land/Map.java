package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.Construction;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.building.Building;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector.ElectricalNetwork;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector.Pylon;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Inventory;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represent the map of the game
 *
 * @author Florian CLOAREC
 */
public class Map {
    private final int mapWidth;
    private final int mapHeight;

    private Plot[][] casesTable;
    private List<ElectricalNetwork> electricalNetworks;

    private Inventory inventory;

    public Map(int mapWidth, int mapHeight, boolean debug){
        this.inventory = new Inventory();
        this.electricalNetworks = new ArrayList<>();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.casesTable = new Plot[this.mapWidth][this.mapHeight];
        for (int i = 0; i < this.mapHeight; i++) {
            for (int j = 0; j < this.mapWidth; j++) {
                this.casesTable[j][i] = new Plot(new Point(j, i), debug);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("Map :" +
                "\nmapWidth=" + mapWidth +
                ", \nmapHeight=" + mapHeight +
                ", \ncasesTable=");
        for (int i = 0; i < this.mapHeight; i++) {
            value.append("\n ");
            for (int j = 0; j < this.mapWidth; j++) {
                value.append(this.casesTable[j][i].toString()).append(" ");
            }

        }

        value.append("\nNetwork :" );
        for (ElectricalNetwork network :
                this.electricalNetworks) {
            value.append("\n\t").append(network.toString());
        }
        value.append("\nInventory :").append(this.inventory.toString());


        return value.toString();
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public Plot getCasesTable(int i, int j) {
        return this.casesTable[i][j];
    }

    public void build(Point position, ConstructionType constructionType) {
        this.casesTable[position.getX()][position.getY()].build(constructionType);
        this.updateMap();
        this.update();
    }

    public void update(){
        for (int i = 0; i < this.mapHeight; i++) {
            for (int j = 0; j < this.mapWidth; j++) {
                this.casesTable[j][i].update();
            }
        }

    }

    public void updateMap(){
        for (int i = 0; i < this.mapHeight; i++) {
            for (int j = 0; j < this.mapWidth; j++) {
                this.casesTable[j][i].updateNeighbour(this);
            }
        }
        this.electricalNetworks = ElectricalNetwork.updateNetwork(this);
    }



    public Resource destroyConstruction(Point position){
        Resource resource = this.casesTable[position.getX()][position.getY()].destroy();
        this.updateMap();
        this.update();
        return  resource;
    }


}
