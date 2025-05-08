package model.resources;

import model.enums.resources_enums.CropType;

public class Crop extends Plant
{
    private boolean oneTime;
    private int growthTime;
    private boolean canBecomeGiant;

    public Crop(CropType cropType)
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
//        this.ObjectType = cropType.getT
    }

    public String getName()
    {
        return this.name;
    }
}
