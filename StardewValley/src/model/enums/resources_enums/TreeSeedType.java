package model.enums.resources_enums;

import model.enums.GameObjectType;

public enum TreeSeedType
{
    Apricot_Sapling(GameObjectType.Apricot_Sapling, "apricot_sapling"),
    Cherry_Sapling(GameObjectType.Cherry_Sapling, "cherry sapling"),
    Banana_Sapling(GameObjectType.Banana_Sapling, "banana sapling"),
    Mango_Sapling(GameObjectType.Mango_Sapling, "mango sapling"),
    Orange_Sapling(GameObjectType.Orange_Sapling, "orange sapling"),
    Peach_Sapling(GameObjectType.Peach_Sapling, "peach sapling"),
    Apple_Sapling(GameObjectType.Apple_Sapling, "apple sapling"),
    Pomegranate_Sapling(GameObjectType.Pomegranate_Sapling, "pomegranate sapling"),
    Acorns(GameObjectType.Acorns, "acorns"),
    Maple_Seeds(GameObjectType.MAPLE_SEED, "maple seeds"),
    Pine_Cones(GameObjectType.PINE_CONE, "pine cones"),
    Mahogany_Seeds(GameObjectType.MAHOGANY_SEED, "mahogany seeds"),
    Mushroom_Tree_Seeds(GameObjectType.Mushroom_Tree_Seeds, "mushroom tree seeds"),
    Mystic_Tree_Seed(GameObjectType.MYSTIC_TREE_SEED, "mystic tree seed"),
    ;

    private final GameObjectType type;
    private final String name;

    TreeSeedType(GameObjectType type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
