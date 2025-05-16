package model.enums.animal_enums;

import model.GameObject;
import model.enums.GameObjectType;

import java.util.ArrayList;
import java.util.List;

public enum FarmAnimalsType
{
    CHICKEN(GameObjectType.CHICKEN, "Chicken", 800,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.EGG, 50),
                    new GameObject(GameObjectType.LARGE_EGG, 95)
            ),
            "Lives in a coop with a capacity of 4. Produces daily if well cared for. Can also live in upgraded coops.",
            AnimalType.COOP, FarmBuildingType.COOP),

    DUCK(GameObjectType.DUCK, "Duck", 1200,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.DUCK_EGG, 95),
                    new GameObject(GameObjectType.DUCK_FEATHER, 250)
            ),
            "Needs a Big Coop (capacity 8). Produces every 2 days. Happier ducks are more likely to produce Duck Feathers. Can also live in Deluxe Coop.",
            AnimalType.COOP, FarmBuildingType.BIG_COOP),

    RABBIT(GameObjectType.RABBIT, "Rabbit", 8000,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.WOOL, 340),
                    new GameObject(GameObjectType.RABBITS_FOOT, 565)
            ),
            "Needs a Deluxe Coop (capacity 12). Produces every 4 days. Happier rabbits have a higher chance of producing Rabbit’s Foot.",
            AnimalType.COOP, FarmBuildingType.DELUXE_COOP),

    DINOSAUR(GameObjectType.DINOSAUR, "Dinosaur", 14000,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.DINOSAUR_EGG, 350)
            ),
            "Needs a Big Coop (capacity 8). Produces every 7 days.",
            AnimalType.COOP, FarmBuildingType.BIG_COOP),

    COW(GameObjectType.COW, "Cow", 1500,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.MILK, 125),
                    new GameObject(GameObjectType.LARGE_MILK, 190)
            ),
            "Produces milk daily if fed. Requires a milk pail to collect. Lives in a barn with a capacity of 4. Can also live in upgraded barns.",
            AnimalType.BARN, FarmBuildingType.BARN),

    GOAT(GameObjectType.GOAT, "Goat", 4000,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.GOAT_MILK, 225),
                    new GameObject(GameObjectType.LARGE_GOAT_MILK, 345)
            ),
            "Fed goats produce milk every 2 days. Requires a milk pail. Needs a Big Barn (capacity 8). Can also live in Deluxe Barn.",
            AnimalType.BARN, FarmBuildingType.BIG_BARN),

    SHEEP(GameObjectType.SHEEP, "Sheep", 8000,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.WOOL, 340)
            ),
            "A fed sheep with at least 70 happiness produces wool every 3 days. Requires shears. Needs a Deluxe Barn (capacity 12).",
            AnimalType.BARN, FarmBuildingType.DELUXE_BARN),

    PIG(GameObjectType.PIG, "Pig", 16000,
            (ArrayList<GameObject>) List.of(
                    new GameObject(GameObjectType.TRUFFLE, 625)
            ),
            "Finds truffles after leaving the barn. Does not produce during winter since they stay indoors. Needs a Deluxe Barn.",
            AnimalType.BARN, FarmBuildingType.DELUXE_BARN);

    private final GameObjectType type;
    private final String name;
    private final int purchaseCost;
    private final ArrayList<GameObject> GameObjects;
    private final String description;
    private final AnimalType animalType;
    private final FarmBuildingType building;

    FarmAnimalsType(GameObjectType type, String name, int purchaseCost, ArrayList<GameObject> GameObjects, String description,
                    AnimalType animalType, FarmBuildingType building) {
        this.type = type;
        this.name = name;
        this.purchaseCost = purchaseCost;
        this.GameObjects = GameObjects;
        this.description = description;
        this.animalType = animalType;
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public int getPurchaseCost() {
        return purchaseCost;
    }

    public ArrayList<GameObject> getProducts() {
        return GameObjects;
    }

    public String getDescription() {
        return description;
    }


    public FarmBuildingType getBuilding() {
        return building;
    }

    public static FarmAnimalsType getFarmAnimalsType(String name)
    {
        for (FarmAnimalsType type : FarmAnimalsType.values())
        {
            if (type.name().equalsIgnoreCase(name))
            {
                return type;
            }
        }
        return null;
    }
}