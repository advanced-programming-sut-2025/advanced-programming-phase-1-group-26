package model.enums.shop_enums;

public enum JojaMartPermanentStock {
    JOJA_COLA("Joja Cola", "The flagship product of Joja corporation.", 75, -1),
    ANCIENT_SEED("Ancient Seed", "Could these still grow?", 500, 1),
    GRASS_STARTER("Grass Starter", "Place this on your farm to start a new patch of grass.", 125, -1),
    SUGAR("Sugar", "Adds sweetness to pastries and candies. Too much can be unhealthy.", 125, -1),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.", 125, -1),
    RICE("Rice", "A basic grain often served under vegetables.", 250, -1);

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    JojaMartPermanentStock(String name, String description, int price, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
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
}
