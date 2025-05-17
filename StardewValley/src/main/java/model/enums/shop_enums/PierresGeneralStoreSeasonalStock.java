package model.enums.shop_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.Set;

public enum PierresGeneralStoreSeasonalStock {
    PARSNIP_SEEDS("Parsnip Seeds", "Plant these in the spring. Takes 4 days to mature.",
            20, 30, 5, GameObjectType.PARSNIP_SEEDS, Season.Spring),
    BEAN_STARTER("Bean Starter", "Plant these in the spring. Takes 10 days to mature, but keeps producing. Grows on a trellis.",
            60, 90, 5, GameObjectType.BEAN_STARTER, Season.Spring),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant these in the spring. Takes 12 days to produce a large cauliflower.",
            80, 120, 5, GameObjectType.CAULIFLOWER_SEEDS, Season.Spring),
    POTATO_SEEDS("Potato Seeds", "Plant these in the spring. Takes 6 days to mature, may yield multiple potatoes.",
            50, 75, 5, GameObjectType.POTATO_SEEDS, Season.Spring),
    TULIP_BULB("Tulip Bulb", "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors.",
            20, 30, 5, GameObjectType.TULIP_BULB, Season.Spring),
    KALE_SEEDS("Kale Seeds", "Plant these in the spring. Takes 6 days to mature. Harvest with scythe.",
            70, 105, 5, GameObjectType.KALE_SEEDS, Season.Spring),
    JAZZ_SEEDS("Jazz Seeds", "Plant in spring. Takes 7 days to produce a blue puffball flower.",
            30, 45, 5, GameObjectType.JAZZ_SEEDS, Season.Spring),
    GARLIC_SEEDS("Garlic Seeds", "Plant these in the spring. Takes 4 days to mature.",
            40, 60, 5, GameObjectType.GARLIC_SEEDS, Season.Spring),
    RICE_SHOOT("Rice Shoot", "Plant in spring. Takes 8 days. Grows faster near water. Harvest with scythe.",
            40, 60, 5, GameObjectType.RICE_SHOOT, Season.Spring),

    MELON_SEEDS("Melon Seeds", "Plant these in the summer. Takes 12 days to mature.",
            80, 120, 5, GameObjectType.MELON_SEEDS, Season.Summer),
    TOMATO_SEEDS("Tomato Seeds", "Plant these in the summer. Takes 11 days. Keeps producing.",
            50, 75, 5, GameObjectType.TOMATO_SEEDS, Season.Summer),
    BLUEBERRY_SEEDS("Blueberry Seeds", "Plant these in the summer. Takes 13 days. Keeps producing.",
            80, 120, 5, GameObjectType.BLUEBERRY_SEEDS, Season.Summer),
    PEPPER_SEEDS("Pepper Seeds", "Plant these in the summer. Takes 5 days. Keeps producing.",
            40, 60, 5, GameObjectType.PEPPER_SEEDS, Season.Summer),
    WHEAT_SEEDS("Wheat Seeds", "Plant in summer or fall. Takes 4 days. Harvest with scythe.",
            10, 15, 5, GameObjectType.WHEAT_SEEDS, Season.Summer, Season.Fall),
    RADISH_SEEDS("Radish Seeds", "Plant these in the summer. Takes 6 days to mature.",
            40, 60, 5, GameObjectType.RADISH_SEEDS, Season.Summer),
    POPPY_SEEDS("Poppy Seeds", "Plant in summer. Produces bright red flower in 7 days.",
            100, 150, 5, GameObjectType.POPPY_SEEDS, Season.Summer),
    SPANGLE_SEEDS("Spangle Seeds", "Plant in summer. Takes 8 days. Assorted colors.",
            50, 75, 5, GameObjectType.SPANGLE_SEEDS, Season.Summer),
    HOPS_STARTER("Hops Starter", "Plant these in the summer. Takes 11 days. Keeps producing. Grows on a trellis.",
            60, 90, 5, GameObjectType.HOPS_STARTER, Season.Summer),
    CORN_SEEDS("Corn Seeds", "Plant in summer or fall. Takes 14 days. Keeps producing.",
            150, 225, 5, GameObjectType.CORN_SEEDS, Season.Summer, Season.Fall),
    SUNFLOWER_SEEDS("Sunflower Seeds", "Plant in summer or fall. Takes 8 days. Yields more seeds at harvest.",
            200, 300, 5, GameObjectType.SUNFLOWER_SEEDS, Season.Summer, Season.Fall),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", "Plant these in the summer. Takes 9 days to mature.",
            100, 150, 5, GameObjectType.RED_CABBAGE_SEEDS, Season.Summer),

    EGGPLANT_SEEDS("Eggplant Seeds", "Plant in fall. Takes 5 days. Keeps producing.",
            20, 30, 5, GameObjectType.EGGPLANT_SEEDS, Season.Fall),
    PUMPKIN_SEEDS("Pumpkin Seeds", "Plant in fall. Takes 13 days to mature.",
            100, 150, 5, GameObjectType.PUMPKIN_SEEDS, Season.Fall),
    BOK_CHOY_SEEDS("Bok Choy Seeds", "Plant in fall. Takes 4 days to mature.",
            50, 75, 5, GameObjectType.BOK_CHOY_SEEDS, Season.Fall),
    YAM_SEEDS("Yam Seeds", "Plant in fall. Takes 10 days to mature.",
            60, 90, 5, GameObjectType.YAM_SEEDS, Season.Fall),
    CRANBERRY_SEEDS("Cranberry Seeds", "Plant in fall. Takes 7 days. Keeps producing.",
            240, 360, 5, GameObjectType.CRANBERRY_SEEDS, Season.Fall),
    FAIRY_SEEDS("Fairy Seeds", "Plant in fall. Takes 12 days. Assorted colors.",
            200, 300, 5, GameObjectType.FAIRY_SEEDS, Season.Fall),
    AMARANTH_SEEDS("Amaranth Seeds", "Plant in fall. Takes 7 days. Harvest with scythe.",
            70, 105, 5, GameObjectType.AMARANTH_SEEDS, Season.Fall),
    GRAPE_STARTER("Grape Starter", "Plant in fall. Takes 10 days. Keeps producing. Grows on a trellis.",
            60, 90, 5, GameObjectType.GRAPE_STARTER, Season.Fall),
    ARTICHOKE_SEEDS("Artichoke Seeds", "Plant in fall. Takes 8 days to mature.",
            30, 45, 5, GameObjectType.ARTICHOKE_SEEDS, Season.Fall);

    private final String name;
    private final String description;
    private final int basePrice;
    private final int outOfSeasonPrice;
    private int dailyLimit;
    private final Set<Season> seasons;
    private final GameObjectType type;

    PierresGeneralStoreSeasonalStock(String name, String description, int basePrice, int outOfSeasonPrice,
                                     int dailyLimit, GameObjectType type, Season... seasons) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.outOfSeasonPrice = outOfSeasonPrice;
        this.dailyLimit = dailyLimit;
        this.seasons = Set.of(seasons);
        this.type = type;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getBasePrice() { return basePrice; }
    public int getOutOfSeasonPrice() { return outOfSeasonPrice; }
    public int getDailyLimit() { return dailyLimit; }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void decreaseDailyLimit() {
        this.dailyLimit--;
    }

    public void resetDailyLimit() {
        for(PierresGeneralStoreSeasonalStock gameObjectType : PierresGeneralStoreSeasonalStock.values()) {
            gameObjectType.setDailyLimit(5);
        }
    }

    public Set<Season> getSeasons() { return seasons; }
    public GameObjectType getType() { return type; }

    @Override
    public String toString() {
        return name;
    }
}

