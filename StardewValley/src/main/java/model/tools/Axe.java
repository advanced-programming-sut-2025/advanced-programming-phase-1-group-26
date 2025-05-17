package model.tools;

import model.enums.tool_enums.AxeLevel;
import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class Axe extends Tool {
    private AxeLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public Axe() {
        super.ObjectType = GameObjectType.AXE;
        super.toolType = ToolType.Axe;
        super.name = toolType.getName();
        this.level = AxeLevel.base;
    }

    public Axe(AxeLevel level) {
        super.toolType = ToolType.Axe;
        super.name = toolType.getName();
        this.level = level;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public AxeLevel getLevel() {
        return level;
    }

    public void setLevel(AxeLevel level) {
        this.level = level;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}

