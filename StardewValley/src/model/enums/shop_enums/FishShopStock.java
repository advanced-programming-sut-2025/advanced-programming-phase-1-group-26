package model.enums.shop_enums;

import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.tool_enums.ToolType;

public enum FishShopStock {
    FISH_SMOKER_RECIPE("Fish Smoker (Recipe)", "A recipe to make Fish Smoker",
            10000, -1, 1, GameObjectType.FISH_SMOKER_RECIPE),
    TROUT_SOUP("Trout Soup", "Pretty salty.",
            250, -1, 1, GameObjectType.TROUT_SOUP),
    BAMBOO_POLE("Bamboo Pole", "Use in the water to catch fish.",
            500, -1, 1, GameObjectType.BAMBOO_POLE),
    TRAINING_ROD("Training Rod", "Easier to use but can only catch basic fish.",
            25, -1, 1, GameObjectType.TRAINING_ROD),
    FIBERGLASS_ROD("Fiberglass Rod", "Use in the water to catch fish.",
            1800, 2, 1, GameObjectType.FIBERGLASS_ROD),
    IRIDIUM_ROD("Iridium Rod", "Use in the water to catch fish.",
            7500, 4, 1, GameObjectType.IRIDIUM_ROD),;

    private final String name;
    private final String description;
    private final int price;
    private final int fishingSkillRequired;
    private int dailyLimit;
    private final GameObjectType gameObjectType;

    FishShopStock(String name, String description, int price, int fishingSkillRequired,
                  int dailyLimit, GameObjectType gameObjectType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.fishingSkillRequired = fishingSkillRequired;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getFishingSkillRequired() { return fishingSkillRequired; } // -1 means N/A
    public int getDailyLimit() { return dailyLimit; }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public GameObjectType getGameObjectType() { return gameObjectType; }

    @Override
    public String toString() {
        return name;
    }

    public void decreaseDailyLimit() {
        this.dailyLimit--;
    }

    public void resetDailyLimit() {
        for(FishShopStock gameObjectType : FishShopStock.values()) {
            gameObjectType.setDailyLimit(1);
        }
    }
}
