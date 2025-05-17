package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum CarpentersShopStock {
    WOOD("Wood", "A sturdy, yet flexible plant material with a wide variety of uses.",
            10, -1, GameObjectType.WOOD),
    STONE("Stone", "A common material with many uses in crafting and building.",
            20, -1, GameObjectType.STONE)
    ;
    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private final GameObjectType gameObjectType;

    CarpentersShopStock(String name, String description, int price, int dailyLimit, GameObjectType gameObjectType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
    public GameObjectType getGameObjectType() { return gameObjectType; }
}
