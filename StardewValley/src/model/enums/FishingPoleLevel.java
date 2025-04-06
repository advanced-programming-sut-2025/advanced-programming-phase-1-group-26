package model.enums;

public enum FishingPoleLevel {
    Training (8, 25, 0),
    Bamboo (8, 500, 0),
    FiberGlass (6, 1800, 2),
    Iridium (4, 7500, 4);

    int baseEnergyUsage;
    int price;
    int baseLevel;
    /* Different fish */
    /* Set FishingMaxxing Later*/

    FishingPoleLevel(int baseEnergyUsage, int price, int baseLevel) {
        this.baseEnergyUsage = baseEnergyUsage;
        this.price = price;
        this.baseLevel = baseLevel;
    }
}
