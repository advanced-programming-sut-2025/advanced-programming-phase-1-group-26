package model.resources;

import model.GameObject;
import model.enums.Season;
import model.enums.resources_enums.FruitType;
import model.enums.resources_enums.TreeType;

import java.util.List;

public class Tree extends CropsAndTrees
{
    private FruitType fruit;
    private int fruitHarvestCycle;
    private List<Season> seasons;

    public Tree(TreeType treeType)
    {
        this.type = treeType;
        this.name = treeType.getName();
        this.source = treeType.getSource();
        this.stages = treeType.getStages();
        this.totalHarvestTime = treeType.getTotalHarvestTime();
        this.fruit = treeType.getFruit();
        this.fruitHarvestCycle = treeType.getFruitHarvestCycle();
        this.baseSellPrice = treeType.getFruitBaseSellPrice();
        this.isEdible = treeType.isEdible();
        this.energy = treeType.getEnergy();
        this.seasons = treeType.getSeasons();
    }
}
