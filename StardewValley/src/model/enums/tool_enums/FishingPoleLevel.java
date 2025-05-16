package model.enums.tool_enums;

public enum FishingPoleLevel {
    Training ("Training pole", 8, 25, 0, 0.1),
    Bamboo ("Bamboo pole", 8, 500, 0, 0.5),
    FiberGlass ("Fiberglass pole", 6, 1800, 2, 0.9),
    Iridium ("Iridium pole", 4, 7500, 4, 1.2);

    private final String name;
    private final int baseEnergyUsage;
    private final int price;
    private final int baseLevel;
    private final double fishQuality;

    FishingPoleLevel(String name, int baseEnergyUsage, int price, int baseLevel, double fishQuality)
    {
        this.name = name;
        this.baseEnergyUsage = baseEnergyUsage;
        this.price = price;
        this.baseLevel = baseLevel;
        this.fishQuality = fishQuality;
    }

    public int getBaseEnergyUsage() {
        return baseEnergyUsage;
    }

    public int getPrice() {
        return price;
    }

    public int getBaseLevel() {
        return baseLevel;
    }

    public String getName() {
        return name;
    }
}
