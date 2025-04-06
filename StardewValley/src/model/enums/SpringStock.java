package model.enums;

public enum SpringStock {
    PARSNIP_SEEDS("Parsnip Seeds", "Plant in spring. 4 days to mature.", 20, 30, 5),
    BEAN_STARTER("Bean Starter", "Plant in spring. 10 days to mature, keeps producing.", 60, 90, 5),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant in spring. 12 days to mature.", 80, 120, 5),
    POTATO_SEEDS("Potato Seeds", "Plant in spring. 6 days to mature.", 50, 75, 5),
    TULIP_BULB("Tulip Bulb", "Produces a colorful flower in 6 days.", 20, 30, 5),
    KALE_SEEDS("Kale Seeds", "Plant in spring. 6 days to mature.", 70, 105, 5),
    JAZZ_SEEDS("Jazz Seeds", "Produces a blue puffball flower in 7 days.", 30, 45, 5),
    GARLIC_SEEDS("Garlic Seeds", "Plant in spring. 4 days to mature.", 40, 60, 5),
    RICE_SHOOT("Rice Shoot", "Takes 8 days. Grows faster near water.", 40, 60, 5)
    ;

    public final String name;
    public final String description;
    public final int normalPrice;
    public final int salePrice;
    public final int dailyLimit;

    SpringStock(String name, String description, int normalPrice, int salePrice, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.dailyLimit = dailyLimit;
    }
}
