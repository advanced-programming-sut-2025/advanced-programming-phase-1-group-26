package model.Tools;

import model.enums.GameObjectType;
import model.enums.ToolEnums.ToolType;

import java.util.ArrayList;

public class Seythe extends Tool {
    private final int energyUsage;
    private final ArrayList<GameObjectType> usage = new ArrayList<>();

    public Seythe() {
        super.type = ToolType.Seythe;
        super.name = type.getName();
        this.energyUsage = 2;
        /* Set Usage: weed, product */
    }

    public ToolType getType() {
        return type;
    }

    public ArrayList<GameObjectType> getUsage() {
        return usage;
    }
}
