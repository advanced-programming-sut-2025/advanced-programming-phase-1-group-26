package model.enums;

public enum SummerStock {
    MELON_SEEDS("Melon Seeds", "Plant these in the summer. Takes 12 days to mature.", 80, 120, 5),
    TOMATO_SEEDS("Tomato Seeds", "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest.", 50, 75, 5),
    BLUEBERRY_SEEDS("Blueberry Seeds", "Plant these in the summer. Takes 13 days to mature, and continues to produce after first harvest.", 80, 120, 5),
    PEPPER_SEEDS("Pepper Seeds", "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest.", 40, 60, 5),
    WHEAT_SEEDS("Wheat Seeds", "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.", 10, 15, 5),
    RADISH_SEEDS("Radish Seeds", "Plant these in the summer. Takes 6 days to mature.", 40, 60, 5),
    POPPY_SEEDS("Poppy Seeds", "Plant in summer. Produces a bright red flower in 7 days.", 100, 150, 5),
    SPANGLE_SEEDS("Spangle Seeds", "Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors.", 50, 75, 5),
    HOPS_STARTER("Hops Starter", "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis.", 60, 90, 5),
    CORN_SEEDS("Corn Seeds", "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.", 150, 225, 5),
    SUNFLOWER_SEEDS("Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.", 200, 300, 5),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", "Plant these in the summer. Takes 9 days to mature.", 100, 150, 5);

    public final String name;
    public final String description;
    public final int normalPrice;
    public final int salePrice;
    public final int dailyLimit;

    SummerStock(String name, String description, int normalPrice, int salePrice, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.dailyLimit = dailyLimit;
    }
}
