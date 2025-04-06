package model.enums;

public enum GameObjectType {
    COPPER_BAR("Copper Bar"),
    IRON_BAR("Iron Bar"),
    GOLD_BAR("Gold Bar"),
    IRIDIUM_BAR("Iridium Bar"),
    COPPER_ORE("Copper Ore"),
    IRON_ORE("Iron Ore"),
    GOLD_ORE("Gold Ore"),
    COAL("Coal"),
    FISH("Fish"),
    WOOD("Wood"),
    FLOWER("Flower"),
    HEART("Heart"),
    STONE("Stone")

    ;

    private final String name;

    GameObjectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
