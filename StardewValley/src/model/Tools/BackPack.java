package model.Tools;

import model.enums.ToolEnums.BackPackLevel;
import model.enums.ToolEnums.ToolType;

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
