package model.enums;

public enum TileTexture
{
    LAND("land"),
    GRASS("grass"),
    LAKE("lake"),
    CABIN("cabin"),
    GREEN_HOUSE("greenhouse"),
    QUARRY("quarry"),

    CABIN_INTERIOR_FLOOR("cabin-interior-floor"),
    CABIN_WALL("cabin-wall"),
    GREEN_HOUSE_FLOOR("greenhouse-floor"),
    GREEN_HOUSE_WALL("greenhouse-wall"),
    GREEN_HOUSE_WOOD("greenhouse-wood"),
    BED_TILE("bed-tile"),
    DECOR_TILE("decor-tile"),
    VILLAGE_GRASS("village-grass"),
    ROAD("road"),
    FENCE("fence"),
    BUILDING("building"),
    SHOP_DOOR("shop-door"),
    CITY_BOARD("city-board"),
    GARDEN("garden"),
    FLOWER("flower"),
    TREE("tree"),
    EMPTY("empty"), // for visual aspects only
    BOOK("book"),
    LAMP("lamp"),
    TABLE("table"),
    COMPUTER("computer"),

    SHOP_BLACKSMITH("shop-blacksmith"),
    NPC_BLACKSMITH("npc-blacksmith"),

    SHOP_JOJAMART("shop-jojamart"),
    NPC_JOJAMART("npc-jojamart"),

    SHOP_PIERRE("shop-pierre"),
    NPC_PIERRE("npc-pierre"),

    SHOP_CARPENTER("shop-carpenter"),
    NPC_CARPENTER("npc-carpenter"),

    SHOP_FISH("shop-fish"),
    NPC_FISH("npc-fish"),

    SHOP_MARNIE("shop-marnie"),
    NPC_MARNIE("npc-marnie"),

    SHOP_SALOON("shop-saloon"),
    NPC_SALOON("npc-saloon"),

    WALL("wall"),
    FLOOR("floor"),

    ANIMAL_BUILDING("animal building"),
    ;

    private final String name;

    TileTexture(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public static TileTexture mapTypeNameToTexture(String typeName)
    {
        for (TileTexture t : TileTexture.values())
        {
            if (t.name.equalsIgnoreCase(typeName))
            {
                return t;
            }
        }
        return null;
    }
}
