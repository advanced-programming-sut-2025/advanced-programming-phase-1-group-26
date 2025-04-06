package model;

import model.enums.FishingPoleLevel;
import model.enums.GameObjectType;
import model.enums.ToolType;

import java.util.ArrayList;

public class FishingPole extends Tool{

    private FishingPoleLevel level;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public FishingPole() {
        super.type = ToolType.FishingPole;
        super.name = type.getName();
        this.level = FishingPoleLevel.Training;
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
