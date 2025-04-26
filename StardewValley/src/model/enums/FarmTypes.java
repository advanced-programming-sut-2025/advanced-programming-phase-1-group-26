package model.enums;


public enum FarmTypes {
    STANDARD("standard", "resources/map/standard.json"),
    RIVERLAND("riverland", "resources/map/river.json"),
    HILL_TOP("hilltop", "resources/map/hilltop.json"),
    BEACH("four_corners", "resources/map/beach.json");

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
