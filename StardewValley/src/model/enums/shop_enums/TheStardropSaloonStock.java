package model.enums.shop_enums;

public enum TheStardropSaloonStock {
    BEER("Beer", "Drink in moderation.", 400, -1),
    SALAD("Salad", "A healthy garden salad.", 220, -1),
    BREAD("Bread", "A crusty baguette.", 120, -1),
    SPAGHETTI("Spaghetti", "An old favorite.", 240, -1),
    PIZZA("Pizza", "It's popular for all the right reasons.", 600, -1),
    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.", 300, -1),
    HASHBROWNS_RECIPE("Hashbrowns Recipe", "A recipe to make Hashbrowns.", 50, 1),
    OMELET_RECIPE("Omelet Recipe", "A recipe to make Omelet.", 100, 1),
    PANCAKES_RECIPE("Pancakes Recipe", "A recipe to make Pancakes.", 100, 1),
    BREAD_RECIPE("Bread Recipe", "A recipe to make Bread.", 100, 1),
    TORTILLA_RECIPE("Tortilla Recipe", "A recipe to make Tortilla.", 100, 1),
    PIZZA_RECIPE("Pizza Recip`e", "A recipe to make Pizza.", 150, 1),
    MAKI_ROLL_RECIPE("Maki Roll Recipe", "A recipe to make Maki Roll.", 300, 1),
    TRIPLE_SHOT_ESPRESSO_RECIPE("Triple Shot Espresso Recipe", "A recipe to make Triple Shot Espresso.", 5000, 1),
    COOKIE_RECIPE("Cookie Recipe", "A recipe to make Cookie.", 300, 1);

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    TheStardropSaloonStock(String name, String description, int price, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
}
