package model;

import model.enums.GameObjectType;
import model.enums.Season;
import model.enums.TreeType;

import java.util.List;

public class Tree
{
    private final TreeType treeType;
    private String source;
    private List<Integer> stages;
    private int totalHarvestTime;
    private String fruitName;
    private int fruitHarvestCycle;
    private int fruitBaseSellPrice;
    private boolean isEdible;
    private int energy;
    private List<Season> seasons;

    public Tree(TreeType treeType)
    {
        this.treeType = treeType;
        this.source = treeType.getSource();
        this.stages = treeType.getStages();
        this.totalHarvestTime = treeType.getTotalHarvestTime();
        this.fruitName = treeType.getFruitName();
        this.fruitHarvestCycle = treeType.getFruitHarvestCycle();
        this.fruitBaseSellPrice = treeType.getFruitBaseSellPrice();
        this.isEdible = treeType.isEdible();
        this.energy = treeType.getEnergy();
        this.seasons = treeType.getSeasons();
    }
}
