package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum JojaMartPermanentStock {
    JOJA_COLA("Joja Cola", "The flagship product of Joja corporation.", 75, -1,
            GameObjectType.JOJA_COLA, -1),
    ANCIENT_SEED("Ancient Seed", "Could these still grow?", 500, 1,
            GameObjectType.ANCIENT_SEEDS, -1),
    GRASS_STARTER("Grass Starter", "Place this on your farm to start a new patch of grass.", 125, -1,
            GameObjectType.GRAPE_STARTER, -1),
    SUGAR("Sugar", "Adds sweetness to pastries and candies. Too much can be unhealthy.", 125, -1,
            GameObjectType.SUGAR, -1),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.", 125, -1,
            GameObjectType.WHEAT_FLOWER, -1),
    RICE("Rice", "A basic grain often served under vegetables.", 250, -1,
            GameObjectType.RICE, -1)
    ;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private final GameObjectType gameObjectType;
    private int limit;

    JojaMartPermanentStock(String name, String description, int price, int dailyLimit, GameObjectType gameObjectType,
                           int limit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
        this.limit = limit;
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
        this.limit -= 1;
    }
    public void resetLimit() {
        for(JojaMartPermanentStock s : JojaMartPermanentStock.values()) {
            s.setLimit(s.getDailyLimit());
        }
    }
}
