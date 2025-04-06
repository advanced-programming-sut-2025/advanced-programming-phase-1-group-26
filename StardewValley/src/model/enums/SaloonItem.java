package model.enums;

public enum SaloonItem {
    BEER("Beer", "Drink in moderation.", 400, -1),
    SALAD("Salad", "A healthy garden salad.", 220, -1),
    BREAD("Bread", "A crusty baguette.", 120, -1),
    SPAGHETTI("Spaghetti", "An old favorite.", 240, -1),
    PIZZA("Pizza", "It's popular for all the right reasons.", 600, -1),
    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.", 300, -1);

    private final String name;
    private final String description;
    private final int price;
    private final int dailyLimit;

    SaloonItem(String name, String description, int price, int dailyLimit) {
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

