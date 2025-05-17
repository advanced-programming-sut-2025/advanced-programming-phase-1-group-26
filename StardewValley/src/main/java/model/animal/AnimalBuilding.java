package model.animal;

import model.App;
import model.GameObject;
import model.Point;
import model.Tile;
import model.enums.GameObjectType;
import model.enums.animal_enums.FarmBuildingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AnimalBuilding extends GameObject
{
    private final Tile startTile;
    private final FarmBuildingType farmBuildingType;
    private final int cost;
    private final int capacity;
    private ArrayList<GameObject> faghatVaseShipingBin = new ArrayList<>(); // TODO: ???
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final int height;
    private final int width;

    public AnimalBuilding(Tile startTile, FarmBuildingType farmBuilding)
    {
        this.startTile = startTile;
        this.farmBuildingType = farmBuilding;
        this.capacity = farmBuilding.getCapacity();
        this.cost = farmBuilding.getPrice();
        super.ObjectType = GameObjectType.ANIMAL_BUILDING;
        this.height = farmBuilding.getHeight();
        this.width = farmBuilding.getWidth();
    }

    public FarmBuildingType getFarmBuildingType() {
        return farmBuildingType;
    }

    public Point getLocation() {
        return startTile.getPoint();
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

    public ArrayList<Tile> getTiles()
    {
        int baseX = startTile.getPoint().getX();
        int baseY = startTile.getPoint().getY();

        ArrayList<Tile> temp = new ArrayList<>();
        for (int y = 0; y <= height; y++)
        {
            for (int x = 0; x <= width; x++)
            {
                Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().getTile(baseX + x, baseY + y);
                temp.add(tile);
            }
        }

        return temp;
    }

    public boolean hasCapacity()
    {
        if (capacity == -1)
        {
            return true;
        }

        return (capacity - faghatVaseShipingBin.size()) > 0;
    }

    public void putAnimalInBuilding(Animal animal)
    {
        animals.add(animal);

        ArrayList<Tile> tiles = getTiles();
        ArrayList<Tile> copy = new ArrayList<>(tiles);

        Iterator<Tile> iterator = copy.iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            if (tile.getObject() != null) {
                iterator.remove();
            }
        }

        // TODO
//        if (copy.size() > 0)
//       {
           Collections.shuffle(copy);

           Tile tile = copy.get(0);
           animal.setTile(tile);
           tile.setObject(animal);
//       }
    }

    public void sellAnimal(Animal animal)
    {
        animals.remove(animal);
    }
}
