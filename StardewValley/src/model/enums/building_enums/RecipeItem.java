package model.enums.building_enums;

public enum RecipeItem {
    HASHBROWNS("Hashbrowns Recipe", "A recipe to make Hashbrowns.", 50, 1),
    OMELET("Omelet Recipe", "A recipe to make Omelet.", 100, 1),
    PANCAKES("Pancakes Recipe", "A recipe to make Pancakes.", 100, 1),
    BREAD("Bread Recipe", "A recipe to make Bread.", 100, 1),
    TORTILLA("Tortilla Recipe", "A recipe to make Tortilla.", 100, 1),
    PIZZA("Pizza Recipe", "A recipe to make Pizza.", 150, 1),
    MAKI_ROLL("Maki Roll Recipe", "A recipe to make Maki Roll.", 300, 1),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso Recipe", "A recipe to make Triple Shot Espresso.", 5000, 1),
    COOKIE("Cookie Recipe", "A recipe to make Cookie.", 300, 1);

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    RecipeItem(String name, String description, int price, int dailyLimit) {
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
