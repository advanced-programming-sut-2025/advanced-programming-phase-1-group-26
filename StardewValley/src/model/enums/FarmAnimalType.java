package model.enums;

public enum FarmAnimalType {
    CHICKEN("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.", 800, "Coop"),
    COW("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.", 1500, "Barn"),
    GOAT("Goat", "Happy goats provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.", 4000, "Big Barn"),
    DUCK("Duck", "Happy ducks lay duck eggs every other day. Lives in the coop.", 1200, "Big Coop"),
    SHEEP("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.", 8000, "Deluxe Barn"),
    RABBIT("Rabbit", "These are wooly rabbits! They shed precious wool every few days. Lives in the coop.", 8000, "Deluxe Coop"),
    DINOSAUR("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop.", 14000, "Big Coop"),
    PIG("Pig", "These pigs are trained to find truffles! Lives in the barn.", 16000, "Deluxe Barn");

    private final String name;
    private final String description;
    private final int price;
    private final String buildingRequired;

    FarmAnimalType(String name, String description, int price, String buildingRequired) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.buildingRequired = buildingRequired;
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

    public String getBuildingRequired() {
        return buildingRequired;
    }
}
