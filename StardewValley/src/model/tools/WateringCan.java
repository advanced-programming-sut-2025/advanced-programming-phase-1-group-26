package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;
import model.enums.tool_enums.WateringCanLevel;

import java.util.ArrayList;

public class WateringCan extends Tool {
    private WateringCanLevel level;
    private final int maxVolume = 20; // TODO: might change value later
    private int currentVolume = 20;

    public WateringCan() {
        super.type = ToolType.WateringCan;
        super.name = type.getName();
        this.level = WateringCanLevel.base;
    }

    public WateringCan(WateringCanLevel level) {
        super.type = ToolType.WateringCan;
        super.name = type.getName();
        this.level = level;
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

    public int getCurrentVolume()
    {
        return currentVolume;
    }

    public void addVolume(int volume)
    {
        currentVolume = Math.max(currentVolume + volume, maxVolume);
    }

    public void decreaseVolume(int volume)
    {
        currentVolume -= volume;
    }
}
