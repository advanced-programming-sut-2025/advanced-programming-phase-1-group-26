package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;
import model.enums.tool_enums.WateringCanLevel;

public class WateringCan extends Tool {
    private WateringCanLevel level;
    private int currentVolume = 20;

    public WateringCan() {
        super.ObjectType = GameObjectType.WATERING_CAN;
        super.toolType = ToolType.WateringCan;
        super.name = toolType.getName();
        this.level = WateringCanLevel.base;
    }

    public WateringCan(WateringCanLevel level) {
        super.toolType = ToolType.WateringCan;
        super.name = toolType.getName();
        this.level = level;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public WateringCanLevel getLevel() {
        return level;
    }

    public void setLevel(WateringCanLevel level) {
        this.level = level;
    }

    public int getCurrentVolume()
    {
        return currentVolume;
    }

    public void addVolume(int volume)
    {
        currentVolume = Math.max(currentVolume + volume, level.getCapacity());
    }

    public void decreaseVolume(int volume)
    {
        currentVolume = Math.max(currentVolume - volume, 0);
    }
}
