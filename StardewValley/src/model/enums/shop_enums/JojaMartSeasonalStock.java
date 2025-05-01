package model.enums.shop_enums;

import model.enums.Season;

import java.util.Arrays;
import java.util.List;

public enum JojaMartSeasonalStock {
    // Spring
    PARSNIP_SEEDS("Parsnip Seeds", "Plant these in the spring. Takes 4 days to mature.",
            25, 5, Season.Spring),
    BEAN_STARTER("Bean Starter", "Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis.",
            75, 5, Season.Spring),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant these in the spring. Takes 12 days to produce a large cauliflower.",
            100, 5, Season.Spring),
    POTATO_SEEDS("Potato Seeds", "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest.",
            62, 5, Season.Spring),
    STRAWBERRY_SEEDS("Strawberry Seeds", "Plant these in spring. Takes 8 days to mature, and keeps producing strawberries after that.",
            100, 5, Season.Spring),
    TULIP_BULB("Tulip Bulb", "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors.",
            25, 5, Season.Spring),
    KALE_SEEDS("Kale Seeds", "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe.",
            87, 5, Season.Spring),
    COFFEE_BEANS_SPRING("Coffee Beans", "Plant in summer or spring. Takes 10 days to grow, Then produces coffee Beans every other day.",
            200, 1, Season.Spring),
    CARROT_SEEDS("Carrot Seeds", "Plant in the spring. Takes 3 days to grow.",
            5, 10, Season.Spring),
    RHUBARB_SEEDS("Rhubarb Seeds", "Plant these in the spring. Takes 13 days to mature.",
            100, 5, Season.Spring),
    JAZZ_SEEDS("Jazz Seeds", "Plant in spring. Takes 7 days to produce a blue puffball flower.",
            37, 5, Season.Spring),

    // Summer
    TOMATO_SEEDS("Tomato Seeds", "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest.",
            62, 5, Season.Summer),
    PEPPER_SEEDS("Pepper Seeds", "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest.",
            50, 5, Season.Summer),
    WHEAT_SEEDS_SUMMER("Wheat Seeds", "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.",
            12, 10, Season.Summer),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", "Plant in the summer. Takes 6 days to grow, and continues to produce after first harvest.",
            10, 10, Season.Summer),
    RADISH_SEEDS("Radish Seeds", "Plant these in the summer. Takes 6 days to mature.",
            50, 5, Season.Summer),
    MELON_SEEDS("Melon Seeds", "Plant these in the summer. Takes 12 days to mature.",
            100, 5, Season.Summer),
    HOPS_STARTER("Hops Starter", "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis.",
            75, 5, Season.Summer),
    POPPY_SEEDS("Poppy Seeds", "Plant in summer. Produces a bright red flower in 7 days.",
            125, 5, Season.Summer),
    SPANGLE_SEEDS("Spangle Seeds", "Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors.",
            62, 5, Season.Summer),
    STARFRUIT_SEEDS("Starfruit Seeds", "Plant these in the summer. Takes 13 days to mature.",
            400, 5, Season.Summer),
    COFFEE_BEANS_SUMMER("Coffee Beans", "Plant in summer or spring. Takes 10 days to grow, Then produces coffee Beans every other day.",
            200, 1, Season.Summer),
    SUNFLOWER_SEEDS_SUMMER("Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.",
            125, 5, Season.Summer),

    // Fall
    CORN_SEEDS("Corn Seeds", "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.",
            187, 5, Season.Fall),
    EGGPLANT_SEEDS("Eggplant Seeds", "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest.",
            25, 5, Season.Fall),
    PUMPKIN_SEEDS("Pumpkin Seeds", "Plant these in the fall. Takes 13 days to mature.",
            125, 5, Season.Fall),
    BROCCOLI_SEEDS("Broccoli Seeds", "Plant in the fall. Takes 8 days to mature, and continues to produce after first harvest.",
            15, 5, Season.Fall),
    AMARANTH_SEEDS("Amaranth Seeds", "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe.",
            87, 5, Season.Fall),
    GRAPE_STARTER("Grape Starter", "Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis.",
            75, 5, Season.Fall),
    BEET_SEEDS("Beet Seeds", "Plant these in the fall. Takes 6 days to mature.",
            20, 5, Season.Fall),
    YAM_SEEDS("Yam Seeds", "Plant these in the fall. Takes 10 days to mature.",
            75, 5, Season.Fall),
    BOK_CHOY_SEEDS("Bok Choy Seeds", "Plant these in the fall. Takes 4 days to mature.",
            62, 5, Season.Fall),
    CRANBERRY_SEEDS("Cranberry Seeds", "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest.",
            300, 5, Season.Fall),
    SUNFLOWER_SEEDS_FALL("Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.",
            125, 5, Season.Fall),
    FAIRY_SEEDS("Fairy Seeds", "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors.",
            250, 5, Season.Fall),
    RARE_SEED("Rare Seed", "Sow in fall. Takes all season to grow.",
            1000, 1, Season.Fall),
    WHEAT_SEEDS_FALL("Wheat Seeds", "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.",
            12, 5, Season.Fall),

    // Winter
    POWDERMELON_SEEDS("Powdermelon Seeds", "This special melon grows in the winter. Takes 7 days to grow.",
            20, 10, Season.Winter);

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private final Season season;

    JojaMartSeasonalStock(String name, String description, int price, int dailyLimit, Season season) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.season = season;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
    public Season getSeason() { return season; }

    // Optional: Filter by season
    public static List<JojaMartSeasonalStock> getBySeason(Season season) {
        return Arrays.stream(values())
                .filter(item -> item.getSeason() == season)
                .toList();
    }
}
