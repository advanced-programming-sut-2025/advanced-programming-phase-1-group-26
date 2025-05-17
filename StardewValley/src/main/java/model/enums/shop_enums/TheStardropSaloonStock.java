package model.enums.shop_enums;

import model.enums.GameObjectType;

public enum TheStardropSaloonStock {
    BEER("Beer", "Drink in moderation.",
            400, -1, GameObjectType.BEER, -1),
    SALAD("Salad", "A healthy garden salad.",
            220, -1, GameObjectType.SALAD, -1),
    BREAD("Bread", "A crusty baguette.",
            120, -1, GameObjectType.BREAD, -1),
    SPAGHETTI("Spaghetti", "An old favorite.",
            240, -1, GameObjectType.SPAGHETTI, -1),
    PIZZA("Pizza", "It's popular for all the right reasons.",
            600, -1, GameObjectType.PIZZA, -1),
    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.",
            300, -1, GameObjectType.COFFEE, -1),
    HASHBROWNS_RECIPE("Hashbrowns Recipe", "A recipe to make Hashbrowns.",
            50, 1, GameObjectType.HASHBROWNS_RECIPE, -1),
    OMELET_RECIPE("Omelet Recipe", "A recipe to make Omelet.",
            100, 1, GameObjectType.OMELET_RECIPE, 1),
    PANCAKES_RECIPE("Pancakes Recipe", "A recipe to make Pancakes.",
            100, 1, GameObjectType.PANCAKES_RECIPE, 1),
    BREAD_RECIPE("Bread Recipe", "A recipe to make Bread.",
            100, 1, GameObjectType.BREAD_RECIPE, 1),
    TORTILLA_RECIPE("Tortilla Recipe", "A recipe to make Tortilla.",
            100, 1, GameObjectType.TORTILLA_RECIPE, 1),
    PIZZA_RECIPE("Pizza Recip`e", "A recipe to make Pizza.",
            150, 1, GameObjectType.PIZZA_RECIPE, 1),
    MAKI_ROLL_RECIPE("Maki Roll Recipe", "A recipe to make Maki Roll.",
            300, 1, GameObjectType.MAKI_ROLL_RECIPE, 1),
    TRIPLE_SHOT_ESPRESSO_RECIPE("Triple Shot Espresso Recipe", "A recipe to make Triple Shot Espresso.",
            5000, 1, GameObjectType.TRIPLE_SHOT_ESPRESSO_RECIPE, 1),
    COOKIE_RECIPE("Cookie Recipe", "A recipe to make Cookie.",
            300, 1, GameObjectType.COOKIE_RECIPE, 1),

    ;

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;
    private final GameObjectType gameObjectType;
    private int limit;

    TheStardropSaloonStock(String name, String description, int price, int dailyLimit,
                           GameObjectType gameObjectType, int limit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.gameObjectType = gameObjectType;
        this.limit = limit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getDailyLimit() { return dailyLimit; }
    public GameObjectType getGameObjectType() { return gameObjectType; }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void decreaseLimit() {
        this.limit -= 1;
    }
    public void resetLimit() {
        for(TheStardropSaloonStock s : TheStardropSaloonStock.values()) {
            s.setLimit(s.getDailyLimit());
        }
    }
}
