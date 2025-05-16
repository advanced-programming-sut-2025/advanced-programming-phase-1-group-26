package model.enums.animal_enums;

import model.GameObject;
import model.enums.GameObjectType;

import java.util.List;

public enum FarmBuildingType
{
    BARN("Barn", "Houses 4 barn-dwelling animals.", 6000,
            List.of(new GameObject(GameObjectType.WOOD, 350),
                    new GameObject(GameObjectType.STONE, 150)), List.of(7, 4), 4, 1),

    BIG_BARN("Big Barn", "Houses 8 barn-dwelling animals. Unlocks goats.", 12000,
            List.of(new GameObject(GameObjectType.WOOD, 450),
                    new GameObject(GameObjectType.STONE, 200)), List.of(7, 4), 8, 1),

    DELUXE_BARN("Deluxe Barn", "Houses 12 barn-dwelling animals. Unlocks sheep and pigs.", 25000,
            List.of(new GameObject(GameObjectType.WOOD, 550),
                    new GameObject(GameObjectType.STONE, 300)), List.of(7, 4), 12, 1),

    COOP("Coop", "Houses 4 coop-dwelling animals.", 4000,
            List.of(new GameObject(GameObjectType.WOOD, 300),
                    new GameObject(GameObjectType.STONE, 100)), List.of(6, 3), 4, 1),

    BIG_COOP("Big Coop", "Houses 8 coop-dwelling animals. Unlocks ducks.", 10000,
            List.of(new GameObject(GameObjectType.WOOD, 400),
                    new GameObject(GameObjectType.STONE, 150)), List.of(6, 3), 8, 1),

    DELUXE_COOP("Deluxe Coop", "Houses 12 coop-dwelling animals. Unlocks rabbits.", 20000,
            List.of(new GameObject(GameObjectType.WOOD, 500),
                    new GameObject(GameObjectType.STONE, 200)), List.of(6, 3), 12, 1),

    WELL("Well", "Provides a place for you to refill your watering can.", 1000,
            List.of(new GameObject(GameObjectType.STONE, 75)), List.of(3, 3), -1, 1),

    SHIPPING_BIN("Shipping Bin", "Items placed in it will be included in the nightly shipment.", 250,
            List.of(new GameObject(GameObjectType.WOOD, 150)), List.of(1, 1), -1, -1);

    private final String name;
    private final String description;
    private final int price;
    private final List<GameObject> requirements;
    private final List<Integer> size;
    private final int capacity;
    private final int dailyLimit;

    FarmBuildingType(String name, String description, int price, List<GameObject> requirements, List<Integer> size, int capacity, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.requirements = requirements;
        this.size = size;
        this.capacity = capacity;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public List<GameObject> getRequirements() {
        return requirements;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public int getCapacity() {
        return capacity;
    }

    public static FarmBuildingType getFarmBuilding(String name)
    {
        for (FarmBuildingType farmBuilding : FarmBuildingType.values())
        {
            if (farmBuilding.getName().equalsIgnoreCase(name))
            {
                return farmBuilding;
            }
        }
        return null;
    }

    public int getWoodNumber()
    {
        for (GameObject gameObject : requirements)
        {
            if (gameObject.getObjectType().equals(GameObjectType.WOOD))
            {
                return gameObject.getNumber();
            }
        }
        return 0;
    }

    public int getStoneNumber()
    {
        for (GameObject gameObject : requirements)
        {
            if (gameObject.getObjectType().equals(GameObjectType.STONE))
            {
                return gameObject.getNumber();
            }
        }
        return 0;
    }

    public int getWidth()
    {
        return size.get(0);
    }

    public int getHeight()
    {
        return size.get(1);
    }
}
