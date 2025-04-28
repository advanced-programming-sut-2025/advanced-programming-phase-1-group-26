package model.enums.shop_enums;

public enum CarpentersShopFarmBuilding {
    BARN("Barn", "Houses 4 barn-dwelling animals.",
            6000, 350, 150, "7x4", 1),
    BIG_BARN("Big Barn", "Houses 8 barn-dwelling animals. Unlocks goats.",
            12000, 450, 200, "7x4", 1),
    DELUXE_BARN("Deluxe Barn", "Houses 12 barn-dwelling animals. Unlocks sheep and pigs.",
            25000, 550, 300, "7x4", 1),
    COOP("Coop", "Houses 4 coop-dwelling animals.",
            4000, 300, 100, "6x3", 1),
    BIG_COOP("Big Coop", "Houses 8 coop-dwelling animals. Unlocks ducks.",
            10000, 400, 150, "6x3", 1),
    DELUXE_COOP("Deluxe Coop", "Houses 12 coop-dwelling animals. Unlocks rabbits.",
            20000, 500, 200, "6x3", 1),
    WELL("Well", "Provides a place for you to refill your watering can.",
            1000, 0, 75, "3x3", 1),
    SHIPPING_BIN("Shipping Bin", "Items placed in it will be included in the nightly shipment.",
            250, 150, 0, "1x1", -1);

    private final String name;
    private final String description;
    private final int goldCost;
    private final int woodRequired;
    private final int stoneRequired;
    private final String size;
    private final int dailyLimit;

    CarpentersShopFarmBuilding(String name, String description, int goldCost, int woodRequired, int stoneRequired, String size, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.goldCost = goldCost;
        this.woodRequired = woodRequired;
        this.stoneRequired = stoneRequired;
        this.size = size;
        this.dailyLimit = dailyLimit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getGoldCost() { return goldCost; }
    public int getWoodRequired() { return woodRequired; }
    public int getStoneRequired() { return stoneRequired; }
    public String getSize() { return size; }
    public int getDailyLimit() { return dailyLimit; }
}
