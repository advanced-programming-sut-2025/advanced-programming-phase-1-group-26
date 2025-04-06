package model;

import model.enums.GameObjectType;
import model.enums.HoeLevel;
import model.enums.ToolType;

import java.util.ArrayList;

public class Hoe extends Tool{

    private HoeLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public Hoe() {
        super.type = ToolType.Hoe;
        super.name = type.getName();
        this.level = HoeLevel.base;

        /* Set Usages: Dirt */

    }

    public ToolType getType() {
        return type;
    }

    public HoeLevel getLevel() {
        return level;
    }

    public void setLevel(HoeLevel level) {
        this.level = level;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}
