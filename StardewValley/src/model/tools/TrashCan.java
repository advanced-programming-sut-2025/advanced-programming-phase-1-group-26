package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;
import model.enums.tool_enums.WateringCanLevel;

import java.util.ArrayList;

public class TrashCan extends Tool {
    private WateringCanLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public TrashCan() {
        super.type = ToolType.TrashCan;
        super.name = type.getName();
        this.level = WateringCanLevel.base;
    }

    public TrashCan(WateringCanLevel level) {
        super.type = ToolType.TrashCan;
        super.name = type.getName();
        this.level = level;
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
