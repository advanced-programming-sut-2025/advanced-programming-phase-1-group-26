package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class Seythe extends Tool {
    private final int energyUsage;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public Seythe() {
        super.ObjectType = GameObjectType.SEYTHE;
        super.toolType = ToolType.Seythe;
        super.name = toolType.getName();
        this.energyUsage = 2;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }

    public int getEnergyUsage()
    {
        return energyUsage;
    }
}
