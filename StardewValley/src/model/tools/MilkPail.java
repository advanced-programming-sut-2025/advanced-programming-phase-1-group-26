package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class MilkPail extends Tool {
    private final int energyUsage;
    private final int price;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public MilkPail() {
        super.ObjectType = GameObjectType.MILK_PAIL;
        super.toolType = ToolType.MilkPail;
        super.name = toolType.getName();
        this.energyUsage = 4;
        this.price = 1000;
        /* Set Usage: Domestic Animals */
    }

    public ToolType getToolType() {
        return toolType;
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
