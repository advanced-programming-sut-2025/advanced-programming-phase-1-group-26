package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;
import model.enums.tool_enums.WateringCanLevel;

import java.util.ArrayList;

public class WateringCan extends Tool {
    private WateringCanLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public WateringCan() {
        super.type = ToolType.WateringCan;
        super.name = type.getName();
        this.level = WateringCanLevel.base;

        /* Set Usages: near products & water */
    }

    public ToolType getType() {
        return type;
    }

    public WateringCanLevel getLevel() {
        return level;
    }

    public void setLevel(WateringCanLevel level) {
        this.level = level;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}
