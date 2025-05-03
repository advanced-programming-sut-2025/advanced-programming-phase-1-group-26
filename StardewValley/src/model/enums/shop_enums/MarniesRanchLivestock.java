package model.enums.shop_enums;

public enum MarniesRanchLivestock {
    CHICKEN("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.",
            800, CarpentersShopFarmBuilding.COOP, 2),
    COW("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.",
            1500, CarpentersShopFarmBuilding.BARN, 2),
    GOAT("Goat", "Happy goats provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.",
            4000, CarpentersShopFarmBuilding.BIG_BARN, 2),
    DUCK("Duck", "Happy ducks lay duck eggs every other day. Lives in the coop.",
            1200, CarpentersShopFarmBuilding.BIG_COOP, 2),
    SHEEP("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.",
            8000, CarpentersShopFarmBuilding.DELUXE_BARN, 2),
    RABBIT("Rabbit", "These are wooly rabbits! They shed precious wool every few days. Lives in the coop.",
            8000, CarpentersShopFarmBuilding.DELUXE_COOP, 2),
    DINOSAUR("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop.",
            14000, CarpentersShopFarmBuilding.BIG_COOP, 2),
    PIG("Pig", "These pigs are trained to find truffles! Lives in the barn.",
            16000, CarpentersShopFarmBuilding.DELUXE_BARN, 2);

    private final String name;
    private final String description;
    private final int price;
    private final CarpentersShopFarmBuilding buildingRequired;
    private final int dailyLimit;

    MarniesRanchLivestock(String name, String description, int price, CarpentersShopFarmBuilding buildingRequired, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.buildingRequired = buildingRequired;
        this.dailyLimit = dailyLimit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public CarpentersShopFarmBuilding getBuildingRequired() {
        return buildingRequired;
    }
    public int getDailyLimit() { return dailyLimit; }
}
