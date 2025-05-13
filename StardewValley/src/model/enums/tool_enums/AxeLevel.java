package model.enums.tool_enums;

public enum AxeLevel {
    base ("basic",5),
    Copper ("cooper",4),
    Iron ("iron",3),
    Golden ("golden",2),
    Iridium ("iridium",1);

    String levelName;
    int baseEnergyUsage;
    int failedEnergyUsage;
    /* Set ForagingMaxxing Later*/

    AxeLevel(String name, int baseEnergyUsage) {
        this.levelName = name;
        this.baseEnergyUsage = baseEnergyUsage;
        this.failedEnergyUsage = baseEnergyUsage - 1;

    }

    public String getLevelName() {
        return levelName;
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
