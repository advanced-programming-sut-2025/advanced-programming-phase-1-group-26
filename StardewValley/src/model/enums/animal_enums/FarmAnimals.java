package model.enums.animal_enums;

import model.enums.GameObjectType;

import java.util.ArrayList;
import java.util.List;

public enum FarmAnimals {
    CHICKEN("Chicken", 800,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.EGG, 50),
                    new Product(GameObjectType.LARGE_EGG, 95)
            ),
            "Lives in a coop with a capacity of 4. Produces daily if well cared for. Can also live in upgraded coops.",
            AnimalType.COOP, FarmBuilding.COOP),

    DUCK("Duck", 1200,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.DUCK_EGG, 95),
                    new Product(GameObjectType.DUCK_FEATHER, 250)
            ),
            "Needs a Big Coop (capacity 8). Produces every 2 days. Happier ducks are more likely to produce Duck Feathers. Can also live in Deluxe Coop.",
            AnimalType.COOP, FarmBuilding.BIG_COOP),

    RABBIT("Rabbit", 8000,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.WOOL, 340),
                    new Product(GameObjectType.RABBITS_FOOT, 565)
            ),
            "Needs a Deluxe Coop (capacity 12). Produces every 4 days. Happier rabbits have a higher chance of producing Rabbit’s Foot.",
            AnimalType.COOP, FarmBuilding.DELUXE_COOP),

    DINOSAUR("Dinosaur", 14000,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.DINOSAUR_EGG, 350)
            ),
            "Needs a Big Coop (capacity 8). Produces every 7 days.",
            AnimalType.COOP, FarmBuilding.BIG_COOP),

    COW("Cow", 1500,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.MILK, 125),
                    new Product(GameObjectType.LARGE_MILK, 190)
            ),
            "Produces milk daily if fed. Requires a milk pail to collect. Lives in a barn with a capacity of 4. Can also live in upgraded barns.",
            AnimalType.BARN, FarmBuilding.BARN),

    GOAT("Goat", 4000,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.GOAT_MILK, 225),
                    new Product(GameObjectType.LARGE_GOAT_MILK, 345)
            ),
            "Fed goats produce milk every 2 days. Requires a milk pail. Needs a Big Barn (capacity 8). Can also live in Deluxe Barn.",
            AnimalType.BARN, FarmBuilding.BIG_BARN),

    SHEEP("Sheep", 8000,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.WOOL, 340)
            ),
            "A fed sheep with at least 70 happiness produces wool every 3 days. Requires shears. Needs a Deluxe Barn (capacity 12).",
            AnimalType.BARN, FarmBuilding.DELUXE_BARN),

    PIG("Pig", 16000,
            (ArrayList<Product>) List.of(
                    new Product(GameObjectType.TRUFFLE, 625)
            ),
            "Finds truffles after leaving the barn. Does not produce during winter since they stay indoors. Needs a Deluxe Barn.",
            AnimalType.BARN, FarmBuilding.DELUXE_BARN);

    private final String name;
    private final int purchaseCost;
    private final ArrayList<Product> products;
    private final String description;
    private final AnimalType animalType;
    private final FarmBuilding building;

    FarmAnimals(String name, int purchaseCost, ArrayList<Product> products, String description,
                AnimalType animalType, FarmBuilding building) {
        this.name = name;
        this.purchaseCost = purchaseCost;
        this.products = products;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public FarmBuilding getBuilding() {
        return building;
    }

    public record Product(GameObjectType type, int price) {
        @Override
        public GameObjectType type() {
            return type;
        }

        @Override
        public int price() {
            return price;
        }

    }

}