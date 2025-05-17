package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum MarniesRanchShopInventory {
    HAY("Hay", "Dried grass used as animal food.", 50, -1,
            GameObjectType.HAY, -1),
    MILK_PAIL("Milk Pail", "Gather milk from your animals.", 1000, 1,
            GameObjectType.MILK_PAIL, 1),
    SHEARS("Shears", "Use this to collect wool from sheep.", 1000, 1,
            GameObjectType.SHEARS, 1),
    ;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private GameObjectType gameObjectType;
    private int limit;

    MarniesRanchShopInventory(String name, String description, int price, int dailyLimit,
                              GameObjectType gameObjectType, int limit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
        this.limit = limit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
    public GameObjectType getGameObjectType() { return gameObjectType; }

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
        for(MarniesRanchShopInventory m : MarniesRanchShopInventory.values()) {
            m.setLimit(m.getDailyLimit());
        }
    }
}
