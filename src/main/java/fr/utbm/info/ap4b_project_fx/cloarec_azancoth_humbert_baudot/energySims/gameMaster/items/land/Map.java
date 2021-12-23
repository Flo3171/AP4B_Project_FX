package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.land;


import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.ResourceType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.Point;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.ConstructionType;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.construction.connector.ElectricalNetwork;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.items.ressource.Inventory;
import fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils.FileUsage;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represent the map of the game
 *
 * @author Florian CLOAREC
 */
public class Map {
    private final Point mapSize;
    private final Inventory inventory;

    private Plot[][] casesTable;
    private List<ElectricalNetwork> electricalNetworks;


    public Map(Point mapSize, boolean debug){
        this.inventory = new Inventory();
        this.electricalNetworks = new ArrayList<>();
        this.mapSize = mapSize;
        this.casesTable = new Plot[this.mapSize.getX()][this.mapSize.getY()];
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i] = new Plot(new Point(j, i), debug);
            }
        }
    }

    public Map(String fileName){
        List<String> fileContent = FileUsage.readFile(fileName);
        this.mapSize = new Point(fileContent.get(0));
        this.inventory = new Inventory(fileContent.get(1));
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("Map :" +
                "\nmapWidth=" + this.mapSize.getX() +
                ", \nmapHeight=" + this.mapSize.getY() +
                ", \ncasesTable=");
        for (int i = 0; i < this.mapSize.getY(); i++) {
            value.append("\n ");
            for (int j = 0; j < this.mapSize.getX(); j++) {
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


    public void saveInFile(String filePath){
        List<String> mapStringList = new ArrayList<>();
        mapStringList.add(this.mapSize.toString());
        mapStringList.add(this.inventory.toString());
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                mapStringList.add(this.casesTable[j][i].toString());
            }
        }
        FileUsage.writeFile(filePath, mapStringList);
    }

    public Point getMapSize() {
        return mapSize;
    }

    public Plot getCasesTable(int i, int j) {
        return this.casesTable[i][j];
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean build(Point position, ConstructionType constructionType) {
        boolean result = this.casesTable[position.getX()][position.getY()].build(constructionType, this.inventory);
        this.updateMap();
        this.update();
        return result;

    }

    public void update(){
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i].update();
            }
        }

    }

    public void updateMap(){
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i].updateNeighbour(this);
            }
        }
        this.electricalNetworks = ElectricalNetwork.updateNetwork(this);
    }



    public boolean destroyConstruction(Point position){
        boolean result = this.casesTable[position.getX()][position.getY()].destroy(this.inventory);
        this.updateMap();
        this.update();
        return  result;
    }


}
