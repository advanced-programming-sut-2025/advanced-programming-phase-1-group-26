package model.tools;

import model.GameObject;
import model.enums.tool_enums.BackPackLevel;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class BackPack extends Tool {
    private BackPackLevel level;
    private ArrayList<GameObject> inventory = new ArrayList<>();

    public BackPack() {
        super.type = ToolType.BackPack;
        super.name = type.getName();
        this.level = BackPackLevel.base;
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
