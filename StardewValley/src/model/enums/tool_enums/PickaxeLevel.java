package model.enums.tool_enums;

public enum PickaxeLevel {

    base (5),
    Copper (4),
    Iron (3),
    Golden (2),
    Iridium (1);

    int baseEnergyUsage;
    int failedEnergyUsage;
    /* Set MiningMaxxing Later*/

    PickaxeLevel(int baseEnergyUsage) {
        this.baseEnergyUsage = baseEnergyUsage;
        this.failedEnergyUsage = Math.max(baseEnergyUsage - 1, 0);
    }
}
