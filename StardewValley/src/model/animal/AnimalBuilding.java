package model.animal;

import model.GameObject;
import model.Point;
import model.enums.GameObjectType;
import model.enums.animal_enums.FarmBuilding;

import java.util.ArrayList;

public class AnimalBuilding extends GameObject {
    private GameObjectType type;
    private Point location;
    private FarmBuilding farmBuilding;
    private int capacity;
    private int cost;
    private ArrayList<GameObject> faghatVaseShipingBin;
    private ArrayList<Animal> animals = new ArrayList<>();

    public AnimalBuilding(FarmBuilding farmBuilding, int x, int y) {
        this.location = new Point(x, y);
        this.farmBuilding = farmBuilding;
        this.capacity = farmBuilding.getCapacity();
        this.cost = farmBuilding.getPrice();
        this.type = GameObjectType.ANIMAL_BUILDING;
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

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public GameObjectType getType() {
        return type;
    }
}
