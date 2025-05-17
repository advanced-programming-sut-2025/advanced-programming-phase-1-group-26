package model.enums.tool_enums;

public enum ToolType {
    Hoe ("Hoe"),
    Pickaxe ("Pickaxe"),
    Axe ("Axe"),
    WateringCan ("Watering Can"),
    FishingPole ("Fishing Pole"),
    Seythe ("Seythe"),
    MilkPail ("Milk Pail"),
    Shear ("Shear"),
    BackPack ("Back Pack"),
    TrashCan ("Trash Can");

    final String name;

    ToolType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ToolType getToolType(String name)
    {
        for (ToolType toolType : ToolType.values())
        {
            if (toolType.getName().equals(name))
            {
                return toolType;
            }
        }
        return null;
    }
}
