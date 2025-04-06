package model.enums;

public enum WinterStock {
    POWDERMELON_SEEDS(
            "Powdermelon Seeds",
            "This special melon grows in the winter. Takes 7 days to grow.",
            20,
            10
    );

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    WinterStock(String name, String description, int price, int dailyLimit) {
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
}
