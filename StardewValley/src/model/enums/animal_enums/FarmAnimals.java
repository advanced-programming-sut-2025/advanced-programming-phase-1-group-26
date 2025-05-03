package model.enums.animal_enums;

public enum FarmAnimals {
    CHICKEN("Chicken", 800,
            new Product[] {
                    new Product("Egg", 50),
                    new Product("Large Egg", 95)
            },
            "Lives in a coop with a capacity of 4. Produces daily if well cared for. Can also live in upgraded coops.",
            AnimalType.COOP, FarmBuilding.COOP),

    DUCK("Duck", 1200,
            new Product[] {
                    new Product("Duck Egg", 95),
                    new Product("Duck Feather", 250)
            },
            "Needs a Big Coop (capacity 8). Produces every 2 days. Happier ducks are more likely to produce Duck Feathers. Can also live in Deluxe Coop.",
            AnimalType.COOP, FarmBuilding.BIG_COOP),

    RABBIT("Rabbit", 8000,
            new Product[] {
                    new Product("Wool", 340),
                    new Product("Rabbit's Foot", 565)
            },
            "Needs a Deluxe Coop (capacity 12). Produces every 4 days. Happier rabbits have a higher chance of producing Rabbit’s Foot.",
            AnimalType.COOP, FarmBuilding.DELUXE_COOP),

    DINOSAUR("Dinosaur", 14000,
            new Product[] {
                    new Product("Dinosaur Egg", 350)
            },
            "Needs a Big Coop (capacity 8). Produces every 7 days.",
            AnimalType.COOP, FarmBuilding.BIG_COOP),

    COW("Cow", 1500,
            new Product[] {
                    new Product("Milk", 125),
                    new Product("Large Milk", 190)
            },
            "Produces milk daily if fed. Requires a milk pail to collect. Lives in a barn with a capacity of 4. Can also live in upgraded barns.",
            AnimalType.BARN, FarmBuilding.BARN),

    GOAT("Goat", 4000,
            new Product[] {
                    new Product("Goat Milk", 225),
                    new Product("Large Goat Milk", 345)
            },
            "Fed goats produce milk every 2 days. Requires a milk pail. Needs a Big Barn (capacity 8). Can also live in Deluxe Barn.",
            AnimalType.BARN, FarmBuilding.BIG_BARN),

    SHEEP("Sheep", 8000,
            new Product[] {
                    new Product("Wool", 340)
            },
            "A fed sheep with at least 70 happiness produces wool every 3 days. Requires shears. Needs a Deluxe Barn (capacity 12).",
            AnimalType.BARN, FarmBuilding.DELUXE_BARN),

    PIG("Pig", 16000,
            new Product[] {
                    new Product("Truffle", 625)
            },
            "Finds truffles after leaving the barn. Does not produce during winter since they stay indoors. Needs a Deluxe Barn.",
            AnimalType.BARN, FarmBuilding.DELUXE_BARN),;

    private final String name;
    private final int purchaseCost;
    private final Product[] products;
    private final String description;
    private final AnimalType animalType;
    private final FarmBuilding building;

    FarmAnimals(String name, int purchaseCost, Product[] products, String description, AnimalType animalType, FarmBuilding building) {
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

    public Product[] getProducts() {
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

    public record Product(String name, int price) {
    }
}
