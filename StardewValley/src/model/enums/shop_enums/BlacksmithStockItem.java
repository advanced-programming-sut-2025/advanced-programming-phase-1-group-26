package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum BlacksmithStockItem {
    COPPER_ORE("Copper Ore", "A common ore that can be smelted into bars.", 75, -1,
            GameObjectType.COPPER_ORE),
    IRON_ORE("Iron Ore", "A fairly common ore that can be smelted into bars.", 150, -1,
            GameObjectType.IRON_ORE),
    COAL("Coal", "A combustible rock that is useful for crafting and smelting.", 150, -1,
            GameObjectType.COAL),
    GOLD_ORE("Gold Ore", "A precious ore that can be smelted into bars.", 400, -1,
            GameObjectType.GOLD_ORE),;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private final GameObjectType gameObjectType;

    BlacksmithStockItem(String name, String description, int price, int dailyLimit, GameObjectType gameObjectType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() {
        return dailyLimit;
    }
}
