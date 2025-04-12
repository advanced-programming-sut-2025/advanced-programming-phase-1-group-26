package model.enums.ToolEnums;

public enum TrashCanLevel {
    base (0.00),
    Copper (0.15),
    Iron (0.30),
    Golden (0.45),
    Iridium (0.60);

    final double returnedMoneyPercentage;

    TrashCanLevel(double returnedMoneyPercentage) {
        this.returnedMoneyPercentage = returnedMoneyPercentage;
    }

    public double getReturnedMoneyPercentage() {
        return returnedMoneyPercentage;
    }
}
