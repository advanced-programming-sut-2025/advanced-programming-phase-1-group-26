package model.tools;

import model.GameObject;
import model.enums.GameObjectType;
import model.enums.tool_enums.BackPackLevel;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class BackPack extends Tool {
    private BackPackLevel level;
    private ArrayList<GameObject> inventory = new ArrayList<>();

    public BackPack() {
        super.ObjectType = GameObjectType.BackPack;
        super.toolType = ToolType.BackPack;
        super.name = toolType.getName();
        this.level = BackPackLevel.base;
    }

    public BackPack(BackPackLevel level) {
        super.toolType = ToolType.BackPack;
        super.name = toolType.getName();
        this.level = level;
    }

    public BackPackLevel getLevel() {
        return level;
    }

    public void setLevel(BackPackLevel level) {
        this.level = level;
    }

    public ArrayList<GameObject> getInventory()
    {
        return inventory;
    }

    public int getCapacity()
    {
        return level.getCapacity();
    }

    public int getSize()
    {
        return inventory.size();
    }
}
