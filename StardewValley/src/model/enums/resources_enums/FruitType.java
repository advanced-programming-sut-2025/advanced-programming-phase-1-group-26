package model.enums.resources_enums;

import model.enums.GameObjectType;

public enum FruitType
{
    Apricot(GameObjectType.APRICOT, "Apricot"),
    Cherry(GameObjectType.CHERRY, "Cherry"),
    Banana(GameObjectType.BANANA, "Banana"),
    Mango(GameObjectType.MANGO, "Mango"),
    Orange(GameObjectType.ORANGE, "Orange"),
    Peach(GameObjectType.PEACH, "Peach"),
    Apple(GameObjectType.APPLE, "Apple"),
    Pomegranate(GameObjectType.POMEGRANATE, "Pomegranate"),
    Oak_Resin(GameObjectType.OAK_RESIN, "Oak_Resin"),
    Maple_Syrup(GameObjectType.MAPLE_SYRUP, "Maple_Syrup"),
    Pine_Tar(GameObjectType.PINE_TAR, "Pine Tar"),
    Sap(GameObjectType.SAP, "Sap"),
    Common_Mushroom(GameObjectType.COMMON_MUSHROOM, "Common_Mushroom"),
    Mystic_Syrup(GameObjectType.COMMON_MUSHROOM, "Mystic_Syrup"),
    ;

    private final GameObjectType type;
    private final String name;

    FruitType(GameObjectType type, String name)
    {
        this.type = type;
        this.name = name;
    }
}
