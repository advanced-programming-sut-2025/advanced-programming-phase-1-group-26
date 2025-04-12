package model.enums.animal_enums;

public enum AnimalShopItem {
    HAY("Hay", "Dried grass used as animal food.", 50, -1),
    MILK_PAIL("Milk Pail", "Gather milk from your animals.", 1000, 1),
    SHEARS("Shears", "Use this to collect wool from sheep", 1000, 1);

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    AnimalShopItem(String name, String description, int price, int dailyLimit) {
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
