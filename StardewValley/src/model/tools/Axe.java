package model.tools;

import model.enums.tool_enums.AxeLevel;
import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

import java.util.ArrayList;

public class Axe extends Tool {
    private AxeLevel level;


    public Axe() {
        super.type = ToolType.Axe;
        super.name = type.getName();
        this.level = AxeLevel.base;
    }

    public ToolType getType() {
        return type;
    }

    public AxeLevel getLevel() {
        return level;
    }

    public void setLevel(AxeLevel level) {
        this.level = level;
    }

}

