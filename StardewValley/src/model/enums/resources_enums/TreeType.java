package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.List;

public enum TreeType
{
    APRICOT_TREE(GameObjectType.APRICOT_TREE, "Apricot Sapling", List.of(7, 7, 7, 7), 28, "Apricot", 1, 59, true, 38, List.of(Season.Spring)),
    CHERRY_TREE(GameObjectType.CHERRY_TREE, "Cherry Sapling", List.of(7, 7, 7, 7), 28, "Cherry", 1, 80, true, 38, List.of(Season.Spring)),
    BANANA_TREE(GameObjectType.BANANA_TREE, "Banana Sapling", List.of(7, 7, 7, 7), 28, "Banana", 1, 150, true, 75, List.of(Season.Summer)),
    MANGO_TREE(GameObjectType.MANGO_TREE, "Mango Sapling", List.of(7, 7, 7, 7), 28, "Mango", 1, 130, true, 100, List.of(Season.Summer)),
    ORANGE_TREE(GameObjectType.ORANGE_TREE, "Orange Sapling", List.of(7, 7, 7, 7), 28, "Orange", 1, 100, true, 38, List.of(Season.Summer)),
    PEACH_TREE(GameObjectType.PEACH_TREE, "Peach Sapling", List.of(7, 7, 7, 7), 28, "Peach", 1, 140, true, 38, List.of(Season.Summer)),
    APPLE_TREE(GameObjectType.APPLE_TREE, "Apple Sapling", List.of(7, 7, 7, 7), 28, "Apple", 1, 100, true, 38, List.of(Season.Fall)),
    POMEGRANATE_TREE(GameObjectType.POMEGRANATE_TREE, "Pomegranate Sapling", List.of(7, 7, 7, 7), 28, "Pomegranate", 1, 140, true, 38, List.of(Season.Fall)),
    OAK_TREE(GameObjectType.OAK_TREE, "Acorns", List.of(7, 7, 7, 7), 28, "Oak Resin", 7, 150, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAPLE_TREE(GameObjectType.MAPLE_TREE, "Maple Seeds", List.of(7, 7, 7, 7), 28, "Maple Syrup", 9, 200, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    PINE_TREE(GameObjectType.PINE_TREE, "Pine Cones", List.of(7, 7, 7, 7), 28, "Pine Tar", 5, 100, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAHOGANY_TREE(GameObjectType.MAHOGANY_TREE, "Mahogany Seeds", List.of(7, 7, 7, 7), 28, "Sap", 1, 2, true, -2, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MUSHROOM_TREE(GameObjectType.MUSHROOM_TREE, "Mushroom Tree Seeds", List.of(7, 7, 7, 7), 28, "Common Mushroom", 1, 40, true, 38, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MYSTIC_TREE(GameObjectType.MYSTIC_TREE, "Mystic Tree Seeds", List.of(7, 7, 7, 7), 28, "Mystic Syrup", 7, 1000, true, 500, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter));
    ;

    private final GameObjectType type;
    private final String source;
    private final List<Integer> stages;
    private final int totalHarvestTime;
    private final String fruitName;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final List<Season> seasons;

    TreeType(GameObjectType type, String source, List<Integer> stages, int totalHarvestTime, String fruitName,
             int fruitHarvestCycle, int fruitBaseSellPrice, boolean isEdible, int energy, List<Season> seasons)
    {
        this.type = type;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitName = fruitName;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.seasons = seasons;
    }

    public String getSource()
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

    public String getFruitName()
    {
        return fruitName;
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
}
