package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;
import model.enums.tool_enums.TrashCanLevel;
import model.enums.tool_enums.WateringCanLevel;

import java.util.ArrayList;

public class TrashCan extends Tool {
    private TrashCanLevel level;
    private double percentage;

    public TrashCan() {
        super.ObjectType = GameObjectType.TRASH_CAN;
        super.toolType = ToolType.TrashCan;
        super.name = toolType.getName();
        this.level = TrashCanLevel.base;
        this.percentage = level.getReturnedMoneyPercentage();
    }

    public TrashCan(TrashCanLevel level) {
        super.ObjectType = GameObjectType.TRASH_CAN;
        super.toolType = ToolType.TrashCan;
        super.name = toolType.getName();
        this.level = level;
        this.percentage = level.getReturnedMoneyPercentage();
    }

    public TrashCanLevel getLevel() {
        return level;
    }

    public void setLevel(TrashCanLevel level) {
        this.level = level;
    }

    public double getPercentage() {
        return percentage;
    }
}
