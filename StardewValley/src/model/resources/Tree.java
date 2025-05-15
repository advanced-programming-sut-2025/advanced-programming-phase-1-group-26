package model.resources;

import model.Tile;
import model.enums.resources_enums.FruitType;
import model.enums.resources_enums.TreeType;

public class Tree extends Plant
{
    private FruitType fruit;
    private int fruitHarvestCycle;

    public Tree(TreeType treeType, Tile tile)
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

        harvestWaitTime = this.totalHarvestTime;
        this.tile = tile;

        if (tile.isGrowFaster())
        {
            setGrowFaster();
        }
    }

    public void harvest()
    {
        hasHarvested = true;
        lastHarvested = 0;
        harvestWaitTime = fruitHarvestCycle;
    }

    public FruitType getFruit()
    {
        return fruit;
    }

    public TreeType getTreeType()
    {
        return (TreeType)type;
    }

    @Override
    public void getAttacked()
    {
        lastHarvested += 1;
    }
}
