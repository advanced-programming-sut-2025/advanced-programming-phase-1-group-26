package model.enums;

public enum BlacksmithUpgrade {
    COPPER_TOOL("Copper Tool", GameObjectType.COPPER_BAR, 5, 2000, 1),
    STEEL_TOOL("Steel Tool", GameObjectType.IRON_BAR, 5, 5000, 1),
    GOLD_TOOL("Gold Tool", GameObjectType.GOLD_BAR, 5, 10000, 1),
    IRIDIUM_TOOL("Iridium Tool", GameObjectType.IRIDIUM_BAR, 5, 25000, 1),

    COPPER_TRASH_CAN("Copper Trash Can", GameObjectType.COPPER_BAR, 5, 1000, 1),
    STEEL_TRASH_CAN("Steel Trash Can", GameObjectType.IRON_BAR, 5, 2500, 1),
    GOLD_TRASH_CAN("Gold Trash Can", GameObjectType.GOLD_BAR, 5, 5000, 1),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", GameObjectType.IRIDIUM_BAR, 5, 12500, 1);

    private final String name;
    private final GameObjectType ingredient;
    private final int ingredientCount;
    private final int cost;
    private final int dailyLimit;

    BlacksmithUpgrade(String name, GameObjectType ingredient, int ingredientCount, int cost, int dailyLimit) {
        this.name = name;
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
        this.cost = cost;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public GameObjectType getIngredient() {
        return ingredient;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    public int getCost() {
        return cost;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}

