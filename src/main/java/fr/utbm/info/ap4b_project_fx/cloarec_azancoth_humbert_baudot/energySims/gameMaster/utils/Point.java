package fr.utbm.info.ap4b_project_fx.cloarec_azancoth_humbert_baudot.energySims.gameMaster.utils;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(String string){
        String[] split = string.split(",");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public static double dist(int xA, int yA, int xB, int yB){
        Point a = new Point(xA, yA);
        Point b = new Point(xB, yB);
        return a.dist(b);
    }

    public double dist(Point b){
        return Math.sqrt(Math.pow(b.x - this.x, 2) + Math.pow(b.y - this.y, 2));
    }

    public double dist(int x, int y){
        Point a = new Point(x,y);
        return this.dist(a);
    }


}
