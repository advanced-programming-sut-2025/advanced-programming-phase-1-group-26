package model.tools;

import model.enums.tool_enums.FishingPoleLevel;
import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class FishingPole extends Tool {

    private FishingPoleLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public FishingPole(FishingPoleLevel level) {
        super.ObjectType = GameObjectType.FISHING_POLE;
        super.toolType = ToolType.FishingPole;
        super.name = toolType.getName();
        this.level = level;
        /* Set Usages: near water */
    }

    public ToolType getToolType() {
        return toolType;
    }

    public FishingPoleLevel getLevel() {
        return level;
    }

    public void setLevel(FishingPoleLevel level) {
        this.level = level;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}
