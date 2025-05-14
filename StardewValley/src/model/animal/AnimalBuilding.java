package model.animal;

import model.Point;
import model.enums.animal_enums.FarmBuilding;

public class AnimalBuilding {
    private Point location;
    private FarmBuilding farmBuilding;
    private int capacity;
    private int cost;

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
}
