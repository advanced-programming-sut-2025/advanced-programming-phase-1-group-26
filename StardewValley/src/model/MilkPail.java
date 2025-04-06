package model;

import model.enums.GameObjectType;
import model.enums.ToolType;

import java.util.ArrayList;

public class MilkPail extends Tool{
    private final int energyUsage;
    private final int price;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public MilkPail() {
        super.type = ToolType.MilkPail;
        super.name = type.getName();
        this.energyUsage = 4;
        this.price = 1000;
        /* Set Usage: Domestic Animals */
    }

    public ToolType getType() {
        return type;
    }

    public int getEnergyUsage() {
        return energyUsage;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}
