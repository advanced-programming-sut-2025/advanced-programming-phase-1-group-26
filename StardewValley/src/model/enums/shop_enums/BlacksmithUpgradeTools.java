package model.enums.shop_enums;

public enum BlacksmithUpgradeTools {
    COPPER_TOOL("Copper Tool", "Copper Bar", 5, 2000, 1),
    STEEL_TOOL("Steel Tool", "Iron Bar", 5, 5000, 1),
    GOLD_TOOL("Gold Tool", "Gold Bar", 5, 10000, 1),
    IRIDIUM_TOOL("Iridium Tool", "Iridium Bar", 5, 25000, 1),
    COPPER_TRASH_CAN("Copper Trash Can", "Copper Bar", 5, 1000, 1),
    STEEL_TRASH_CAN("Steel Trash Can", "Iron Bar", 5, 2500, 1),
    GOLD_TRASH_CAN("Gold Trash Can", "Gold Bar", 5, 5000, 1),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", "Iridium Bar", 5, 12500, 1);

    private final String name;
    private final String ingredient;
    private final int ingredientAmount;
    private final int cost;
    private final int dailyLimit;

    BlacksmithUpgradeTools(String name, String ingredient, int ingredientAmount, int cost, int dailyLimit) {
        this.name = name;
        this.ingredient = ingredient;
        this.ingredientAmount = ingredientAmount;
        this.cost = cost;
        this.dailyLimit = dailyLimit;
    }

    public String getName() { return name; }
    public String getIngredient() { return ingredient; }
    public int getIngredientAmount() { return ingredientAmount; }
    public int getCost() { return cost; }
    public int getDailyLimit() { return dailyLimit; }
}
