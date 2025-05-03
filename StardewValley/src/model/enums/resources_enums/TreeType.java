package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;
import model.resources.Tree;

import java.util.List;
import java.util.Stack;

public enum TreeType
{
    APRICOT_TREE(GameObjectType.APRICOT_TREE, "Apricot Tree", TreeSeedType.Apricot_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Apricot, 1, 59, true, 38, List.of(Season.Spring)),
    CHERRY_TREE(GameObjectType.CHERRY_TREE, "Cherry Tree", TreeSeedType.Cherry_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Cherry, 1, 80, true, 38, List.of(Season.Spring)),
    BANANA_TREE(GameObjectType.BANANA_TREE, "Banana Tree", TreeSeedType.Banana_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Banana, 1, 150, true, 75, List.of(Season.Summer)),
    MANGO_TREE(GameObjectType.MANGO_TREE, "Mango Tree", TreeSeedType.Mango_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Mango, 1, 130, true, 100, List.of(Season.Summer)),
    ORANGE_TREE(GameObjectType.ORANGE_TREE, "Orange Tree", TreeSeedType.Orange_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Orange, 1, 100, true, 38, List.of(Season.Summer)),
    PEACH_TREE(GameObjectType.PEACH_TREE, "Peach Tree", TreeSeedType.Peach_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Peach, 1, 140, true, 38, List.of(Season.Summer)),
    APPLE_TREE(GameObjectType.APPLE_TREE, "Apple Tree", TreeSeedType.Apple_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Apple, 1, 100, true, 38, List.of(Season.Fall)),
    POMEGRANATE_TREE(GameObjectType.POMEGRANATE_TREE,  "Pomegranate Tree",  TreeSeedType.Pomegranate_Sapling, List.of(7, 7, 7, 7), 28, FruitType.Pomegranate, 1, 140, true, 38, List.of(Season.Fall)),
    OAK_TREE(GameObjectType.OAK_TREE, "Oak Tree", TreeSeedType.Acorns, List.of(7, 7, 7, 7), 28, FruitType.Oak_Resin, 7, 150, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAPLE_TREE(GameObjectType.MAPLE_TREE, "Maple Tree", TreeSeedType.Maple_Seeds, List.of(7, 7, 7, 7), 28, FruitType.Maple_Syrup, 9, 200, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    PINE_TREE(GameObjectType.PINE_TREE, "Pine Tree", TreeSeedType.Pine_Cones, List.of(7, 7, 7, 7), 28, FruitType.Pine_Tar, 5, 100, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAHOGANY_TREE(GameObjectType.MAHOGANY_TREE, "Mahogany Tree", TreeSeedType.Mahogany_Seeds, List.of(7, 7, 7, 7), 28, FruitType.Sap, 1, 2, true, -2, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MUSHROOM_TREE(GameObjectType.MUSHROOM_TREE, "Mushroom Tree", TreeSeedType.Mushroom_Tree_Seeds, List.of(7, 7, 7, 7), 28, FruitType.Common_Mushroom, 1, 40, true, 38, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MYSTIC_TREE(GameObjectType.MYSTIC_TREE, "Mystic Tree", TreeSeedType.Mystic_Tree_Seed, List.of(7, 7, 7, 7), 28, FruitType.Mystic_Syrup, 7, 1000, true, 500, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter));
    ;

    private final GameObjectType type;
    private final String name;
    private final TreeSeedType source;
    private final List<Integer> stages;
    private final int totalHarvestTime;
    private final FruitType fruit;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final List<Season> seasons;

    TreeType(GameObjectType type, String name, TreeSeedType source, List<Integer> stages, int totalHarvestTime, FruitType fruit,
             int fruitHarvestCycle, int fruitBaseSellPrice, boolean isEdible, int energy, List<Season> seasons)
    {
        this.type = type;
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruit = fruit;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.seasons = seasons;
    }

    public TreeSeedType getSource()
    {
        return source;
    }

    public List<Integer> getStages()
    {
        return stages;
    }

    public int getTotalHarvestTime()
    {
        return totalHarvestTime;
    }

    public FruitType getFruit()
    {
        return fruit;
    }

    public int getFruitHarvestCycle()
    {
        return fruitHarvestCycle;
    }

    public int getFruitBaseSellPrice()
    {
        return fruitBaseSellPrice;
    }

    public boolean isEdible()
    {
        return isEdible;
    }

    public int getEnergy()
    {
        return energy;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }

    public String getName()
    {
        return name;
    }

    public String getCraftInfo()
    {
        StringBuilder output = new StringBuilder();
        output.append("Name: ").append(name).append("\n");
        output.append("Source: ").append(source.getName()).append("\n");
        output.append("Fruit: ").append(fruit.getName()).append("\n");
        output.append("Fruit Harvest Cycle: ").append(fruitHarvestCycle).append("\n");

        output.append("Stages: ");
        for (int i = 0; i < getStages().size(); i++)
        {
            output.append(getStages().get(i)).append(i == getStages().size() - 1 ? "\n" : " - ");
        }

        output.append("Total Harvest Time: ").append(totalHarvestTime).append("\n");
        output.append("Fruit Base Sell Price: ").append(fruitBaseSellPrice).append("\n");
        output.append("Is Fruit Edible: ").append(isEdible ? "TRUE" : "FALSE").append("\n");
        output.append("Fruit Base Energy: ").append(energy).append("\n");

        output.append("Season(s): ");
        for (int i = 0; i < getSeasons().size(); i++)
        {
            output.append(getSeasons().get(i).getName()).append(i == getSeasons().size() - 1 ? "\n" : ", ");
        }

        return output.toString().trim();
    }

    public static TreeType getTreeType(String name)
    {
        for (TreeType type : TreeType.values())
        {
            if (type.getName().equalsIgnoreCase(name))
            {
                return type;
            }
        }
        return null;
    }
}
