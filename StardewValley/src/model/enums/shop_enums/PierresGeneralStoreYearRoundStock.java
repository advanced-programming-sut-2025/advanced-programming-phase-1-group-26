package model.enums.shop_enums;

import model.enums.GameObjectType;
import model.enums.Season;

public enum PierresGeneralStoreYearRoundStock { //TODO needs some coding
    // Year-Round Stock
    RICE("Rice", "A basic grain often served under vegetables.",
            200, -1, null, GameObjectType.RICE),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.",
            100, -1, null, GameObjectType.WHEAT_FLOWER),
    BOUQUET("Bouquet", "A gift that shows your romantic interest. (Unlocked after level 2 friendship)",
            1000, 2, null, GameObjectType.BOUQUET),
    WEDDING_RING("Wedding Ring", "Used to ask for marriage. (Unlocked after level 3 friendship)",
            10000, 2, null, GameObjectType.WEDDING_RING),
    DEHYDRATOR_RECIPE("Dehydrator (Recipe)", "A recipe to make Dehydrator",
            10000, 1, null, GameObjectType.DEHYDRATOR_RECIPE),
    GRASS_STARTER_RECIPE("Grass Starter (Recipe)", "A recipe to make Grass Starter",
            1000, 1, null, GameObjectType.GRASS_STARTER_RECIPE),
    SUGAR("Sugar", "Adds sweetness to pastries and candies.",
            100, -1, null, GameObjectType.SUGAR),
    OIL("Oil", "All-purpose cooking oil.",
            200, -1, null, GameObjectType.OIL),
    VINEGAR("Vinegar", "Used in many cooking recipes.",
            200, -1, null, GameObjectType.VINEGAR),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", "100% chance of staying watered overnight.",
            150, -1, null, GameObjectType.DELUXE_RETAINING_SOIL),
    GRASS_STARTER("Grass Starter", "Starts a new patch of grass.",
            100, -1, null, GameObjectType.GRAPE_STARTER),
    SPEED_GRO("Speed-Gro", "Makes plants grow 1 day earlier.",
            100, -1, null, GameObjectType.SPEED_GRO),
    APPLE_SAPLING("Apple Sapling", "Matures in 28 days. Fruits in fall.",
            4000, -1, null, GameObjectType.Apple_Sapling),
    APRICOT_SAPLING("Apricot Sapling", "Matures in 28 days. Fruits in spring.",
            2000, -1, null, GameObjectType.Apricot_Sapling),
    CHERRY_SAPLING("Cherry Sapling", "Matures in 28 days. Fruits in spring.",
            3400, -1, null, GameObjectType.Cherry_Sapling),
    ORANGE_SAPLING("Orange Sapling", "Matures in 28 days. Fruits in summer.",
            4000, -1, null, GameObjectType.Orange_Sapling),
    PEACH_SAPLING("Peach Sapling", "Matures in 28 days. Fruits in summer.",
            6000, -1, null, GameObjectType.Peach_Sapling),
    POMEGRANATE_SAPLING("Pomegranate Sapling", "Matures in 28 days. Fruits in fall.",
            6000, -1, null, GameObjectType.Pomegranate_Sapling),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", "Chance of staying watered overnight.",
            100, -1, null, GameObjectType.BASIC_RETAINING_SOIL),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", "Good chance of staying watered overnight.",
            150, -1, null, GameObjectType.QUALITY_RETAINING_SOIL),

    // Spring Items Example
    PARSNIP_SEEDS("Parsnip Seeds", "Plant in spring. 4 days to mature.",
            25, 5, Season.Spring, GameObjectType.PARSNIP_SEEDS),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant in spring. 12 days to mature.",
            100, 5, Season.Spring, GameObjectType.CAULIFLOWER_SEEDS),
    STRAWBERRY_SEEDS("Strawberry Seeds", "Plant in spring. 8 days to mature, keeps producing.",
            100, 5, Season.Spring, GameObjectType.STRAWBERRY_SEEDS),
    COFFEE_BEANS("Coffee Beans", "Grows in spring or summer. 10 days to grow, produces every other day.",
            200, 1, Season.Spring, GameObjectType.COFFEE),

    ;

    private final String displayName;
    private final String description;
    private final int price;
    private final int dailyLimit; // -1 for unlimited
    private final Season season; // null for year-round
    private final GameObjectType gameObjectType;


    PierresGeneralStoreYearRoundStock(String displayName, String description, int price, int dailyLimit,
                                      Season season, GameObjectType gameObjectType) {
        this.displayName = displayName;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.season = season;
        this.gameObjectType = gameObjectType;
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
}
