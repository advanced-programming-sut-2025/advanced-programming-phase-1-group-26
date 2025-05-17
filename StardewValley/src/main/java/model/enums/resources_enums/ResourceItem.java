package model.enums.resources_enums;

import model.enums.GameObjectType;

public enum ResourceItem {
    WOOD(GameObjectType.WOOD, "Wood", "A sturdy, yet flexible plant material with a wide variety of uses.", 10, -1),
    STONE(GameObjectType.STONE, "Stone", "A common material with many uses in crafting and building.", 20, -1);

    private final GameObjectType type;
    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    ResourceItem(GameObjectType type, String name, String description, int price, int dailyLimit) {
        this.type = type;
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

    public GameObjectType getType()
    {
        return type;
    }
}
