package model.enums.tool_enums;

public enum WateringCanLevel {

    base (40, 5),
    Copper (55, 4),
    Iron (70, 3),
    Golden (85, 2),
    Iridium (100, 1);

    final int capacity;
    final int baseEnergyUsage;
    /* Set FarmingMaxxing Later*/

    WateringCanLevel(int capacity, int baseEnergyUsage) {
        this.capacity = capacity;
        this.baseEnergyUsage = baseEnergyUsage;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBaseEnergyUsage() {
        return baseEnergyUsage;
    }
}
