package model.enums.animal_enums;

public enum FishingItem {
    FISH_SMOKER_RECIPE("Fish Smoker (Recipe)", "A recipe to make Fish Smoker", 10000, -1, 1),
    TROUT_SOUP("Trout Soup", "Pretty salty.", 250, -1, 1),
    BAMBOO_POLE("Bamboo Pole", "Use in the water to catch fish.", 500, -1, 1),
    TRAINING_ROD("Training Rod", "It's a lot easier to use than other rods, but can only catch basic fish.", 25, -1, 1),
    FIBERGLASS_ROD("Fiberglass Rod", "Use in the water to catch fish.", 1800, 2, 1),
    IRIDIUM_ROD("Iridium Rod", "Use in the water to catch fish.", 7500, 4, 1);

    private final String name;
    private final String description;
    private final int price;
    private final int fishingSkillRequired;
    private final int dailyLimit;

    FishingItem(String name, String description, int price, int fishingSkillRequired, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.fishingSkillRequired = fishingSkillRequired;
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

    public int getFishingSkillRequired() {
        return fishingSkillRequired;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public boolean requiresSkill() {
        return fishingSkillRequired >= 0;
    }
}
