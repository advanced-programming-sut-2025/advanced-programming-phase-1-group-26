package model.enums.shop_enums;

import model.enums.Season;

public enum PierresGeneralStoreYearRoundStock { //TODO needs some coding
    // Year-Round Stock
    RICE("Rice", "A basic grain often served under vegetables.",
            200, -1, null),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.",
            100, -1, null),
    BOUQUET("Bouquet", "A gift that shows your romantic interest. (Unlocked after level 2 friendship)",
            1000, 2, null),
    WEDDING_RING("Wedding Ring", "Used to ask for marriage. (Unlocked after level 3 friendship)",
            10000, 2, null),
    DEHYDRATOR_RECIPE("Dehydrator (Recipe)", "A recipe to make Dehydrator",
            10000, 1, null),
    GRASS_STARTER_RECIPE("Grass Starter (Recipe)", "A recipe to make Grass Starter",
            1000, 1, null),
    SUGAR("Sugar", "Adds sweetness to pastries and candies.",
            100, -1, null),
    OIL("Oil", "All-purpose cooking oil.",
            200, -1, null),
    VINEGAR("Vinegar", "Used in many cooking recipes.",
            200, -1, null),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", "100% chance of staying watered overnight.",
            150, -1, null),
    GRASS_STARTER("Grass Starter", "Starts a new patch of grass.",
            100, -1, null),
    SPEED_GRO("Speed-Gro", "Makes plants grow 1 day earlier.",
            100, -1, null),
    APPLE_SAPLING("Apple Sapling", "Matures in 28 days. Fruits in fall.",
            4000, -1, null),
    APRICOT_SAPLING("Apricot Sapling", "Matures in 28 days. Fruits in spring.",
            2000, -1, null),
    CHERRY_SAPLING("Cherry Sapling", "Matures in 28 days. Fruits in spring.",
            3400, -1, null),
    ORANGE_SAPLING("Orange Sapling", "Matures in 28 days. Fruits in summer.",
            4000, -1, null),
    PEACH_SAPLING("Peach Sapling", "Matures in 28 days. Fruits in summer.",
            6000, -1, null),
    POMEGRANATE_SAPLING("Pomegranate Sapling", "Matures in 28 days. Fruits in fall.",
            6000, -1, null),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", "Chance of staying watered overnight.",
            100, -1, null),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", "Good chance of staying watered overnight.",
            150, -1, null),

    // Spring Items Example
    PARSNIP_SEEDS("Parsnip Seeds", "Plant in spring. 4 days to mature.",
            25, 5, Season.Spring),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant in spring. 12 days to mature.",
            100, 5, Season.Spring),
    STRAWBERRY_SEEDS("Strawberry Seeds", "Plant in spring. 8 days to mature, keeps producing.",
            100, 5, Season.Spring),
    COFFEE_BEANS("Coffee Beans", "Grows in spring or summer. 10 days to grow, produces every other day.",
            200, 1, Season.Spring),

    ;

    private final String displayName;
    private final String description;
    private final int price;
    private final int dailyLimit; // -1 for unlimited
    private final Season season; // null for year-round

    PierresGeneralStoreYearRoundStock(String displayName, String description, int price, int dailyLimit, Season season) {
        this.displayName = displayName;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.season = season;
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
}
