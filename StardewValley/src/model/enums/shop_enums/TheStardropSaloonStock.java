package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum TheStardropSaloonStock {
    BEER("Beer", "Drink in moderation.",
            400, -1, GameObjectType.BEER),
    SALAD("Salad", "A healthy garden salad.",
            220, -1, GameObjectType.SALAD),
    BREAD("Bread", "A crusty baguette.",
            120, -1, GameObjectType.BREAD),
    SPAGHETTI("Spaghetti", "An old favorite.",
            240, -1, GameObjectType.SPAGHETTI),
    PIZZA("Pizza", "It's popular for all the right reasons.",
            600, -1, GameObjectType.PIZZA),
    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.",
            300, -1, GameObjectType.COFFEE),
    HASHBROWNS_RECIPE("Hashbrowns Recipe", "A recipe to make Hashbrowns.",
            50, 1, GameObjectType.HASHBROWNS_RECIPE),
    OMELET_RECIPE("Omelet Recipe", "A recipe to make Omelet.",
            100, 1, GameObjectType.OMELET_RECIPE),
    PANCAKES_RECIPE("Pancakes Recipe", "A recipe to make Pancakes.",
            100, 1, GameObjectType.PANCAKES_RECIPE),
    BREAD_RECIPE("Bread Recipe", "A recipe to make Bread.",
            100, 1, GameObjectType.BREAD_RECIPE),
    TORTILLA_RECIPE("Tortilla Recipe", "A recipe to make Tortilla.",
            100, 1, GameObjectType.TORTILLA_RECIPE),
    PIZZA_RECIPE("Pizza Recip`e", "A recipe to make Pizza.",
            150, 1, GameObjectType.PIZZA_RECIPE),
    MAKI_ROLL_RECIPE("Maki Roll Recipe", "A recipe to make Maki Roll.",
            300, 1, GameObjectType.MAKI_ROLL_RECIPE),
    TRIPLE_SHOT_ESPRESSO_RECIPE("Triple Shot Espresso Recipe", "A recipe to make Triple Shot Espresso.",
            5000, 1, GameObjectType.TRIPLE_SHOT_ESPRESSO_RECIPE),
    COOKIE_RECIPE("Cookie Recipe", "A recipe to make Cookie.",
            300, 1, GameObjectType.COOKIE_RECIPE),

    ;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private final GameObjectType gameObjectType;

    TheStardropSaloonStock(String name, String description, int price, int dailyLimit, GameObjectType gameObjectType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
    public GameObjectType getGameObjectType() { return gameObjectType; }
}
