package model.animal;

import model.enums.animal_enums.FarmBuilding;

public class AnimalBuilding {
    private FarmBuilding farmBuilding;
    private int capacity;
    private int cost;

    public AnimalBuilding(FarmBuilding farmBuilding) {
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
}
