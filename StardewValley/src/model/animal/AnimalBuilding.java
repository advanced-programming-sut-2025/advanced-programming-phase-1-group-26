package model.animal;

import model.GameObject;
import model.Point;
import model.enums.animal_enums.FarmBuilding;

import java.util.ArrayList;

public class AnimalBuilding {
    private Point location;
    private FarmBuilding farmBuilding;
    private int capacity;
    private int cost;
    private ArrayList<GameObject> faghatVaseShipingBin;

    public AnimalBuilding(FarmBuilding farmBuilding, int x, int y) {
        this.location = new Point(x, y);
        this.farmBuilding = farmBuilding;
        this.capacity = farmBuilding.getCapacity();
        this.cost = farmBuilding.getPrice();
    }

    public FarmBuilding getFarmBuilding() {
        return farmBuilding;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCost() {
        return cost;
    }

    public Point getLocation() {
        return location;
    }

    public ArrayList<GameObject> getFaghatVaseShipingBin() {
        return faghatVaseShipingBin;
    }

    public void addFaghatVaseShipingBin(GameObject gameObject) {
        faghatVaseShipingBin.add(gameObject);
    }
}
