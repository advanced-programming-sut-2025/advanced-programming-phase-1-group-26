package model.enums.tool_enums;

public enum HoeLevel {

    base (5),
    Copper (4),
    Iron (3),
    Golden (2),
    Iridium (1);

    int baseEnergyUsage;
    int failedEnergyUsage;

    HoeLevel(int baseEnergyUsage) {
        this.baseEnergyUsage = baseEnergyUsage;
        this.failedEnergyUsage = Math.max(baseEnergyUsage - 1, 0);
    }

    public int getBaseEnergyUsage() {
        return baseEnergyUsage;
    }

    public void setBaseEnergyUsage(int baseEnergyUsage) {
        this.baseEnergyUsage = baseEnergyUsage;
    }

    public int getFailedEnergyUsage() {
        return failedEnergyUsage;
    }

    public void setFailedEnergyUsage(int failedEnergyUsage) {
        this.failedEnergyUsage = failedEnergyUsage;
    }
}
