package model.enums.shop_enums;

import model.enums.animal_enums.FarmAnimalsType;

public enum MarniesRanchLivestock {
    CHICKEN("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.",
            800, CarpentersShopFarmBuilding.COOP, 2, FarmAnimalsType.CHICKEN),
    COW("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.",
            1500, CarpentersShopFarmBuilding.BARN, 2, FarmAnimalsType.COW),
    GOAT("Goat", "Happy goats provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.",
            4000, CarpentersShopFarmBuilding.BIG_BARN, 2, FarmAnimalsType.GOAT),
    DUCK("Duck", "Happy ducks lay duck eggs every other day. Lives in the coop.",
            1200, CarpentersShopFarmBuilding.BIG_COOP, 2, FarmAnimalsType.DUCK),
    SHEEP("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.",
            8000, CarpentersShopFarmBuilding.DELUXE_BARN, 2, FarmAnimalsType.SHEEP),
    RABBIT("Rabbit", "These are wooly rabbits! They shed precious wool every few days. Lives in the coop.",
            8000, CarpentersShopFarmBuilding.DELUXE_COOP, 2, FarmAnimalsType.RABBIT),
    DINOSAUR("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop.",
            14000, CarpentersShopFarmBuilding.BIG_COOP, 2, FarmAnimalsType.DINOSAUR),
    PIG("Pig", "These pigs are trained to find truffles! Lives in the barn.",
            16000, CarpentersShopFarmBuilding.DELUXE_BARN, 2, FarmAnimalsType.PIG),
    ;

    private final String name;
    private final String description;
    private final int price;
    private final CarpentersShopFarmBuilding buildingRequired;
    private int dailyLimit;
    private final FarmAnimalsType animalType;

    MarniesRanchLivestock(String name, String description, int price, CarpentersShopFarmBuilding buildingRequired,
                          int dailyLimit, FarmAnimalsType animalType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.buildingRequired = buildingRequired;
        this.dailyLimit = dailyLimit;
        this.animalType = animalType;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public CarpentersShopFarmBuilding getBuildingRequired() {
        return buildingRequired;
    }
    public int getDailyLimit() { return dailyLimit; }

    public FarmAnimalsType getAnimalType() {
        return animalType;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void decreaseDailyLimit() {
        this.dailyLimit -= 1;
    }

    public void resetDailyLimit() {
        this.dailyLimit = 2;
    }
}
