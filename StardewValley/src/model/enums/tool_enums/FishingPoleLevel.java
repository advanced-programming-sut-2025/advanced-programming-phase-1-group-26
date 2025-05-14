package model.enums.tool_enums;

public enum FishingPoleLevel {
    Training ("Training pole", 8, 25, 0),
    Bamboo ("Bamboo pole", 8, 500, 0),
    FiberGlass ("Fiberglass pole", 6, 1800, 2),
    Iridium ("Iridium pole", 4, 7500, 4);

    String name;
    int baseEnergyUsage;
    int price;
    int baseLevel;
    /* Different fish */
    /* Set FishingMaxxing Later*/

    FishingPoleLevel(String name, int baseEnergyUsage, int price, int baseLevel) {
        this.name = name;
        this.baseEnergyUsage = baseEnergyUsage;
        this.price = price;
        this.baseLevel = baseLevel;
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
