package model.enums;

public enum MineralType
{
    QUARTZ(GameObjectType.QUARTZ_MINERAL, "A clear crystal commonly found in caves and mines.", 25),
    EARTH_CRYSTAL(GameObjectType.EARTH_CRYSTAL_MINERAL, "A resinous substance found near the surface.", 50),
    FROZEN_TEAR(GameObjectType.FROZEN_TEAR_MINERAL, "A crystal fabled to be the frozen tears of a yeti.", 75),
    FIRE_QUARTZ(GameObjectType.FIRE_QUARTZ_MINERAL, "A glowing red crystal commonly found near hot lava.", 100),
    EMERALD(GameObjectType.EMERALD_MINERAL, "A precious stone with a brilliant green color.", 250),
    AQUAMARINE(GameObjectType.AQUAMARINE_MINERAL, "A shimmery blue-green gem.", 180),
    RUBY(GameObjectType.RUBY_MINERAL, "A precious stone that is sought after for its rich color and beautiful luster.", 250),
    AMETHYST(GameObjectType.AMETHYST_MINERAL, "A purple variant of quartz.", 100),
    TOPAZ(GameObjectType.TOPAZ_MINERAL, "Fairly common but still prized for its beauty.", 80),
    JADE(GameObjectType.JADE_MINERAL, "A pale green ornamental stone.", 200),
    DIAMOND(GameObjectType.DIAMOND_MINERAL, "A rare and valuable gem.", 750),
    PRISMATIC_SHARD(GameObjectType.PRISMATIC_SHARD_MINERAL, "A very rare and powerful substance with unknown origins.", 2000),
    COPPER(GameObjectType.COPPER_MINERAL, "A common ore that can be smelted into bars.", 5),
    IRON(GameObjectType.IRON_MINERAL, "A fairly common ore that can be smelted into bars.", 10),
    GOLD(GameObjectType.GOLD_MINERAL, "A precious ore that can be smelted into bars.", 25),
    IRIDIUM(GameObjectType.IRIDIUM_MINERAL, "An exotic ore with many curious properties. Can be smelted into bars.", 100),
    COAL(GameObjectType.COAL_MINERAL, "A combustible rock that is useful for crafting and smelting.", 15);

    private final GameObjectType type;
    private final String description;
    private final int sellPrice;

    MineralType(GameObjectType type, String description, int sellPrice)
    {
        this.type = type;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public String getDescription()
    {
        return description;
    }

    public int getSellPrice()
    {
        return sellPrice;
    }
}
