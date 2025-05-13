package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum MarniesRanchShopInventory {
    HAY("Hay", "Dried grass used as animal food.", 50, -1,
            GameObjectType.HAY),
    MILK_PAIL("Milk Pail", "Gather milk from your animals.", 1000, 1,
            GameObjectType.MILK_PAIL),
    SHEARS("Shears", "Use this to collect wool from sheep.", 1000, 1,
            GameObjectType.SHEARS),
    ;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private GameObjectType gameObjectType;

    MarniesRanchShopInventory(String name, String description, int price, int dailyLimit, GameObjectType gameObjectType) {
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
