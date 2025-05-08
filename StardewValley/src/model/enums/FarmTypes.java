package model.enums;


public enum FarmTypes {
    STANDARD("standard", "StardewValley/resources/map/standard.json"),
    RIVERLAND("riverland", "StardewValley/resources/map/river.json"),
    HILL_TOP("hilltop", "StardewValley/resources/map/hilltop.json"),
    BEACH("four_corners", "StardewValley/resources/map/beach.json");

    private final String name;
    private final String mapPath;

    FarmTypes(String name, String mapPath)
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
}
