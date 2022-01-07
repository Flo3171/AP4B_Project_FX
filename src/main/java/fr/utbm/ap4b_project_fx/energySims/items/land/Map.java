package fr.utbm.ap4b_project_fx.energySims.items.land;


import fr.utbm.ap4b_project_fx.energySims.items.construction.ConstructionType;
import fr.utbm.ap4b_project_fx.energySims.items.construction.connector.ElectricalNetwork;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Inventory;
import fr.utbm.ap4b_project_fx.energySims.items.ressource.Resource;
import fr.utbm.ap4b_project_fx.energySims.utils.FileUsage;
import fr.utbm.ap4b_project_fx.energySims.utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class Map represents the map of the game, it contents all the Plot of the map, the inventory and the list of the electrical Networks
 * We can build and destroy construction on a Map and update this
 * We can generate a random map or get one from a file
 * We can save a map in a file
 * @see Plot
 * @see Point
 * @see Inventory
 *
 * @author Florian CLOAREC
 * @version 1.0
 */
public class Map {

    /**
     * The dimension in width and height of the casesTable
     */
    private final Point mapSize;

    /**
     * The inventory of the player, so the list of all the Resource he had
     * @see Resource
     */
    private final Inventory inventory;

    /**
     * Double array where each element correspond to a Plot on the grid of the board
     */
    private final Plot[][] casesTable;

    /**
     * List of all the ElectricalNetwork found on the map
     */
    private List<ElectricalNetwork> electricalNetworks;


    /**
     * Constructor use to generate a new Map with random generation
     * @param mapSize
     *                  the width and the height of the Plot grind on the map
     * @param debug
     *                  set to True for a full grass and empty map
     */
    public Map(Point mapSize, boolean debug){
        Random r = new Random();
        this.inventory = new Inventory();
        this.mapSize = mapSize;
        this.casesTable = new Plot[this.mapSize.getX()][this.mapSize.getY()];
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i] = new Plot(new Point(j, i), debug);
            }
        }
        this.electricalNetworks = new ArrayList<>();

        boolean result;
        do {
            result = this.casesTable[r.nextInt(this.mapSize.getX())][r.nextInt(this.mapSize.getY())].build(ConstructionType.ROAD, null);
        }while (!result);
    }


    /**
     * Constructor use to build a map form a file
     * the file must exist and have been created by the saveInFile method
     * @param fileName
     *                  the absolute or relative path to the file
     *
     * @see Map#saveInFile(String)
     */
    public Map(String fileName){
        List<String> fileContent = FileUsage.readFile(fileName);
        this.mapSize = new Point(fileContent.get(0));
        this.inventory = new Inventory(fileContent.get(1));
        this.electricalNetworks = new ArrayList<>();
        this.casesTable = new Plot[this.mapSize.getX()][this.mapSize.getY()];
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i] = new Plot(new Point(j, i), fileContent.get(i*this.mapSize.getX() + j + 2 ));
            }
        }
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
                value.append("\n ");
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

    /**
     * Write the object Map in a file to persistent save
     * @param filePath
     *                  the absolute or relative path to the file where we want to save
     * @see Map#Map(String)  Map
     */
    public synchronized void saveInFile(String filePath){
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

    /**
     * Build a new construction of the specified type in the specified position on the plot grid
     * @param position
     *                  the coordinate of the plot in the grid where we want to build the construction
     * @param constructionType
     *                  the type of the construction that we want to build
     * @return
     *                  True if all gone well
     *                  False else
     */
    public synchronized boolean build(Point position, ConstructionType constructionType) {
        if (!((constructionType != ConstructionType.TREE && constructionType  != ConstructionType.PYLON) && !this.isRoadAround(position))){
            boolean result = this.casesTable[position.getX()][position.getY()].build(constructionType, this.inventory);
            this.updateNetwork();
            return result;
        }
        else{
            return false;
        }


    }


    /**
     * update the network on the map by updating the neighbour of all the construction on the map and then update really the network
     */
    public synchronized void updateNetwork(){
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i].updateNeighbour(this);
            }
        }
        this.electricalNetworks = ElectricalNetwork.updateNetwork(this);
    }


    /**
     * Destroy a construction on the map
     * @param position
     *                  the coordinate of the plot in the grid where we want to destroy the construction
     * @return
     *          True if all gone well
     *          False else
     *
     */
    public synchronized boolean destroyConstruction(Point position){
        boolean result = this.casesTable[position.getX()][position.getY()].destroy(this.inventory);
        this.updateNetwork();
        return  result;
    }

    public synchronized void close(){
        for (int i = 0; i < this.mapSize.getY(); i++) {
            for (int j = 0; j < this.mapSize.getX(); j++) {
                this.casesTable[j][i].close();
            }
        }
    }

    public synchronized boolean isPointInMap(Point position){
        return position.getX() >= 0 && position.getX() < this.getMapSize().getX() && position.getY() >= 0 && position.getY() < this.getMapSize().getY();
    }

    public synchronized boolean isRoadAround(Point position){
        boolean roadFound = false;
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                Point adjacentPoint = new Point(position.getX() + i, position.getY() + j);
                if (this.isPointInMap(adjacentPoint) && (i != 0 || j != 0) && this.getCasesTable(adjacentPoint.getX(), adjacentPoint.getY()).getConstruction() != null && this.getCasesTable(adjacentPoint.getX(), adjacentPoint.getY()).getConstruction().getConstructionType() == ConstructionType.ROAD){
                    roadFound = true;
                }
            }
        }
        return roadFound;
    }

    public synchronized double getPollution(){
        double pollution = 0;
        for (int j = 0; j < this.mapSize.getY(); j++) {
            for (int i = 0; i < this.mapSize.getX(); i++) {
                pollution += this.getCasesTable(i, j).getPollution();
            }

        }
        return pollution;
    }


}
