package model.enums.resources_enums;

public enum YearRoundStock {
    RICE("Rice", "A basic grain often served under vegetables.", 200, -1),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.", 100, -1),
    BOUQUET("Bouquet", "A gift that shows your romantic interest.", 1000, 2),
    WEDDING_RING("Wedding Ring", "Used to ask for marriage.", 10000, 2),
    DEHYDRATOR_RECIPE("Dehydrator (Recipe)", "A recipe to make Dehydrator.", 10000, 1),
    GRASS_STARTER_RECIPE("Grass Starter (Recipe)", "A recipe to make Grass Starter.", 1000, 1),
    SUGAR("Sugar", "Adds sweetness to pastries and candies.", 100, -1),
    OIL("Oil", "All-purpose cooking oil.", 200, -1),
    VINEGAR("Vinegar", "Aged fermented liquid.", 200, -1),
    BASIC_FERTILIZER("Basic Fertilizer", "Improves soil quality a little.", 100, -1),
    QUALITY_FERTILIZER("Quality Fertilizer", "Improves soil quality.", 150, -1),
    GRASS_STARTER("Grass Starter", "Starts a patch of grass.", 100, -1),
    SPEED_GRO("Speed-Gro", "Increases growth rate by 10%.", 100, -1),
    DELUXE_SPEED_GRO("Deluxe Speed-Gro", "Increases growth rate by 25%.", 150, -1),
    APPLE_SAPLING("Apple Sapling", "Produces Apples in fall.", 4000, -1),
    APRICOT_SAPLING("Apricot Sapling", "Produces Apricots in spring.", 2000, -1),
    CHERRY_SAPLING("Cherry Sapling", "Produces Cherries in spring.", 3400, -1),
    ORANGE_SAPLING("Orange Sapling", "Produces Oranges in summer.", 4000, -1),
    PEACH_SAPLING("Peach Sapling", "Produces Peaches in summer.", 6000, -1),
    POMEGRANATE_SAPLING("Pomegranate Sapling", "Produces Pomegranates in fall.", 6000, -1),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", "Chance of staying watered overnight.", 100, -1),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", "Good chance of staying watered overnight.", 150, -1)
    ;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    YearRoundStock(String name, String description, int price, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
}

