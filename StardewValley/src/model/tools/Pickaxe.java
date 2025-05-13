package model.tools;

import model.enums.GameObjectType;
import model.enums.tool_enums.PickaxeLevel;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class Pickaxe extends Tool {

    private PickaxeLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public Pickaxe() {
        super.ObjectType = GameObjectType.PICKAXE;
        super.toolType = ToolType.Pickaxe;
        super.name = toolType.getName();
        this.level = PickaxeLevel.base;
    }

    public Pickaxe(PickaxeLevel level) {
        super.toolType = ToolType.Pickaxe;
        super.name = toolType.getName();
        this.level = level;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public PickaxeLevel getLevel() {
        return level;
    }

    public void setLevel(PickaxeLevel level) {
        this.level = level;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}
