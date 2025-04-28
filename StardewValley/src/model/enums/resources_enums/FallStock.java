package model.enums.resources_enums;

public enum FallStock {
    EGGPLANT_SEEDS("Eggplant Seeds", "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest.", 20, 30, 5),
    CORN_SEEDS("Corn Seeds", "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.", 150, 225, 5),
    PUMPKIN_SEEDS("Pumpkin Seeds", "Plant these in the fall. Takes 13 days to mature.", 100, 150, 5),
    BOK_CHOY_SEEDS("Bok Choy Seeds", "Plant these in the fall. Takes 4 days to mature.", 50, 75, 5),
    YAM_SEEDS("Yam Seeds", "Plant these in the fall. Takes 10 days to mature.", 60, 90, 5),
    CRANBERRY_SEEDS("Cranberry Seeds", "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest.", 240, 360, 5),
    SUNFLOWER_SEEDS("Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.", 200, 300, 5),
    FAIRY_SEEDS("Fairy Seeds", "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted colors.", 200, 300, 5),
    AMARANTH_SEEDS("Amaranth Seeds", "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe.", 70, 105, 5),
    GRAPE_STARTER("Grape Starter", "Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis.", 60, 90, 5),
    WHEAT_SEEDS("Wheat Seeds", "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.", 10, 15, 5),
    ARTICHOKE_SEEDS("Artichoke Seeds", "Plant these in the fall. Takes 8 days to mature.", 30, 45, 5);

    public final String name;
    public final String description;
    public final int normalPrice;
    public final int salePrice;
    public final int dailyLimit;

    FallStock(String name, String description, int normalPrice, int salePrice, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.dailyLimit = dailyLimit;
    }
}
