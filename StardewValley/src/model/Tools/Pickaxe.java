package model.Tools;

import model.enums.GameObjectType;
import model.enums.ToolEnums.PickaxeLevel;
import model.enums.ToolEnums.ToolType;

import java.util.ArrayList;

public class Pickaxe extends Tool {

    private PickaxeLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public Pickaxe() {
        super.type = ToolType.Pickaxe;
        super.name = type.getName();
        this.level = PickaxeLevel.base;

        /* Set Usages: Stone & Minerals & HoedDirt & PlayerItem */
    }

    public ToolType getType() {
        return type;
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
