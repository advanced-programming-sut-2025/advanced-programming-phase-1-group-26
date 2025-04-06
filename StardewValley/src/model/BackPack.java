package model;

import model.enums.AxeLevel;
import model.enums.BackPackLevel;
import model.enums.ToolType;

public class BackPack extends Tool {
    private BackPackLevel level;

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
}
