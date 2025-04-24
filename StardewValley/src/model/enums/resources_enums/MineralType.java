package model.enums.resources_enums;

public enum MineralType
{
    QUARTZ("A clear crystal commonly found in caves and mines.", 25),
    EARTH_CRYSTAL("A resinous substance found near the surface.", 50),
    FROZEN_TEAR("A crystal fabled to be the frozen tears of a yeti.", 75),
    FIRE_QUARTZ("A glowing red crystal commonly found near hot lava.", 100),
    EMERALD("A precious stone with a brilliant green color.", 250),
    AQUAMARINE("A shimmery blue-green gem.", 180),
    RUBY("A precious stone that is sought after for its rich color and beautiful luster.", 250),
    AMETHYST("A purple variant of quartz.", 100),
    TOPAZ("Fairly common but still prized for its beauty.", 80),
    JADE("A pale green ornamental stone.", 200),
    DIAMOND("A rare and valuable gem.", 750),
    PRISMATIC_SHARD("A very rare and powerful substance with unknown origins.", 2000),
    COPPER("A common ore that can be smelted into bars.", 5),
    IRON("A fairly common ore that can be smelted into bars.", 10),
    GOLD("A precious ore that can be smelted into bars.", 25),
    IRIDIUM("An exotic ore with many curious properties. Can be smelted into bars.", 100),
    COAL("A combustible rock that is useful for crafting and smelting.", 15);

    private final String description;
    private final int sellPrice;

    MineralType(String description, int sellPrice)
    {
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
