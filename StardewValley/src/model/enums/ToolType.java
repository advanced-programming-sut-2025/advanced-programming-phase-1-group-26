package model.enums;

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
}
