package model.enums.shop_enums;

import model.enums.GameObjectType;
import model.enums.Season;

public enum PierresGeneralStoreYearRoundStock { //TODO needs some coding
    // Year-Round Stock
    RICE("Rice", "A basic grain often served under vegetables.",
            200, -1, null, GameObjectType.RICE, -1),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.",
            100, -1, null, GameObjectType.WHEAT_FLOWER, -1),
    BOUQUET("Bouquet", "A gift that shows your romantic interest. (Unlocked after level 2 friendship)",
            1000, 2, null, GameObjectType.BOUQUET, 2),
    WEDDING_RING("Wedding Ring", "Used to ask for marriage. (Unlocked after level 3 friendship)",
            10000, 2, null, GameObjectType.WEDDING_RING, 2),
    DEHYDRATOR_RECIPE("Dehydrator (Recipe)", "A recipe to make Dehydrator",
            10000, 1, null, GameObjectType.DEHYDRATOR_RECIPE, 1),
    GRASS_STARTER_RECIPE("Grass Starter (Recipe)", "A recipe to make Grass Starter",
            1000, 1, null, GameObjectType.GRASS_STARTER_RECIPE, 1),
    SUGAR("Sugar", "Adds sweetness to pastries and candies.",
            100, -1, null, GameObjectType.SUGAR, -1),
    OIL("Oil", "All-purpose cooking oil.",
            200, -1, null, GameObjectType.OIL, -1),
    VINEGAR("Vinegar", "Used in many cooking recipes.",
            200, -1, null, GameObjectType.VINEGAR, -1),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", "100% chance of staying watered overnight.",
            150, -1, null, GameObjectType.DELUXE_RETAINING_SOIL, -1),
    GRASS_STARTER("Grass Starter", "Starts a new patch of grass.",
            100, -1, null, GameObjectType.GRAPE_STARTER, -1),
    SPEED_GRO("Speed-Gro", "Makes plants grow 1 day earlier.",
            100, -1, null, GameObjectType.SPEED_GRO, -1),
    APPLE_SAPLING("Apple Sapling", "Matures in 28 days. Fruits in fall.",
            4000, -1, null, GameObjectType.Apple_Sapling, -1),
    APRICOT_SAPLING("Apricot Sapling", "Matures in 28 days. Fruits in spring.",
            2000, -1, null, GameObjectType.Apricot_Sapling, -1),
    CHERRY_SAPLING("Cherry Sapling", "Matures in 28 days. Fruits in spring.",
            3400, -1, null, GameObjectType.Cherry_Sapling, -1),
    ORANGE_SAPLING("Orange Sapling", "Matures in 28 days. Fruits in summer.",
            4000, -1, null, GameObjectType.Orange_Sapling, -1),
    PEACH_SAPLING("Peach Sapling", "Matures in 28 days. Fruits in summer.",
            6000, -1, null, GameObjectType.Peach_Sapling, -1),
    POMEGRANATE_SAPLING("Pomegranate Sapling", "Matures in 28 days. Fruits in fall.",
            6000, -1, null, GameObjectType.Pomegranate_Sapling, -1),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", "Chance of staying watered overnight.",
            100, -1, null, GameObjectType.BASIC_RETAINING_SOIL, -1),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", "Good chance of staying watered overnight.",
            150, -1, null, GameObjectType.QUALITY_RETAINING_SOIL, -1),


    ;

    private final String displayName;
    private final String description;
    private final int price;
    private final int dailyLimit; // -1 for unlimited
    private final Season season; // null for year-round
    private final GameObjectType gameObjectType;
    private int limit;


    PierresGeneralStoreYearRoundStock(String displayName, String description, int price, int dailyLimit,
                                      Season season, GameObjectType gameObjectType, int limit) {
        this.displayName = displayName;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.season = season;
        this.gameObjectType = gameObjectType;
        this.limit = limit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public Season getSeason() {
        return season;
    }

    public boolean isAvailableInSeason(Season currentSeason) {
        return season == null || season == currentSeason;
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void decreaseLimit() {
        this.limit--;
    }

    public void resetLimit() {
        for(PierresGeneralStoreYearRoundStock gameObjectType : PierresGeneralStoreYearRoundStock.values()) {
            gameObjectType.setLimit(getDailyLimit());
        }
    }
}
