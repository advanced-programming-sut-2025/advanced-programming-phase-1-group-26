package model.enums;

public enum TileTexture
{
    LAND("land"),
    GRASS("grass"),
    LAKE("lake"),
    CABIN("cabin"),
    GREEN_HOUSE("green house"),
    QUARRY("quarry");

    private final String name;

    TileTexture(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
