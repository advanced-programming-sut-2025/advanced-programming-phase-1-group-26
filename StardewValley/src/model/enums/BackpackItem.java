package model.enums;

public enum BackpackItem {
    LARGE_PACK("Large Pack", "Unlocks the 2nd row of inventory (12 more slots, total 24).", 2000, "At the start of the game", 1),
    DELUXE_PACK("Deluxe Pack", "Unlocks the 3rd row of inventory (infinite slots).", 10000, "After purchasing the Large Pack", 1);

    private final String name;
    private final String description;
    private final int price;
    private final String availableCondition;
    private final int dailyLimit;

    BackpackItem(String name, String description, int price, String availableCondition, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableCondition = availableCondition;
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

    public String getAvailableCondition() {
        return availableCondition;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
