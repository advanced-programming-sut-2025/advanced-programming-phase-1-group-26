package model.tools;

import model.enums.tool_enums.FishingPoleLevel;
import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class FishingPole extends Tool {

    private FishingPoleLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public FishingPole(FishingPoleLevel level) {
        super.type = ToolType.FishingPole;
        super.name = type.getName();
        this.level = level;
        /* Set Usages: near water */
    }

    public ToolType getType() {
        return type;
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
