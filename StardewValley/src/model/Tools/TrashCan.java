package model.Tools;

import model.enums.GameObjectType;
import model.enums.ToolEnums.ToolType;
import model.enums.ToolEnums.WateringCanLevel;

import java.util.ArrayList;

public class TrashCan extends Tool {
    private WateringCanLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public TrashCan() {
        super.type = ToolType.TrashCan;
        super.name = type.getName();
        this.level = WateringCanLevel.base;
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
