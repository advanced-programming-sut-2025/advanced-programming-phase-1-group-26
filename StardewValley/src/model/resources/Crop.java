package model.resources;

import model.Tile;
import model.enums.resources_enums.CropType;

public class Crop extends Plant
{
    private boolean oneTime;
    private int growthTime;
    private boolean canBecomeGiant;

    public Crop(CropType cropType, Tile tile)
    {
        this.type = cropType;
        this.name = cropType.getName();
        this.source = cropType.getSeedType();
        this.stages = cropType.getStages();
        this.totalHarvestTime = cropType.getTotalHarvestTime();
        this.oneTime = cropType.isOneTime();
        this.growthTime = cropType.getGrowthTime();
        this.baseSellPrice = cropType.getBaseSellPrice();
        this.isEdible = cropType.isEdible();
        this.energy = cropType.getEnergy();
        this.seasons = cropType.getSeasons();
        this.canBecomeGiant = cropType.isCanBecomeGiant();
        this.ObjectType = cropType.getType();

        this.harvestWaitTime = totalHarvestTime;
        this.tile = tile;
    }

    public String getName()
    {
        return this.name;
    }

//    @Override
//    public boolean canHarvest()
//    {
//        return currentStage == totalHarvestTime && lastHarvested >= harvestWaitTime;
//    }

    public CropType getCropType()
    {
        return (CropType) type;
    }

    public boolean harvest()
    {
        if (oneTime)
        {
            return true;
        }

        hasHarvested = true;
        lastHarvested = 0;
        harvestWaitTime = growthTime;

        return false;
    }
}
