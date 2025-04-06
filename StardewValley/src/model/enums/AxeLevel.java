package model.enums;

public enum AxeLevel {
    base (5),
    Copper (4),
    Iron (3),
    Golden (2),
    Iridium (1);

    int baseEnergyUsage;
    int failedEnergyUsage;
    /* Set ForagingMaxxing Later*/

    AxeLevel(int baseEnergyUsage) {
        this.baseEnergyUsage = baseEnergyUsage;
        this.failedEnergyUsage = baseEnergyUsage - 1;

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
