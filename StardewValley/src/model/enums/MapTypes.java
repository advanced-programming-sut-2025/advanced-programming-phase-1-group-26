package model.enums;

import java.util.ArrayList;

public enum MapTypes
{
    STANDARD("standard", "StardewValley/resources/map/standard.json"),
    RIVERLAND("riverland", "StardewValley/resources/map/river.json"),
    HILL_TOP("hilltop", "StardewValley/resources/map/hilltop.json"),
    BEACH("four_corners", "StardewValley/resources/map/beach.json"),

    CABIN("cabin", "StardewValley/resources/map/cabin.json"),
    GREEN_HOUSE("greenhouse", "StardewValley/resources/map/greenhouse.json"),
    CITY("city", "StardewValley/resources/map/city.json"),
    SHOP("shop", "StardewValley/resources/map/shop.json"),
    ;

    private final String name;
    private final String mapPath;

    MapTypes(String name, String mapPath)
    {
        this.name = name;
        this.mapPath = mapPath;
    }

    public String getName()
    {
        return name;
    }

    public String getMapPath()
    {
        return mapPath;
    }

    public static ArrayList<MapTypes> farmTypes()
    {
        ArrayList<MapTypes> farmTypes = new ArrayList<MapTypes>();
        farmTypes.add(STANDARD);
        farmTypes.add(RIVERLAND);
        farmTypes.add(HILL_TOP);
        farmTypes.add(BEACH);
        return farmTypes;
    }

    public MapTypes getFarmType(String name)
    {
        if (name.equalsIgnoreCase(STANDARD.getName()))
        {
            return STANDARD;
        }

        if (name.equalsIgnoreCase(RIVERLAND.getName()))
        {
            return RIVERLAND;
        }

        if (name.equalsIgnoreCase(HILL_TOP.getName()))
        {
            return HILL_TOP;
        }

        if (name.equalsIgnoreCase(BEACH.getName()))
        {
            return BEACH;
        }

        return null;
    }
}
