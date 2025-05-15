package model.enums.shop_enums;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

public enum BlacksmithUpgradeTools {
    COPPER_TOOL("Copper Tool", "Copper Bar", 5, 2000, 1,
            GameObjectType.COPPER_TOOL),
    STEEL_TOOL("Steel Tool", "Iron Bar", 5, 5000, 1,
            GameObjectType.STEEL_TOOL),
    GOLD_TOOL("Gold Tool", "Gold Bar", 5, 10000, 1,
            GameObjectType.GOLD_TOOL),
    IRIDIUM_TOOL("Iridium Tool", "Iridium Bar", 5, 25000, 1,
            GameObjectType.IRIDIUM_TOOL),
    COPPER_TRASH_CAN("Copper Trash Can", "Copper Bar", 5, 1000, 1,
            GameObjectType.COPPER_TRASH_CAN),
    STEEL_TRASH_CAN("Steel Trash Can", "Iron Bar", 5, 2500, 1,
            GameObjectType.STEEL_TRASH_CAN),
    GOLD_TRASH_CAN("Gold Trash Can", "Gold Bar", 5, 5000, 1,
            GameObjectType.GOLD_TRASH_CAN),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", "Iridium Bar", 5, 12500, 1,
            GameObjectType.IRIDIUM_TRASH_CAN),
    ;

    private final String name;
    private final String ingredient;
    private final int ingredientAmount;
    private final int cost;
    private int dailyLimit;
    private final GameObjectType gameObjectType;

    BlacksmithUpgradeTools(String name, String ingredient, int ingredientAmount, int cost, int dailyLimit,
                           GameObjectType gameObjectType) {
        this.name = name;
        this.ingredient = ingredient;
        this.ingredientAmount = ingredientAmount;
        this.cost = cost;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
    }

    public String getName() { return name; }
    public String getIngredient() { return ingredient; }
    public int getIngredientAmount() { return ingredientAmount; }
    public int getCost() { return cost; }
    public int getDailyLimit() { return dailyLimit; }
    public GameObjectType getGameObjectType() { return gameObjectType; }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void resetDailyLimit() {
        for (BlacksmithUpgradeTools tool : BlacksmithUpgradeTools.values()) {
            tool.setDailyLimit(1);
        }
    }

    public void decreaseDailyLimit() {
        setDailyLimit(--dailyLimit);
    }
}
